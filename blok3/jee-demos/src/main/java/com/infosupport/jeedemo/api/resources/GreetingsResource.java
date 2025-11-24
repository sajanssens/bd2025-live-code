package com.infosupport.jeedemo.api.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("greetings")
public class GreetingsResource {

    @Inject
    private Logger log;

    @GET // returns Response object directly.
    @Produces(APPLICATION_JSON)
    public Response getAll() {
        return Response.ok()
                .entity("Hello!") // body
                .build();
    }
}
