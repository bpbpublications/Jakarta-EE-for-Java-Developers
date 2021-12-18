package net.rhuanrocha.samplerestful.resources;

import net.rhuanrocha.samplerestful.beans.User;
import net.rhuanrocha.samplerestful.persistence.UserDatasource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/users")
public class UserResource {

    @Inject
    private UserDatasource userDatasource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("email") String email){
        if(email != null){
            return Response
                    .ok(userDatasource.listByEmail(email))
                    .build();
        }

        return Response.ok(userDatasource.listAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response save(@FormParam("name")String name, @FormParam("email") String email){
        User user = userDatasource.persist(User.of(name,email));
        //returning a HTTP 201 with locator = /users/{id}

        return Response
                .created(URI.create(String.format("/users/%s", user.getId())))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id){
        User user = userDatasource.findById(id);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(user).build();
    }

}
