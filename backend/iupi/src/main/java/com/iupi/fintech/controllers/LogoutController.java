package com.iupi.fintech.controllers;

import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.models.User;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.imp.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/logout")

public class LogoutController implements LogoutSuccessHandler {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {

        try {

            invalidateSession(req);
            System.out.println("esta por desloguearse");
            String returnTo = req.getScheme() + "://" + req.getServerName();
            if ((req.getScheme().equals("http") && req.getServerPort() != 80) || (req.getScheme().equals("https") && req.getServerPort() != 443)) {
                returnTo += ":" + req.getServerPort();
            }
            returnTo += "/";
            String logoutUrl = authService.logout(returnTo);
            setFechaUltimaConexionIntoUser(authentication);
            res.sendRedirect(logoutUrl);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private void invalidateSession(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
    }

    // Busca al usuario de la sesion y le setea la ultima fecha de conexion
    private void setFechaUltimaConexionIntoUser(Authentication authentication) {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        Map<String, Object> attributes = token.getPrincipal().getAttributes();

        // Extraer el email de los atributos
        String email = (String) attributes.get("email");

        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setFechaUltimaConexion(LocalDate.now());
            userRepository.save(user);
        }
    }
}
