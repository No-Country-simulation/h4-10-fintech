package com.iupi.fintech.controllers;

import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.services.imp.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Value("${AUTH0_ISSUER}")
    private String issuer;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final AuthService authService;

    @Autowired
    public AuthController(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService, AuthService authService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
        this.authService = authService;
    }

    @GetMapping("/generate-custom-token")
    @Operation(summary = "Genera un token personalizado a partir del Token emitido de Auth0")
    public ResponseEntity<JwtToken> generateCustomToken(@RequestParam String access_token, HttpSession session) throws Exception {

        try {
            String customToken = authService.generateCustomToken(access_token);
            session.setAttribute("customToken", customToken);
            return ResponseEntity.ok(new JwtToken(customToken));
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logout")
    @Operation(summary = "Logout manual del usuario")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Realiza el logout local
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        // Redirige a Auth0 para el logout
        String logoutUrl = issuer + "v2/logout";

        // Redirige al usuario a Auth0 para el logout
        response.sendRedirect(logoutUrl);

        return ResponseEntity.status(302).header("Location", logoutUrl).build();
    }





}
