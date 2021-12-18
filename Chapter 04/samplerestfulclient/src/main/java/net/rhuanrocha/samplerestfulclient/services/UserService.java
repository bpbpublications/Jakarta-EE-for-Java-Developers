package net.rhuanrocha.samplerestfulclient.services;

import net.rhuanrocha.samplerestfulclient.dtos.UserDto;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class UserService {
    private static final String TARGET_SERVICE= "http://localhost:8080/samplerestful-1.0-SNAPSHOT/resources/users";
    private Logger logger = Logger.getLogger(UserService.class.getName());
    @Inject
    private EmailService emailService;

    public UserDto findById(String id){
        Client client = ClientBuilder.newClient();
        UserDto userDto = client
                .target(TARGET_SERVICE+"/"+id).request(MediaType.APPLICATION_JSON)
                .get(UserDto.class);
        return userDto;
    }

    public boolean save(UserDto userDto){
        Form form = new Form();
        form.param("name", userDto.getName());
        form.param("email", userDto.getEmail());

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(TARGET_SERVICE+"/").request()
                .post(Entity.form(form));

        return response.getStatus() == Response.Status.CREATED.getStatusCode();// If status is 201 then it returns true.
    }

    public void saveAsync(UserDto userDto, String subject, String message){
        Form form = new Form();
        form.param("name", userDto.getName());
        form.param("email", userDto.getEmail());


        Client client = ClientBuilder.newClient();
        CompletionStage<Response> completionStage = client
                .target(TARGET_SERVICE+"/").request()
                .rx()
                .post(Entity.form(form));

        completionStage.thenAccept(response -> {
            if(response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                emailService.send("system@test.test",userDto.getEmail(),subject,message);
                logger.info("User solve!");
            }
            else{
                logger.warning("The User had some issue to solve.");
            }
        });


    }

    /*public UserDto findById(String id){
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(TARGET_SERVICE+"/"+id).request(MediaType.APPLICATION_JSON)
                .get();
        if(response.getStatus() != 200){
            return null;
        }
        return response.readEntity(UserDto.class);
    }*/


}
