package net.rhuanrocha.endpoint;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.rhuanrocha.bean.Email;
import net.rhuanrocha.consumer.EmailConsumer;
import net.rhuanrocha.consumer.EmailConsumerTopic;
import net.rhuanrocha.service.EmailService;
import net.rhuanrocha.service.EmailServiceTopic;



@Path("email")
public class EmailEndpoint {

    @Inject
    private EmailService emailService;

    @Inject
    private EmailServiceTopic emailServiceTopic;

    @Inject
    private EmailConsumer emailConsumer;

    @Inject
    private EmailConsumerTopic emailConsumerTopic;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(Email email){
        emailService.sendEmail(email);
        return Response.ok().build();
    }

    @GET
    public Response startConsumer(){
        emailConsumer.consumeMessage();
        return Response.ok().build();
    }

    @GET
    @Path("/topic")
    public Response startConsumerTopic(@QueryParam("typeSubscription") @DefaultValue("NONDURABLE_UNSHARED") String typeSubscription){

        switch (typeSubscription){
            case "DURABLE_UNSHARED":
                emailConsumerTopic.consumeMessageDurableUnshared();
                break;
            case "DURABLE_SHARED":
                emailConsumerTopic.consumeMessageDurableShared();
                break;
            case "NONDURABLE_SHARED":
                emailConsumerTopic.consumeMessageNondurableShared();
                break;
            default:
                emailConsumerTopic.consumeMessageNondurableUnshared();
        }

        return Response.ok().build();
    }

    @POST
    @Path("/topic")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmailTopic(Email email){
        emailServiceTopic.sendEmail(email);
        return Response.ok().build();
    }
}