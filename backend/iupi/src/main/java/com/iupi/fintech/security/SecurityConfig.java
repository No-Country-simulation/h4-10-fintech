package com.iupi.fintech.security;

import com.iupi.fintech.config.jwt.CustomJwtDecoderService;
import com.iupi.fintech.controllers.LogoutController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${auth0.domain}")
    private String domain;
    @Value("${auth0.clientId}")
    private String clientId;
    @Value("${auth0.clientSecret}")
    private String clientSecret;
    @Value("${AUTH0_AUDIENCE}")
    private String audience;
    @Value("${AUTH0_USERINFO_URL}")
    private String userInfoUrl;
    @Value("${AUTH0_TOKEN_URL}")
    private String tokenUrl;

    @Value("${AUTH0_AUTHORIZATION_URL}")
    private String authorizationUrl;
    @Value("${AUTH0_CALLBACK_URL}")
    private String callbackUrl;
    @Value("${AUTH0_JWKS_URL}")
    private String jwksUrl;

    private final CustomJwtDecoderService customJwtDecoder;
    private final SecurityFilter securityFilter;

    @Autowired
    public SecurityConfig(SecurityFilter securityFilter, CustomJwtDecoderService customJwtDecoder) {
        this.customJwtDecoder = customJwtDecoder;
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Lazy AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        //Auth Controller
                        .requestMatchers("/api/auth/access-token**").permitAll()
                        .requestMatchers("/api/home**").authenticated()
                        .requestMatchers("/logout**").authenticated()
                        // User Controller
                        .requestMatchers(HttpMethod.GET, "/api/user**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/user/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/user/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/user/**").authenticated()
                        //Perfil Controller
                        .requestMatchers(HttpMethod.GET, "/api/perfil/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/perfil/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/perfil/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/perfil/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/perfil/**").authenticated()

                        //Swagger
                        .requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                //Login a traves de Auth0
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(authenticationSuccessHandler)
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcUserService()))
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwtAuthenticationProvider())
                )
                .addFilterBefore(securityFilter, OAuth2LoginAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                );
        return http.build();
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider(new CustomJwtDecoderService());
        provider.setJwtAuthenticationConverter(jwtAuthenticationConverter());
        return provider;
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutController();
    }

    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserService() {

            @Override
            public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
                OidcUser oidcUser = super.loadUser(userRequest);

                // Verifica si los claims necesarios están presentes
                String email = oidcUser.getAttribute("email");
                if (email == null) {
                    throw new OAuth2AuthenticationException("El email no está presente en el token.");
                }
                return oidcUser;
            }

            ; // Manejo básico de OIDC User
        };
    }


    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistration.withRegistrationId("auth0")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(callbackUrl)
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenUrl)
                .userInfoUri(userInfoUrl)
                .jwkSetUri(jwksUrl)
                .userNameAttributeName("sub")
                .clientName("Auth0")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("openid", "profile", "email")
                .build();

        return new InMemoryClientRegistrationRepository(registration);
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("permissions");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return authenticationConverter;
    }

    // Utilando Jwk Provider
    //    @Bean
//    public JwkProvider jwkProvider() {
//        JwkProvider jwkProvider = new JwkProviderBuilder("https://"+domain)
//                .cached(10, 24, TimeUnit.HOURS)
//                .rateLimited(10, 1, TimeUnit.MINUTES)
//                .build();
//        return new JwkProvider() {
//            @Override
//            public Jwk get(String kid) throws JwkException {
//                return jwkProvider.get(kid);
//            }
//        };
//    }


}
