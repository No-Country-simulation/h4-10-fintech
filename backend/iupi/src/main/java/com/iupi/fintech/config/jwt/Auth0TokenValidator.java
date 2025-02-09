package com.iupi.fintech.config.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.iupi.fintech.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

@Service
public class Auth0TokenValidator implements JwkProvider {

    @Value("${AUTH0_ISSUER}")
    private String issuer;

    JwkProvider jwkProvider = new JwkProviderBuilder("https://dev-byesylnv0qhe4lwt.us.auth0.com/")
            .cached(10, 24, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build();

    @Override
    public Jwk get(String kid) throws JwkException {
        return jwkProvider.get(kid);
    }


    public DecodedJWT validateToken(String token) {
        try {
            // Decodificamos Access Token
            DecodedJWT jwt = JWT.decode(token);
// obtenemos su KID
            String kid = jwt.getKeyId().trim();

            // Obtener la clave pública
            Jwk jwk = jwkProvider.get(kid);

            RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();

            // Verificar el token con el algoritmo y emisor
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            return verifier.verify(token);
        } catch (ApplicationException | JwkException e) {
            throw new ApplicationException("Error al validar el token: " + e.getMessage());
        }
    }

}

