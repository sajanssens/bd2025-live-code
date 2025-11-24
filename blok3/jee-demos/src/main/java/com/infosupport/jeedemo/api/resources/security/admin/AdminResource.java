package com.infosupport.jeedemo.api.resources.security.admin;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("admin")
public class AdminResource {
    @GET
    @RolesAllowed({"admin", "user"})
    public String helloUser() {
        return "Hello user or admin!";
    }

    @POST
    @RolesAllowed({"admin"})
    public String helloAdmin() {
        return "Hello admin!";
    }
}
