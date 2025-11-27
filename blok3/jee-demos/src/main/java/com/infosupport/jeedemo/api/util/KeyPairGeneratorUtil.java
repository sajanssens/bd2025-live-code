package com.infosupport.jeedemo.api.util;

import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Utility to generate RSA key pair for JWT signing and verification.
 * Run this main method to generate new matching private-key.pem and public-key.pem files.
 */
public class KeyPairGeneratorUtil {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // Generate RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the keys
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();

        // Base64 encode
        String privateKeyPEM = Base64.getEncoder().encodeToString(privateKeyBytes);
        String publicKeyPEM = Base64.getEncoder().encodeToString(publicKeyBytes);

        // Write private key to file
        String privateKeyPath = "blok3/jee-demos/src/main/resources/META-INF/private-key.pem";
        try (FileWriter writer = new FileWriter(privateKeyPath)) {
            writer.write("-----BEGIN PRIVATE KEY-----\n");
            writer.write(formatPEM(privateKeyPEM));
            writer.write("\n-----END PRIVATE KEY-----\n");
        }

        // Write public key to file
        String publicKeyPath = "blok3/jee-demos/src/main/resources/META-INF/public-key.pem";
        try (FileWriter writer = new FileWriter(publicKeyPath)) {
            writer.write("-----BEGIN PUBLIC KEY-----\n");
            writer.write(formatPEM(publicKeyPEM));
            writer.write("\n-----END PUBLIC KEY-----\n");
        }

        System.out.println("✅ Generated new RSA key pair!");
        System.out.println("Private key: " + privateKeyPath);
        System.out.println("Public key:  " + publicKeyPath);
    }

    private static String formatPEM(String base64) {
        StringBuilder formatted = new StringBuilder();
        int index = 0;
        while (index < base64.length()) {
            formatted.append(base64, index, Math.min(index + 64, base64.length()));
            formatted.append("\n");
            index += 64;
        }
        return formatted.toString().trim();
    }
}

