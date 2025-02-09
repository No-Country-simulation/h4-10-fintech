package com.iupi.fintech.utils.iol;

import com.iupi.fintech.dtos.TokenIOL;
import com.iupi.fintech.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class IolApiService implements IolInterface{

    @Value("${IOL_USERNAME}")
    private String username;

    @Value("${IOL_PASSWORD}")
    private String password;
    @Value("${IOL_URL}")
    private String url;
    private final IolUserRepository iolUserRepository;


@Autowired
    public IolApiService(IolUserRepository iolUserRepository) {
        this.iolUserRepository = iolUserRepository;
    }



    public void saveIolToken(String userIupi,String iolToken) {

        IolUser iolUser = new IolUser();
        iolUser.setUsername(userIupi);
        iolUser.setEncryptedIolToken(iolToken);
        System.out.println("token iol desde el save: "+iolToken);
        System.out.println("encriptado: "+iolUser.getEncryptedIolToken());
        iolUserRepository.save(iolUser);
        System.out.println("iolUser : " + iolUser.toString());
    }

    @Override
    public void iolLogin(String userIupi) {

        validateUser(this.username, this.password);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("grant_type", "password");
        formData.add("username", this.username);
        formData.add("password", this.password);

        TokenIOL t= getConnection().post()
                .uri("/token")
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(TokenIOL.class)
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(e -> System.out.println("Error: " + e.getMessage()))
                .block();

       saveIolToken(userIupi,t.getAccess_token());
    }
    public String getIolToken(String userIupi) {
        return iolUserRepository.findById(userIupi).get().getIolToken();
    }

    private void validateUser(String username, String password) {
        if(username==null || password==null){
            throw new ApplicationException("No se encontraron las variables de entorno");
        }
    }

    public WebClient getConnection(){
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                    String cookieHeader = clientResponse.headers().asHttpHeaders().getFirst(HttpHeaders.SET_COOKIE);
                    if (cookieHeader != null) {
                        System.out.println("Cookies recibidas: " + cookieHeader);
                    }
                    return Mono.just(clientResponse);
                }))
                .build();
    }



}
