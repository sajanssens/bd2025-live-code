package com.infosupport.jeedemo.api.resources;

import com.infosupport.jeedemo.BEER;
import com.infosupport.jeedemo.Dao;
import com.infosupport.jeedemo.domain.Beer;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.slf4j.Logger;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("beers")
public class BeersResource {

    @Inject @Named("general")
    private Logger log;

    @Inject @BEER
    private Dao<Beer> beerDao;

    @Inject
    private BeerResource beerResource;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Beer> getAll() {
        return beerDao.findAll();
    }

    @POST
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    public Beer add(Beer b) {
        return beerDao.create(b);
    }

    // HIER GEEN WERKWOORD ZOALS @GET GEBRUIKEN!!!
    @Path("{id}")
    public BeerResource toBeerResource(@PathParam("id") String id) {
        return beerResource.with(id);
    }
}
