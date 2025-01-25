package com.iupi.fintech.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomJwtDecoderService implements JwtDecoder {


    // Codigo por revisar , sino desechar


    @Value("${SECRET_KEY}")
    private String secretKey;
    @Value("${auth0.domain}")
    private String domain;
    @Value("${auth0.clientSecret}")
    private String clientSecret;
    @Value("${auth0.audience}")
    private String audience;
    @Value("${AUTH0_JWKS_URL}")
    private String clientPublic;
    @Value("${AUTH0_ISSUER}")
    private String issuer;




    public CustomJwtDecoderService() {}

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            token = token.replace("Bearer ", "").trim();

            // Crear el verificador
            String publicKeyPath = "public_key.pem";
            String publicKeyContent = new String(Files.readAllBytes(Paths.get(publicKeyPath)))
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyContent);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(spec);

            // Crear el algoritmo con la clave pública
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(domain.trim())
                    .withAudience(audience.trim())
                    .build();


            DecodedJWT decodedJWT = verifier.verify(token);

            // Extraer Claims
            Map<String, Object> claims = new HashMap<>();
            decodedJWT.getClaims().forEach((k, v) -> claims.put(k, v.as(Object.class)));

            // Construir el objeto Jwt que Spring Security utiliza
            return Jwt.withTokenValue(token)
                    .headers(h -> h.put("alg", decodedJWT.getAlgorithm()))
                    .claims(c -> c.putAll(claims))
                    .issuedAt(decodedJWT.getIssuedAt().toInstant())
                    .expiresAt(decodedJWT.getExpiresAt().toInstant())
                    .build();

        } catch (Exception e) {
            throw new JwtException("Token inválido", e);
        }
    }
}
