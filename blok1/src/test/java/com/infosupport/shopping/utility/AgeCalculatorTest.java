package com.infosupport.shopping.utility;

import com.infosupport.utjava.shopping.utility.AgeCalculator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgeCalculatorTest {

    @Test
    public void Calculate_On32ndBirthDay_Returns32() {
        int age = AgeCalculator.calculate(LocalDate.of(1978, 9, 27), LocalDate.of(2010, 9, 27));
        assertEquals(32, age);
    }
}
