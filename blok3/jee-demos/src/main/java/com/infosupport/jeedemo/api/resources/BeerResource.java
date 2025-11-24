package com.infosupport.jeedemo.api.resources;

import com.infosupport.jeedemo.BEER;
import com.infosupport.jeedemo.domain.Beer;
import com.infosupport.jeedemo.domain.Repo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import lombok.Setter;
import org.slf4j.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped // to guarantee thread safety, each request gets its own instance of this class
@Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
public class BeerResource {

    @Inject
    private Logger log;

    @Inject @BEER
    private Repo<Beer> beerDao;

    @Setter
    private String id;

    @GET
    @Produces(APPLICATION_JSON)
    public Beer get() {
        return beerDao.read(Integer.parseInt(this.id));
    }
}
