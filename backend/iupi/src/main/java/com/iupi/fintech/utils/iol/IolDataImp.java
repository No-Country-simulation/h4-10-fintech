package com.iupi.fintech.utils.iol;

import com.iupi.fintech.models.generic.FondosComunInversion;
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

    @Autowired
    public IolDataImp(IolInterface iolInterface, IolUserRepository iolUserRepository) {
        this.iolInterface = iolInterface;
        this.iolUserRepository = iolUserRepository;
    }


    @Override
    public List<FondosComunInversion> getIolData(String user) {
        System.out.println("entro al metodo de obtener data");
    Optional<IolUser> userIupii = iolUserRepository.findById(user);
    String token= userIupii.get().getEncryptedIolToken();
        WebClient webClient= iolInterface.getConnection();
        System.out.println("token: "+token);

        return webClient.get()
                .uri("/api/v2/Titulos/FCI")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FondosComunInversion>>() {})
                .block();
    }
}
