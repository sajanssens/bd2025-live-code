package com.infosupport.jeedemo.api.util;

import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKey;

@ApplicationScoped
public class KeyGeneratorSimple {

    public SecretKey generateKey() {
        byte[] key = "simplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeysimplekeyoftochnietsimpel".getBytes();
        return Keys.hmacShaKeyFor(key);
    }
}
