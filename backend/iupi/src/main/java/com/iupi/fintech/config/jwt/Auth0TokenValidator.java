package com.iupi.fintech.config.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

    @Service
    public class Auth0TokenValidator {

        @Value("${AUTH0_JWKS_URL}")
        private String jwksUrl;
        @Value("${AUTH0_ISSUER}")
        private String issuer;

        @Autowired
        private final JwkProvider jwkProvider;

        public Auth0TokenValidator(String jwksUrl, String issuer) {
            this.jwkProvider = new JwkProviderBuilder(jwksUrl)
                    .cached(10, 24, TimeUnit.HOURS)
                    .rateLimited(10, 1, TimeUnit.MINUTES)
                    .build();
            this.jwksUrl = jwksUrl;
            this.issuer = issuer;
        }

        public DecodedJWT validateToken(String token) {
            try {
                DecodedJWT jwt = JWT.decode(token);
                String kid = jwt.getKeyId();

                // Obtener la clave pública
                Jwk jwk = jwkProvider.get(kid);
                RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();

                // Verificar el token con el algoritmo y emisor
                Algorithm algorithm = Algorithm.RSA256(publicKey, null);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(issuer)
                        .build();

                return verifier.verify(token);
            } catch (Exception e) {
                throw new RuntimeException("Error al validar el token: " + e.getMessage(), e);
            }
        }
    }

