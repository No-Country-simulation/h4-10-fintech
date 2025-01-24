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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

//@Component
public class CustomJwtDecoderService{ //implements JwtDecoder {


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


//
//    public CustomJwtDecoderService() {}
//
//    @Override
//    public Jwt decode(String token) throws JwtException {
//        try {
//            token = token.replace("Bearer ", "");
//            token.trim();
////            Algorithm algorithm = Algorithm.HMAC256(secretKey);
//            System.out.println("ENTRO AL CUSTOM TOKEN DECODER DEL CSECURITY");
//            // Se obtiene la instancia de las 2 claves
//            KeyPair keyPair = KeyPairGenerator.getInstance(clientPublic, clientSecret).generateKeyPair();
//            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
//
//
//
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer(domain.trim())
//                    .withAudience(audience.trim())
//                    .build();
//
//
//            DecodedJWT decodedJWT = verifier.verify(token);
//
//            // Extraer Claims
//            Map<String, Object> claims = new HashMap<>();
//            decodedJWT.getClaims().forEach((k, v) -> claims.put(k, v.as(Object.class)));
//
//            // Construir el objeto Jwt que Spring Security utiliza
//            return Jwt.withTokenValue(token)
//                    .headers(h -> h.put("alg", decodedJWT.getAlgorithm()))
//                    .claims(c -> c.putAll(claims))
//                    .issuedAt(decodedJWT.getIssuedAt().toInstant())
//                    .expiresAt(decodedJWT.getExpiresAt().toInstant())
//                    .build();
//
//        } catch (Exception e) {
//            throw new JwtException("Token inválido", e);
//        }
//    }
}
