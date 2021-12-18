package net.rhuanrocha.ejb;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;

@Stateless
public class HelloWorldService {

    @RolesAllowed("admin")
    public String sayHello(){
        return "Hello EJB";
    }
}
