package com.iupi.fintech.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TokenIOL {

    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private String issued;
    private String expires;
    private String refreshexpires;

    @Override
    public String toString() {
        return "TokenIok{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", issued='" + issued + '\'' +
                ", expires='" + expires + '\'' +
                ", refreshexpires='" + refreshexpires + '\'' +
                '}';
    }
}
