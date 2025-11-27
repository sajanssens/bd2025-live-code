package com.infosupport.jeedemo.api.resources;

import com.infosupport.jeedemo.api.ejbfeatures.LogMethodCall;
import com.infosupport.jeedemo.api.exceptions.QueryParamTooLongException;
import com.infosupport.jeedemo.domain.Beer;
import com.infosupport.jeedemo.domain.BeerRepoEJB;
import com.infosupport.jeedemo.domain.Repo;
import com.infosupport.jeedemo.domain.Role;
import com.infosupport.jeedemo.domain.qualifiers.BEER;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Path("beers")
public class BeersResource {

    private final Logger log = LoggerFactory.getLogger(BeersResource.class);

    @Inject @BEER
    private Repo<Beer> beerRepo;

    @Inject
    private BeerRepoEJB beerRepoEJB;

    @Inject
    private BeerResource beerResource;

    @GET
    @Produces(APPLICATION_JSON)
    @RolesAllowed(Role.ADMIN)
    public Collection<Beer> get(@QueryParam("brand") String brand) {
        if (brand != null && brand.length() > 10) {
            // throw new BadRequestException("brand mag niet langer zijn dan 10 tekens");
            throw new QueryParamTooLongException("brand mag niet langer zijn dan 10 tekens");
        }
        return beerRepo.findAll();
    }

    @POST
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @RolesAllowed(Role.ADMIN)
    public Beer post(Beer b) {
        return beerRepo.create(b);
    }

    @POST @Path("ejb")
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @PermitAll
    @LogMethodCall
    public Beer postEJB(Beer b) {
        Future<Integer> integerInTheFuture = beerRepoEJB.fireAndReturnInTheFuture();
        supplyAsync(() -> getNow(integerInTheFuture))
                .thenAccept(i -> log.debug("Integer from the future is now: {}", i));

        return beerRepoEJB.create(b);
    }

    private static Integer getNow(Future<Integer> i) {
        try {
            return i.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    // HIER GEEN WERKWOORD ZOALS @GET GEBRUIKEN!!!
    @Path("{id}")
    public BeerResource toBeerResource(@PathParam("id") String id) {
        beerResource.setId(id);
        return beerResource;
    }
}
