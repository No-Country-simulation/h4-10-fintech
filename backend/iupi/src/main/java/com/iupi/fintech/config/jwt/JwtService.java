package com.iupi.fintech.config.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.models.User;
import com.iupi.fintech.security.JwtVerifierConfig;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;


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
    @Value("${AUTH0_ISSUER}")
    private String issuer;
    @Value("${SECRET_KEY}")
    private String privateKey;

    private final JwtVerifierConfig jwtVerifier;

    public JwtService(JwtVerifierConfig jwtVerifierConfig) {
        this.jwtVerifier = jwtVerifierConfig;
    }

    public DecodedJWT decodeCustomToken(String token) {
        try {
            return jwtVerifier.jwtVerifier().verify(token);
        } catch (JWTVerificationException | IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ApplicationException("Token inválido: " + e.getMessage());
        }
    }

    public String generateCustomToken(User user, UserInfo userInfo) throws  IOException, NoSuchAlgorithmException, InvalidKeySpecException {

//        String privateKeyPath = "private_key.pem";
//        String privateKeyContent = new String(Files.readAllBytes(Paths.get(privateKeyPath)))
//                .replace("-----BEGIN PRIVATE KEY-----", "")
//                .replace("-----END PRIVATE KEY-----", "")
//                .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);

        try {
            Algorithm algorithm = Algorithm.RSA256(null, privateKey);

            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(userInfo.getSub())
                    .withAudience(audience)
                    .withClaim("email", user.getEmail())
                    .withClaim("name", user.getNombre())
                    .withClaim("id", user.getUsuarioId())
                    // .withClaim("perfil", user.getPerfiles().getPerfilId())
                    // .withClaim("auth0Id", userInfo.getSub())
                    .withIssuedAt(new Date())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new ApplicationException("Error al crear el token: " + e.getMessage());
        }

    }

    public Boolean validateCustomToken(String token)  {

        DecodedJWT verifier = decodeCustomToken(token);

        // Verificar el token
        return verifier != null;
    }

    public String getEmailFromToken(String token) {

        try {
            DecodedJWT verifier = decodeCustomToken(token);
            return verifier.getClaim("email").asString();
        } catch (ApplicationException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);

        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {

        DecodedJWT jwt = decodeCustomToken(token);
        return jwt.getExpiresAt();
    }
}
