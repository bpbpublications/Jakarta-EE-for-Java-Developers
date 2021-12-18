package net.rhuanrocha.endpoint;

import net.rhuanrocha.singleton.AccessSingleton;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("access")
public class AccessCountEndpoint {

    @Inject
    private AccessSingleton accessSingleton;

    @GET
    public Response count(){
        accessSingleton.increment();
        return Response.ok(accessSingleton.getCount()).build();
    }
}
