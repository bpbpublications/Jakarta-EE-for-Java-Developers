package net.rhuanrocha.resource;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import net.rhuanrocha.ejb.HelloWorldService;

@Path("hello")
@DeclareRoles({ "admin", "customer" })
@RolesAllowed("admin")
public class HelloWorldResource {
    @Inject
    private HelloWorldService helloWorldService;

    @GET
    @RolesAllowed("admin")
    public String hello(){
        return "Hello World";
    }

    @GET
    @Path("/ejb")
    @PermitAll
    public String helloEjb(){
        return helloWorldService.sayHello();
    }
}

