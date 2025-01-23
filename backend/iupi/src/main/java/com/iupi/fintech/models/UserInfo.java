package com.iupi.fintech.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class UserInfo {
    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String nickname;
    private String email;
    private boolean email_verified;
    private String picture;
    private Date issued_at;
    private Date expires_in;

}
