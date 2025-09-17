package com.infosupport.oo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trainee {
    private final int id;
    private int age;
    private String specialism;
}
