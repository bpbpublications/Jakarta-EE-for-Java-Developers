package net.rhuanrocha.samplerestfulclient.resources;

import net.rhuanrocha.samplerestfulclient.dtos.UserDto;
import net.rhuanrocha.samplerestfulclient.services.EmailService;
import net.rhuanrocha.samplerestfulclient.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/emails")
public class EmailResource {

    @Inject
    private UserService userService;

    @Inject
    private EmailService emailService;

    @POST
    @Path("/{idUser}")
    public Response send(@PathParam("idUser") String idUser,
                         @DefaultValue ("Hello") @FormParam("subject") String subject,
                         @DefaultValue("Hello! Wellcome!") @FormParam("message") String message){

        UserDto user = userService.findById(idUser);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        emailService.send("system@test.test",user.getEmail(),subject,message);
        return Response.ok().build();
    }

    @POST
    public Response sendAndSaveUser(@FormParam("name")String name,
                                    @FormParam("email") String email,
                                    @DefaultValue ("Hello") @FormParam("subject") String subject,
                                    @DefaultValue("Hello! Wellcome!") @FormParam("message") String message){
          UserDto user = UserDto.of(name, email);
          userService.saveAsync(user,subject,message);
          return Response.ok().build();

    }

}
