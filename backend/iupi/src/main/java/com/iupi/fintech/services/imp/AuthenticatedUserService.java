package com.iupi.fintech.services.imp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

//    public OidcUser getAuthenticatedUsername() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        Object principal = authentication.getPrincipal();
//
//        if (principal instanceof OidcUser oidcUser) {
//            return oidcUser;
//        }
//        return null;
//    }
    public String getAuthenticatedUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if (authentication.isAuthenticated()){
           return authentication.getName();
       }
       return null;
    }
}