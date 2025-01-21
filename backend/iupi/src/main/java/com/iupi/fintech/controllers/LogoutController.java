package com.iupi.fintech.controllers;

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

@RestController
@RequestMapping("/logout")
public class LogoutController implements LogoutSuccessHandler {

    @Autowired
    private AuthService authService;

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {

        try {
            invalidateSession(req);
            String returnTo = req.getScheme() + "://" + req.getServerName();
            if ((req.getScheme().equals("http") && req.getServerPort() != 80) || (req.getScheme().equals("https") && req.getServerPort() != 443)) {
                returnTo += ":" + req.getServerPort();
            }
            returnTo += "/";
            String logoutUrl = authService.logout(returnTo);

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
}
