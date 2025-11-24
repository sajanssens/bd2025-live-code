package com.infosupport.jeedemo.api.resources;

import com.infosupport.jeedemo.domain.User;
import com.infosupport.jeedemo.domain.UserRepo;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.util.KeyUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;

import static com.infosupport.jeedemo.api.util.PasswordUtils.digest;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.time.temporal.ChronoUnit.MINUTES;
import static org.eclipse.microprofile.config.ConfigProvider.getConfig;

@Path("users/mpjwt")
public class UsersMpJwtResource {

    @Inject
    private UserRepo repo;

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @PermitAll
    public User register(User u) {
        u.setPassword(digest(u.getPassword()));
        return repo.create(u);
    }

    @POST @Path("login")
    @Consumes(APPLICATION_JSON)
    @PermitAll
    public String login(User input) throws GeneralSecurityException, IOException {
        String username = input.getUsername();
        String password = input.getPassword();
        var user = repo.findByUsernameAndPassword(username, password);
        return issueToken(user);
    }

    static String issueToken(User user) throws GeneralSecurityException, IOException {
        return Jwt.issuer("infosupport")
                .subject("movies")
                .upn(user.getUsername())
                .groups(user.getRoles())
                .claim("username", user.getUsername())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30, MINUTES))
                .sign(KeyUtils.readPrivateKey(getConfig().getValue("jwt.sign.key.location", String.class)));
    }
}
