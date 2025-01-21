package com.iupi.fintech.services.imp;

import com.iupi.fintech.config.jwt.JwtService;
import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.dtos.user.UserInfo;
import com.iupi.fintech.mappers.user.UserMapper;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class AuthService {


    @Value("${auth0.issuerInfo}")
    private static String userInfoUrl;
    @Value("${auth0.clientId}")
    private static String clientId;
    @Value("${auth0.domain}")
    private static String domain;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, UserService userService, UserMapper userMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    public String autentificarUser(String accessToken) throws Exception {

        String token;
        try {
            UserInfo userInfo = getUsernameFromAccessToken(accessToken);
            User userResponse = userRepository.findByEmail(userInfo.getEmail());

            if (userResponse == null) {
                User newUser = userRepository.save(userMapper.toEntitySave(userInfo));

                return new JwtToken(jwtService.generateToken(newUser, userInfo));
            }

            return new JwtToken((jwtService.generateToken(userResponse, userInfo)));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public UserInfo getUsernameFromAccessToken(String accessToken) {

        WebClient webClient = WebClient
                .builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri(userInfoUrl)
                .headers(header -> header.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(UserInfo.class)
                .doOnNext(user -> System.out.println("user en el getUsernameFromAccessToken: " + user))
                .doOnError(e -> System.out.println("Error: " + e.getMessage()))
                .block();
    }

public String logout(String returnTo) {

    return String.format("https://%s/v2/logout?client_id=%s&returnTo=%s",
            domain, clientId, returnTo);
}


}
