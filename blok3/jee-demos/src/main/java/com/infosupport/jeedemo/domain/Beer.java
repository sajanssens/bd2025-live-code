package com.infosupport.jeedemo.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)
@Builder @AllArgsConstructor @NoArgsConstructor
public class Beer extends JPAEntity {
    private String brand;
    private double alc;
}
