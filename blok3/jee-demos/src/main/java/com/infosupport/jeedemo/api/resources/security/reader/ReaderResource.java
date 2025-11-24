package com.infosupport.jeedemo.api.resources.security.reader;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("reader")
@RolesAllowed({"reader"})
public class ReaderResource {

    @GET
    public String helloReader() {
        return "Hello reader!";
    }
}
