package com.iupi.fintech.utils.iol;

import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.ProductoFciMapper;
import com.iupi.fintech.models.generic.ProductoFCI;
import com.iupi.fintech.repositories.ProductoFciRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
@Service
public class FciUpdateService {

    @Value("${IOL_USERNAME}")
    private String username;

    @Value("${IOL_PASSWORD}")
    private String password;
    @Value("${IOL_URL}")
    private String url;
    private final ProductoFciRepository productoRepository;
    private final ProductoFciMapper productoMapper;

    public FciUpdateService(ProductoFciRepository productoRepository, ProductoFciMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }


    @Scheduled(cron = "0 0 6,18 * * *")
    public void updateFCI() {

        iolLogin()
                .flatMap(this::getIolDataFCI)
                .subscribe(productos -> {
                    productoRepository.saveAll(productos);
                    System.out.println(" FCI actualizado correctamente.");
                }, error -> System.err.println(" Error en la actualización de FCI: " + error.getMessage()));
    }


    private Mono<List<ProductoFCI>> getIolDataFCI(String token) {

        return getConnection().get()
                .uri("/api/v2/Titulos/FCI")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(ProductoFciDto.class)
                .map(productoMapper::toEntity)
                .collectList();
    }

    private Mono<String> iolLogin() {

        validateUser(this.username, this.password);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("grant_type", "password");
        formData.add("username", this.username);
        formData.add("password", this.password);

        return getConnection().post()
                .uri("/token")
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(Map.class).
                map(response -> (String) response.get("access_token"))
                .doOnSuccess(token -> System.out.println(" Token obtenido: " + token))
                .doOnError(error -> System.err.println("❌ Error al obtener el token: " + error.getMessage()));

    }

    private WebClient getConnection() {
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

    private void validateUser(String username, String password) {
        if (username == null || password == null) {
            throw new ApplicationException("No se encontraron las variables de entorno");
        }
    }

}
