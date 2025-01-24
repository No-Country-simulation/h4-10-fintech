package com.iupi.fintech.controllers;

import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.services.imp.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    private final AuthService authService;

    public AuthController(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService, AuthService authService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
        this.authService = authService;
    }

    @GetMapping("/access-token")
    public ResponseEntity<?> getAccessToken(OAuth2AuthenticationToken auth) throws AuthenticationServiceException {

        try {
            // Cargar el cliente autorizado
            OAuth2AuthorizedClient client = authorizedClientService
                    .loadAuthorizedClient(
                            auth.getAuthorizedClientRegistrationId(),
                            auth.getName()
                    );

            if (client == null || client.getAccessToken() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("No se pudo obtener el token de acceso.");
            }

            // Obtenemos el token de acceso
            String accessToken = client.getAccessToken().getTokenValue();

            OAuth2User principal = auth.getPrincipal();
            String email = (String) principal.getAttributes().get("email");
            if (email == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo extraer el email del usuario.");
            }

            String customToken = String.valueOf(authService.autentificarUser(accessToken));
            if (customToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("No se pudo autenticar al usuario.");
            }
            return ResponseEntity.ok(Map.of("access_token", customToken));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Ocurrió un error al procesar la solicitud.", "details", ex.getMessage()));
        }

    }
    @GetMapping("/generate-custom-token")
    public ResponseEntity<?> generateCustomToken(@RequestParam String access_token) throws Exception {


        try {
            String customToken = authService.generateCustomToken(access_token);
            return ResponseEntity.ok(new JwtToken(customToken));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: "+ e.getMessage());
        }
    }


    @GetMapping("/logout")
    @Operation(summary = "Logout manual del usuario")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Realiza el logout local
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        // Redirige a Auth0 para el logout
        String logoutUrl = "https://dev-byesylnv0qhe4lwt.us.auth0.com/v2/logout";
        System.out.println("Logout URL: " + logoutUrl);

        // Redirige al usuario a Auth0 para el logout
        response.sendRedirect(logoutUrl);

        return ResponseEntity.status(302).header("Location", logoutUrl).build();
    }





}
