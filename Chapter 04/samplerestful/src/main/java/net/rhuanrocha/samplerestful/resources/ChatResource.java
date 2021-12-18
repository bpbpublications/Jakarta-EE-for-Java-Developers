package net.rhuanrocha.samplerestful.resources;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class ChatResource {

    @Context
    private Sse sse;
    private SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void init(){
        sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void startConnection(@Context SseEventSink eventSink){
        eventSink.send(sse.newEvent("welcome to chat!"));
        sseBroadcaster.register(eventSink);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response sendMessage(@FormParam("message") String message){
        sseBroadcaster.broadcast(sse.newEvent(message));
        return Response.ok().build();
    }

}
