package com.infosupport.jeedemo.api.resources.security.permitted;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("permitted")
@PermitAll
public class PermittedResource {
    @GET
    public String permitted() {
        return "Everyone will be able to access this endpoint.";
    }
}
