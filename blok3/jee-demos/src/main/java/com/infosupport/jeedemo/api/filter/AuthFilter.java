package com.infosupport.jeedemo.api.filter;

import com.infosupport.jeedemo.api.util.KeyGeneratorSimple;
import io.jsonwebtoken.Jwts;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;

import javax.crypto.SecretKey;

import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;
import static jakarta.ws.rs.core.Response.status;

// @Provider // using microprofile JWT instead of this manual filter
// @Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private KeyGeneratorSimple keyGenerator;

    @Override
    public void filter(ContainerRequestContext req) {
        if (resourceInfo.getResourceMethod().isAnnotationPresent(NoAuthorizationNeeded.class)) {
            return;
        }

        // Get the HTTP Authorization header from the request
        String authorizationHeader = req.getHeaderString(AUTHORIZATION);
        // log.info("#### authorizationHeader : " + authorizationHeader);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            // log.warn("#### invalid authorizationHeader : " + authorizationHeader);
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        try {
            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();
            // Validate the token
            SecretKey key = keyGenerator.generateKey();
            Jwts.parser().verifyWith(key).build().parse(token);
        } catch (Exception e) {
            req.abortWith(status(UNAUTHORIZED).build());
        }
    }
}
