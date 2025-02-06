package com.iupi.fintech.utils.iol;

import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.models.generic.ProductoFci;
import com.iupi.fintech.repositories.ProductoFciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class IolDataImp implements IolDataInterface {

    private final IolInterface iolInterface;
    private final IolUserRepository iolUserRepository;
    private final ProductoFciRepository productoRepository;


    @Autowired
    public IolDataImp(IolInterface iolInterface, IolUserRepository iolUserRepository, ProductoFciRepository productoRepository) {
        this.iolInterface = iolInterface;
        this.iolUserRepository = iolUserRepository;
        this.productoRepository = productoRepository;
    }

// Obtiene todos los datos de FCI de la plataforma y los almacena en nuestra DB
    @Override
    public List<ProductoFciDto> getIolData(String user) {
        System.out.println("entro al metodo de obtener data");
    Optional<IolUser> userIupii = iolUserRepository.findById(user);
    String token= userIupii.get().getEncryptedIolToken();
        WebClient webClient= iolInterface.getConnection();
        System.out.println("token: "+token);

        return webClient.get()
                .uri("/api/v2/Titulos/FCI")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductoFciDto>>() {})
                .block();
    }

    //Obtiene un titulo por su simbolo(identificador)
    @Override
    public ProductoFci getIolDataBySimbolo(String user, String simbolo) {
//        Optional<IolUser> userIupii = iolUserRepository.findById(user);
//        String token= userIupii.get().getEncryptedIolToken();
//        WebClient webClient= iolInterface.getConnection();
//        return webClient.get()
//                .uri("/api/v2/Titulos/FCI/"+simbolo)
//                .header("Authorization", "Bearer " + token)
//                .retrieve()
//                .bodyToMono(ProductoFCI.class)
//                .block();
        return null;
    }
}
