package com.iupi.fintech.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JwtVerifierConfig {

    @Value("${AUTH0_ISSUER}")
    private String issuer;
    @Value("${auth0.audience}")
    private String audience;

    @Bean
    public JWTVerifier jwtVerifier() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Leer y procesar la clave pública
        String publicKeyPath = "public_key.pem";
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(publicKeyPath)))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(spec);

        // Crear y devolver el verificador
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .withAudience(audience)
                .build();
    }
}

