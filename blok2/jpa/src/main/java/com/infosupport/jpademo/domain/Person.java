package com.infosupport.jpademo.domain;

import com.infosupport.jpademo.BooleanTFConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
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
public class Person {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private int age;
    private Gender gender;

    @Convert(converter = BooleanTFConverter.class)
    private boolean isAlive;

    @OneToOne(cascade = CascadeType.PERSIST)
    private LeaseCar leaseCar;
}
