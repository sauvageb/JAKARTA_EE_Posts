package com.example.demo.api;

import com.example.demo.api.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
public class UserResource {

    private static UserService userService = new UserService();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<UserDto> userList = userService.findAll();

        return Response
                .ok(userList)
                .build();
    }


}
