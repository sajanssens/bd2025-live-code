package com.infosupport.jeedemo.api.util;

import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKey;

@ApplicationScoped
public class KeyGeneratorSimple implements KeyGenerator {

    @Override
    public SecretKey generateKey() {
        byte[] key = "simplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeyoftochnietsimpel".getBytes();
        return Keys.hmacShaKeyFor(key);
    }
}
