package com.iupi.fintech.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
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


    public String generateCustomToken(String token) {
        return null;
    }

    public String generateToken(User user, UserInfo userInfo) throws NoSuchAlgorithmException, NoSuchProviderException {

        System.out.println("ENTRO AL GENERATE TOKEN DEL JWT SERVICE");
        // Se obtiene la instancia de las 2 claves
        System.out.println(" clientPublic: " + clientPublic);
        System.out.println("clientSecret: " + clientSecret);

        KeyPair keyPair = KeyPairGenerator.getInstance(clientPublic).generateKeyPair();
        System.out.println("obtuvo el keirPair");
        System.out.println("keyPair: " + keyPair.getPublic());
        System.out.println("keyPair: " + keyPair.getPrivate());
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        try {
          //  Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) clientPublic, (RSAPrivateKey) clientSecret);
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            return JWT.create()
                    .withIssuer(domain)
                    .withSubject(userInfo.getSub())
                    .withAudience(audience)
                    .withClaim("id", user.getUsuarioId())
                    .withClaim("auth0Id", user.getAuth0Id())
                    .withClaim("email", user.getEmail())
                    .withClaim("name", "alejandir")//user.getNombre())
                    .withClaim("perfil", 5)//String.valueOf(user.getPerfiles().getPerfilId()))
                    .withIssuedAt(new Date())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al crear el token");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        Jwt jwt = decode(token);
        return Date.from(jwt.getExpiresAt());
    }

    public String getUsernameFromToken(String token) {

        if (token == null) {
            throw new RuntimeException("Token nulo");
        }
        DecodedJWT verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            verifier = JWT.require(algorithm)
                    .withIssuer(domain)
                    .withAudience(audience)
                    .build()
                    .verify(token);

            if (verifier.getSubject() == null) {
                throw new RuntimeException("Verificador invalido");
            }
            return String.valueOf(verifier.getClaim("email").as(String.class));
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al verificar el token");
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String email = getUsernameFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Jwt decode(String token) throws JwtException {
        try {
            token = token.replace("Bearer ", "");
            token.trim();
//            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            // Se obtiene la instancia de las 2 claves
            KeyPair keyPair = KeyPairGenerator.getInstance(clientPublic, clientSecret).generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

                Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);


      //      Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) clientPublic, (RSAPrivateKey) clientSecret);
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
