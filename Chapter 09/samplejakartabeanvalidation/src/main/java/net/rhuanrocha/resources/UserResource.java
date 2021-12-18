package net.rhuanrocha.resources;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.rhuanrocha.dto.UserDto;
import net.rhuanrocha.entity.User;
import net.rhuanrocha.service.UserService;

import java.net.URI;

@Path("users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@NotNull UserDto user){
        User userToSave = new User();
        user.setName(user.getName());
        user.setName(user.getEmail());
        userService.save(userToSave);

        return Response
                .created(URI.create(String.format("/users/%d",userToSave.getId())))
                .build();
    }
}
