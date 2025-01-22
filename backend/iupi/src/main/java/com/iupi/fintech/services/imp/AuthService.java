package com.iupi.fintech.services.imp;

import com.iupi.fintech.config.jwt.JwtService;
import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.mappers.user.UserMapper;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public String autentificarUser(String accessToken) throws Exception {

        String token;
        try {
            System.out.println(" entro a autentificar al usuario en el auth Service");
            UserInfo userInfo = getUsernameFromAccessToken(accessToken);
            System.out.println("user info: "+userInfo.toString());
            User userResponse = userService.findByEmail(userInfo.getEmail());

            if (userResponse == null) {

                User newUser = userRepository.save(userMapper.toEntitySave(userInfo));
                System.out.println("new user: "+newUser.toString());

                return new JwtToken(jwtService.generateToken(newUser, userInfo)).toString();
            }

            return new JwtToken((jwtService.generateToken(userResponse, userInfo))).toString();
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
                .doOnNext(user -> System.out.println("user en el getUsernameFromAccessToken: " + user.toString()))
                .doOnError(e -> System.out.println("Error: " + e.getMessage()))
                .block();
    }

    public String logout(String returnTo) {

        return String.format("https://%s/v2/logout?client_id=%s&returnTo=%s",
                domain, clientId, returnTo);
    }


}
