package com.infosupport.jeedemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class Beer {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String brand;
    private double alc;

    public Beer(String brand, double alc) {
        this.brand = brand;
        this.alc = alc;
    }
}
