package com.iupi.fintech.services.imp;

import com.auth0.jwt.interfaces.DecodedJWT;
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

    // METODO OBSOLETO, FALTA CORREGIR CODIGO EN EL CONTROLADOR PARA ELIMINAR
    public String autentificarUser(String accessToken) throws Exception {

        String token;
        try {
            //--------------- Primero validamos el token que recibimos tras loguearnos
            DecodedJWT signedJWT = jwtService.validateToken(accessToken);
            System.out.println("verifico token en el authserveice");
            //------------- Obtenemos los datos de usuario del accesstoken
            UserInfo userInfo = getDataFromAccessToken(accessToken);
            System.out.println(" obtubo el user info: " + userInfo.toString());
            //--------- Obtenemos el usuario si esta en la base de datos, sino pasara a registrarlo
            User userResponse = userService.findByEmail(userInfo.getEmail());
            System.out.println(" user response es: " + userResponse.getAuth0Id());
            System.out.println("Se decodifico y valido el accesstoken");
            if (userResponse == null) {

                User newUser = userRepository.save(userMapper.toEntitySave(userInfo));
                System.out.println("new user: " + newUser.toString());
                String tokennn= jwtService.generateCustomToken(newUser, userInfo);
                Boolean tr= jwtService.validateCustomToken(tokennn);
                System.out.println("tokennn: " + tr);
                return new JwtToken(jwtService.generateCustomToken(newUser, userInfo)).toString();
            }
            String tokennn= jwtService.generateCustomToken(userResponse, userInfo);
            Boolean tr= jwtService.validateCustomToken(tokennn);
            System.out.println("tokennn: " + tr);
            return new JwtToken(jwtService.generateCustomToken(userResponse, userInfo)).toString();
        } catch (Exception e) {
            throw new Exception("error en el authService : "+ e.getMessage());
        }
    }

    public String generateCustomToken(String accessToken) throws Exception {
        try {

            //------------- Obtenemos los datos de usuario del accesstoken
            UserInfo userInfo = getDataFromAccessToken(accessToken);

            //--------- Obtenemos el usuario si esta en la base de datos, sino pasara a registrarlo
            User userResponse = userService.findByEmail(userInfo.getEmail());

            if (userResponse == null) {

                User newUser = userRepository.save(userMapper.toEntitySave(userInfo));
                String customToken= jwtService.generateCustomToken(newUser, userInfo);

                return new JwtToken(customToken).jwtToken();
            }
            String token= jwtService.generateCustomToken(userResponse, userInfo);

            return new JwtToken(token).jwtToken();
        } catch (Exception e) {
            throw new Exception("error al crear el custom Token : "+ e.getMessage());
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


}
