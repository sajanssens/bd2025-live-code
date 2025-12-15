package com.infosupport.jeedemo.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    private String make;
    private String type;
    private double price;

    @Min(0) @Max(100) @Builder.Default
    private int fillLevel = 100;

    public void sip() {

    }
}
