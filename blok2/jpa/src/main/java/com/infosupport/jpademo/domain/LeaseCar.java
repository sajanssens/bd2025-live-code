package com.infosupport.jpademo.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class LeaseCar {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String brand;

    @OneToOne(mappedBy = "leaseCar", cascade = CascadeType.PERSIST)
    private Person owner;
}
