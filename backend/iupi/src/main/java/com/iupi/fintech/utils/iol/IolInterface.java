package com.iupi.fintech.utils.iol;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public interface IolInterface {

    public String getIolToken(String userIupi);
    public void saveIolToken(String userIupi,String iolToken);

    public void iolLogin(String userIupi);

    public WebClient getConnection();

}
