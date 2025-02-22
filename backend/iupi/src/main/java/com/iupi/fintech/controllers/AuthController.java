package com.iupi.fintech.controllers;

import com.iupi.fintech.config.jwt.JwtToken;
import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.services.imp.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Value("${AUTH0_ISSUER}")
    private String issuer;
    @Value("${SECRET_KEY}")
    private String secretKey;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final AuthService authService;
    private ResponseEntity<JwtToken> jwtTokenResponseEntity;

    @Autowired
    public AuthController(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService, AuthService authService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
        this.authService = authService;
    }

    @GetMapping("/generate-custom-token")
    @Operation(summary = "Genera un token personalizado a partir del Token emitido de Auth0")
    public ResponseEntity<ApiResponseDto<String>> generateCustomToken(@RequestParam String access_token, HttpServletResponse response) throws Exception {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!authentication.isAuthenticated()) {
                return new ResponseEntity<>(new ApiResponseDto<>(false, "Usuario no autenticado", null), HttpStatus.UNAUTHORIZED);
            }

            // Genera el token personalizado
            String customToken = authService.generateCustomToken(access_token);

            // Enviar el token como query param al frontend
//            String redirectUrl = "https://iupi-fintech.vercel.app/dashboard?loggedIn=true&token=" + customToken;
//            response.sendRedirect(redirectUrl);

            return ResponseEntity.ok(new ApiResponseDto<>(true, "Token personalizado generado", customToken));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/is-authenticated")
//    public ResponseEntity<Boolean> isAuthenticated(HttpSession session) {
//        String customToken = (String) session.getAttribute("customToken");
//        return ResponseEntity.ok(customToken != null);
//    }

    @GetMapping("/logout")
    @Operation(summary = "Logout manual del usuario sin envio de token.")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Realiza el logout local
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
        System.out.println("esta en el logout manual");
        // Redirige a Auth0 para el logout
        String logoutUrl = issuer + "v2/logout";
        System.out.println("url de logout manual: " + logoutUrl);

        // Redirige al usuario a Auth0 para el logout
        response.sendRedirect(logoutUrl);
        System.out.println(" se supone que redirigio");

        return ResponseEntity.status(302).header("Location", logoutUrl).build();
    }

    // ENDPOINTS DE PRUEBA
    @GetMapping("/test-token")
    public ResponseEntity<String> testToken(@RequestHeader("Authorization") String token) {
        System.out.println("entro al test token");
        return ResponseEntity.ok("Token recibido: " + token);
    }

    @GetMapping("/test-auth")
    public ResponseEntity<?> testAuth(Authentication authentication) {
        System.out.println("entro al test auth");
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        }
        return ResponseEntity.ok("Usuario autenticado: " + authentication.getName());
    }
    @GetMapping("/test-authh")
    public ResponseEntity<String> testAuth(OidcUser authentication) {
        System.out.println("entro al test auth");
        authentication.getAttribute("email");
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        }
        return ResponseEntity.ok("Usuario autenticado: " + authentication.getName());
    }

    @GetMapping("/cualquiera")
    @Operation(summary = "Obtiene al usuario actual")
    public ResponseEntity<String> getCualquiera() {

        return new ResponseEntity<>("ha pasado la prueba", HttpStatus.OK);

    }

    @GetMapping("/getUser")
    public ResponseEntity<Iterable<UserResponseDto>> laist () {

       Iterable<UserResponseDto> a=authService.getMisUsers();

        return  ResponseEntity.ok(a);
    }




}
