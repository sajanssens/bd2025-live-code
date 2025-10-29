package com.infosupport.jpademo.domain;

import com.infosupport.jpademo.BooleanTFConverter;
import com.infosupport.jpademo.dao.HasId;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class Person implements HasId {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private int age;
    private Gender gender;

    @Convert(converter = BooleanTFConverter.class)
    private boolean isAlive;

    @ManyToOne // UNIDI
    private Company company;

    @ManyToOne // BIDI
    private Department worksAt;
}
