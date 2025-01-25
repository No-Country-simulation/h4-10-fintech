package com.iupi.fintech.services.imp;

import com.iupi.fintech.config.jwt.JwtService;
import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.mappers.user.UserMapper;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class AuthService {


    @Value("${auth0.issuerInfo}")
    private String userInfoUrl;
    @Value("${auth0.clientId}")
    private String clientId;
    @Value("${auth0.domain}")
    private String domain;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public AuthService(UserRepository userRepository, UserMapper userMapper, JwtService jwtService, UserService userService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public String generateCustomToken(String accessToken) {
        try {

            //------------- Obtenemos los datos de usuario del accesstoken
            UserInfo userInfo = getDataFromAccessToken(accessToken);

            //--------- Obtenemos el usuario si esta en la base de datos, sino pasara a registrarlo
            User userResponse = userService.findByEmail(userInfo.getEmail());

            if (userResponse == null) {

                User newUser = userRepository.save(userMapper.toEntitySave(userInfo));
                String customToken = jwtService.generateCustomToken(newUser, userInfo);

                return new JwtToken(customToken).jwtToken();
            }
            String token = jwtService.generateCustomToken(userResponse, userInfo);

            return new JwtToken(token).jwtToken();
        } catch (ApplicationException | IOException | NoSuchAlgorithmException |
                 InvalidKeySpecException e) {
            throw new ApplicationException("error al crear el custom Token : " + e.getMessage());
        }
    }

    /**
     * Metodo para obtener los datos del usuario del AccessToken
     * El metodo hace una peticion a la url de UserInfo para extraer los datos
     **/
    public UserInfo getDataFromAccessToken(String accessToken) {

        WebClient webClient = WebClient
                .builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri(userInfoUrl)
                .headers(header -> header.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(UserInfo.class)
                .doOnNext(user -> System.out.println("user info: " + user.toString()))
                .doOnError(e -> System.out.println("Error: " + e.getMessage()))
                .block();
    }

    public String logout(String returnTo) {

        return String.format("https://%s/v2/logout?client_id=%s&returnTo=%s",
                domain, clientId, returnTo);
    }

    public String getUsernameFromToken(String token) {

        return jwtService.getEmailFromToken(token);
    }

    public boolean validateTokenLocal(String token, UserDetails userDetails) {
        try {
            String email = jwtService.getEmailFromToken(token);

            return (email.equals(userDetails.getUsername()) && !jwtService.isTokenExpired(token));
        } catch (ApplicationException e) {
            throw new ApplicationException(e.getMessage() + " ha quedadp pegado en el validate local");
        }
    }


}
