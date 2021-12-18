package net.rhuanrocha.samplerestfulclient.resources;

import net.rhuanrocha.samplerestfulclient.services.ChatService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
public class ChatEndpoint {

    @Inject
    private ChatService chatService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response startConnection(){
        chatService.startConnection();
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response sendMessage(@FormParam("message") String message){
        chatService.sendMessage(message);
        return Response.ok().build();
    }

    @GET
    @Path("/messages")
    public Response listMessages(){
        return Response.ok(chatService.getMessages()).build();
    }
}
