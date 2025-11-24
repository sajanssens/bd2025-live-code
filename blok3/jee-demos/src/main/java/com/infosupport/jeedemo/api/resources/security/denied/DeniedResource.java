package com.infosupport.jeedemo.api.resources.security.denied;

import jakarta.annotation.security.DenyAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("denied")
@DenyAll
public class DeniedResource {
    @GET
    public String denied() {
        return "Nobody will be able to access this endpoint";
    }
}
