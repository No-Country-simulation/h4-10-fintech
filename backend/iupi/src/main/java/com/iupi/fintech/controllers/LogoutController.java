package com.iupi.fintech.controllers;

import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;;
import com.iupi.fintech.services.imp.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/logout")

public class LogoutController implements LogoutSuccessHandler {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {

        String token = req.getHeader("Authorization").replace("Bearer ", "");

        System.out.println("token= " + token);
      String username = authService.getUsernameFromToken(token);

        try {

            String returnTo = req.getScheme() + "://" + req.getServerName();
            if ((req.getScheme().equals("http") && req.getServerPort() != 80) || (req.getScheme().equals("https") && req.getServerPort() != 443)) {
                returnTo += ":" + req.getServerPort();
            }
            returnTo += "/";
            String logoutUrl = authService.logout(returnTo);
            System.out.println("esta por setear la ultima fecha de conexion");
           setFechaUltimaConexionIntoUser(username);
            System.out.println("seteo la ultima fecha dde conexion");
            System.out.println("logoutUrl: " + logoutUrl);
            res.sendRedirect(logoutUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Este medodo invalida la sesion si trabajasemos con ella
    private void invalidateSession(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
    }

    // Busca al usuario en el token y le setea la ultima fecha de conexion
    private void setFechaUltimaConexionIntoUser(String email) {

//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        Map<String, Object> attributes = token.getPrincipal().getAttributes();
//
//        // Extraer el email de los atributos
//        String email = (String) attributes.get("email");

            User user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                user.setFechaUltimaConexion(LocalDate.now());
                userRepository.save(user);
            }
    }
}
