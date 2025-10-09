package com.infosupport.utjava.shopping;

import com.infosupport.utjava.shopping.utility.AgeCalculator;

import java.time.LocalDate;

public record User(String name, LocalDate dateOfBirth, String accountNumber) {
    public int getAge() {
        return AgeCalculator.calculate(dateOfBirth, LocalDate.now());
    }
}
