package com.iupi.fintech.security;

import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.services.imp.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private final AuthService authService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    public AuthenticationSuccessHandler(AuthService authService, OAuth2AuthorizedClientService authorizedClientService) {
        this.authService = authService;
        this.authorizedClientService = authorizedClientService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Extraer el OAuth2AuthenticationToken
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        // Acceder al token de acceso desde el cliente autorizado
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                token.getAuthorizedClientRegistrationId(), token.getName());


        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        System.out.println("accessToken em eñ authenticationHandler: " + accessToken);
        try {
           // JwtToken tokenDTO = authService.autentificarUser(accessToken);
            JwtToken tokenDTO = new JwtToken(authService.autentificarUser(accessToken));

            System.out.println("token custom: " + tokenDTO.jwtToken());

            // Devolver el custom token como parte de la respuesta
            response.setContentType("application/json");
            response.getWriter().write("{\"custom_token\": \"" + tokenDTO.jwtToken() + "\"}");
            response.sendRedirect("/api/home");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}