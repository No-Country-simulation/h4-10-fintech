package com.iupi.fintech.config.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
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

    @Value("${AUTH0_ISSUER}")
    private String issuer;
    @Autowired
    private JwkProvider jwkProvider;

//    @Autowired
//    public Auth0TokenValidator(String issuer, JwkProvider jwkProvider) {
//        this.issuer = issuer;
//        this.jwkProvider = jwkProvider;
//    }


    public DecodedJWT validateToken(String token) {
        try {
            System.out.println("hablo desde el decode del validateToken: " + token);

            DecodedJWT jwt = JWT.decode(token);
            System.out.println("el jwt decodificado con claim sub es: " + jwt.getClaim("sub"));
            String kid = jwt.getKeyId().trim();

            System.out.println("kid de jwt decodificado: " + kid);

            // Obtener la clave pública
            Jwk jwk = jwkProvider.get(kid);

            RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();

            // Verificar el token con el algoritmo y emisor
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("https://dev-byesylnv0qhe4lwt.us.auth0.com/")
                    .build();
            System.out.println("Verifoc el token");
            return verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el token: " + e.getMessage(), e);
        }
    }

}

