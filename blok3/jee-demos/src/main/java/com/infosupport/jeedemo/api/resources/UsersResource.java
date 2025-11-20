package com.infosupport.jeedemo.api.resources;

import com.infosupport.jeedemo.api.util.KeyGenerator;
import com.infosupport.jeedemo.api.util.filter.NotAuthorized;
import com.infosupport.jeedemo.domain.User;
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

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.infosupport.jeedemo.api.util.PasswordUtils.digest;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.time.LocalDateTime.now;

@Path("users")
public class UsersResource {

    @Inject
    private UserRepo repo;

    @Inject
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @NotAuthorized
    public User register(User u) {
        u.setPassword(digest(u.getPassword()));
        return repo.create(u);
    }

    @POST @Path("login")
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @NotAuthorized
    public User login(User input) {
        try {
            String username = input.getUsername();
            String password = input.getPassword();

            User user = repo.findByUsernameAndPassword(username, password);

            String jwt = issueToken(user);
            user.setToken(jwt);

            return user;
        } catch (NoResultException e) {
            throw new NotAuthorizedException("User " + input + " is not authorized.");
        }
    }

    private String issueToken(User user) {
        Key key = keyGenerator.generateKey();
        String jwt = Jwts.builder()
                .issuer(uriInfo.getAbsolutePath().toString())
                .subject("baseball-quiz")
                .claim("username", user.getUsername())
                .claim("roles", user.getRoles()) // roles toevoegen
                .issuedAt(new Date())
                .expiration(toDate(now().plusMinutes(15L)))
                .signWith(key)
                .compact();
        return jwt;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
