package com.infosupport.jeedemo.api.resources;

import com.infosupport.jeedemo.domain.TokenDto;
import com.infosupport.jeedemo.domain.User;
import com.infosupport.jeedemo.domain.UserDto;
import com.infosupport.jeedemo.domain.UserRepo;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.util.KeyUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.time.temporal.ChronoUnit.MINUTES;
import static org.eclipse.microprofile.config.ConfigProvider.getConfig;

@Path("users")
public class UsersResource {

    @Inject
    private UserRepo repo;

    @POST
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @PermitAll
    public User register(User u) {
        User user = repo.create(u);
        user.setPassword("**************");
        return user;
    }

    @POST @Path("login")
    @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON)
    @PermitAll
    public TokenDto login(UserDto input) throws GeneralSecurityException, IOException {
        String username = input.username();
        String password = input.password();
        var user = repo.findByUsernameAndPassword(username, password);
        return new TokenDto(issueToken(user));
    }

    static String issueToken(User user) throws GeneralSecurityException, IOException {
        return Jwt.issuer(getConfig().getValue("mp.jwt.verify.issuer", String.class))
                .subject("beers")
                .upn(user.getUsername())
                .groups(user.getRoles())
                .claim("username", user.getUsername())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30, MINUTES))
                .sign(KeyUtils.readPrivateKey(getConfig().getValue("jwt.sign.key.location", String.class)));
    }
}
