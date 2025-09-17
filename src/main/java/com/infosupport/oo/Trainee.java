package com.infosupport.oo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
@AllArgsConstructor
public class Trainee {
    private final int id;

    private int age;
    private String specialism;
}
