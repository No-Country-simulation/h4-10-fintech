package com.iupi.fintech.utils.iol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

import java.util.Base64;

@Entity @Getter @ToString
public class IolUser {

    @Id
    private String username;

    @Column(name = "iol_token" , nullable = false, length = 2000)
    private String encryptedIolToken;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEncryptedIolToken(String iolToken) {
        this.encryptedIolToken = encrypt(iolToken);
    }
    public String getIolToken() {
        return decrypt();
    }
    public String getEncryptedIolToken() {
        return decrypt();
    }




    private String encrypt(String data) {
        // Implementar encriptación (ej. AES)
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    private String decrypt() {
        // Implementar desencriptación
        return new String(Base64.getDecoder().decode(this.encryptedIolToken));
    }

}
