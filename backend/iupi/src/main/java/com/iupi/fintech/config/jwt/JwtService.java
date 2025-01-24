package com.iupi.fintech.config.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.models.User;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.nimbusds.openid.connect.sdk.federation.entities.EntityStatement.sign;

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
    @Autowired
    private JwkProvider jwkProvider;


    public String generateCustomToken( User user, UserInfo userInfo) throws JwkException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        String privateKeyPath = "private_key.pem";
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(privateKeyPath)))
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);
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
            throw new RuntimeException("Error al crear el token: "+ e.getMessage());
        }

    }

    public Boolean validateCustomToken(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
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

        // Crear el verificador
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
        // Verificar el token
         if (verifier.verify(token) == null) {
             return false;
        }
         return true;
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        Jwt jwt = (Jwt) JWT.decode(token);
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

    public boolean validateTokenLocal(String token, UserDetails userDetails) {
        String email = getUsernameFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     *  MEtodo para validar el token.
     *  Lo decodifica primero, obtiene su Kid y lo compara con el jwkProvider que tiene la llave publica
     * @param token
     * @return DecodedJWT
     */

    // En la proxima actualizacion lo elimino de aqui ya que esta en Auth0TokenValidator
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
