package com.iupi.fintech.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Service
public class CustomTokenGenerator {

    @Value("${Auth0_clientSecret}")
    private String clientSecret;
    @Value("${AUTH0_ISSUER}")
    private String issuer;
    @Value("${Auth0_domain}")
    private String domain;

    public String generateCustomToken(String subject, String audience, long expirationMillis) {
        try {
            // Convertir la clave privada PEM a un objeto PrivateKey
            String privateKeyContent = clientSecret;
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

            Algorithm algorithm = Algorithm.RSA256((RSAKeyProvider) privateKey);

            // Generar el token
            return JWT.create()
                    .withIssuer(domain)
                    .withSubject(subject)
                    .withAudience(audience)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationMillis))
                    .sign(algorithm);

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token: " + e.getMessage(), e);
        }
    }
}
