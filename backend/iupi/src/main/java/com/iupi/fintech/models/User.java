package com.iupi.fintech.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class User {

    private Long id;
}
