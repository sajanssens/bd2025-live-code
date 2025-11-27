package com.infosupport.jeedemo.api.resources;

import com.infosupport.jeedemo.api.filter.NoAuthorizationNeeded;
import com.infosupport.jeedemo.api.util.KeyGeneratorSimple;
import com.infosupport.jeedemo.domain.User;
import com.infosupport.jeedemo.domain.UserDto;
import com.infosupport.jeedemo.domain.UserRepo;
import io.jsonwebtoken.Jwts;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.time.LocalDateTime.now;

@Path("users-manual-jwt")
public class UsersResourceManualJwt {

    @Inject
    private UserRepo repo;

    @Inject
    private KeyGeneratorSimple keyGenerator;

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @NoAuthorizationNeeded
    public User register(User u) {
        return repo.create(u);
    }

    @POST @Path("login")
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @NoAuthorizationNeeded
    public User login(UserDto input) {
        try {
            String username = input.username();
            String password = input.password();

            User user = repo.findByUsernameAndPassword(username, password);

            String jwt = issueToken(user);
            user.setToken(jwt);

            return user;
        } catch (NoResultException e) {
            throw new NotAuthorizedException("User " + input + " is not authorized.");
        }
    }

    private String issueToken(User user) {
        return Jwts.builder()
                .issuer(uriInfo.getAbsolutePath().toString())
                .subject("beers")
                .claim("username", user.getUsername())
                .claim("roles", user.getRoles())
                .issuedAt(new Date())
                .expiration(toDate(now().plusMinutes(15L)))
                .signWith(keyGenerator.generateKey())
                .compact();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
