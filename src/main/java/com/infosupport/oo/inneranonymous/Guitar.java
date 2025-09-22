package com.infosupport.oo.inneranonymous;

import com.infosupport.oo.annotations.PerformanceTest;
import com.infosupport.oo.annotations.PerformanceTests;

import java.time.LocalDate;

public class Guitar {
    private String brand;
    private String model;
    private GuitarType guitarType;

    /**
     * The date when the guitar was sold. Should be in the past.
     */
    @Past
    private LocalDate sold;

    public Guitar(String brand, String model, LocalDate sold) {
        this.brand = brand;
        this.model = model;
        this.sold = sold;

        this.guitarType = GuitarType.TELECASTER;
    }


// 'lange versie': elke @PerformanceTest is gewrapped in een @PerformanceTests
//    @PerformanceTests({
//        @PerformanceTest(numberOfTries = 2, maxTime = 4),
//        @PerformanceTest(numberOfTries = 10, maxTime = 7)
//    })
    @PerformanceTest(numberOfTries = 2, maxTime = 4)
    @PerformanceTest(numberOfTries = 10, maxTime = 7)
    public void ship() {
        System.out.println("Shipping guitar");
    }

    /**
     * Plays this guitar, by the given musician.
     * @param musician The musician playing the guitar.
     */
    public void play(String musician) {

    }
}
