package net.rhuanrocha.samplerestfulclient.services;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.SseEventSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class ChatService implements Serializable {
    private static final String TARGET_SERVICE= "http://localhost:8080/samplerestful-1.0-SNAPSHOT/resources/chat";
    private SseEventSource sseEventSource;
    private List<String> messages;

    @PostConstruct
    public void init(){
        messages = new ArrayList<>();
    }

    public void startConnection(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(TARGET_SERVICE);
        sseEventSource = SseEventSource.target(webTarget).build();
        sseEventSource.register(inboundSseEvent -> messages.add(inboundSseEvent.readData()));
        sseEventSource.open();
    }

    public boolean sendMessage(String message){
        Form form = new Form();
        form.param("message", message);

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(TARGET_SERVICE).request()
                .post(Entity.form(form));

        return response.getStatus() == Response.Status.OK.getStatusCode();
    }
    public void closeConnection(){
        sseEventSource.close();
    }

    public List<String> getMessages(){
        return messages;
    }
}
