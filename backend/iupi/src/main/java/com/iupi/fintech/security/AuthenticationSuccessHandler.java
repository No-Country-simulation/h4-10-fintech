package com.iupi.fintech.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.iupi.fintech.config.jwt.Auth0TokenValidator;
import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.exceptions.ApplicationException;
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

    private final Auth0TokenValidator auth0TokenValidator;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    public AuthenticationSuccessHandler( Auth0TokenValidator auth0TokenValidator, OAuth2AuthorizedClientService authorizedClientService) {
        this.auth0TokenValidator = auth0TokenValidator;
        this.authorizedClientService = authorizedClientService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Extraer el OAuth2AuthenticationToken
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        // Acceder al token de acceso desde el cliente autorizado
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                token.getAuthorizedClientRegistrationId(), token.getName());
            // Se Extrae el access token
        String accessToken = authorizedClient.getAccessToken().getTokenValue();

        try {
            System.out.println("entro a validad el token");
               DecodedJWT jwt = auth0TokenValidator.validateToken(accessToken);
               if(jwt == null) {
                   throw new ApplicationException("Error al validar el token");
               }

            response.sendRedirect("/api/auth/generate-custom-token?access_token=" + accessToken);
        }catch (Exception e) {
            throw new ApplicationException("Error al validar el token"+ e.getMessage());
        }
    }
}