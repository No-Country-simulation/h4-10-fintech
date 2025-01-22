package com.iupi.fintech.config.jwt;


import java.io.Serializable;

//@Data
//@Getter@Setter
//public class JwtToken {
//
//    private String token;
//}
public record JwtToken (
        String jwtToken
)  implements Serializable {
}