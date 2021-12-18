package net.rhuanrocha.resource;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.security.Principal;

@Path("hello/prog")
public class HelloWorldProgResource {

    @Inject
    private SecurityContext securityContext;

    @GET
    public String hello(){
        Principal principal = securityContext.getCallerPrincipal();
        if(!securityContext.isCallerInRole("admin")){
            throw new ForbiddenException("User not allowed");
        }

        return "Hello World "+principal.getName();
    }
}
