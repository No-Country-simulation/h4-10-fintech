package com.iupi.fintech.config.jwt;


import java.io.Serializable;

public record JwtToken (
        String jwtToken
)  implements Serializable {
}