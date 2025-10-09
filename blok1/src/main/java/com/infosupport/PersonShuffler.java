package com.infosupport;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PersonShuffler {

    static void main() {
        List<String> seventeens = Arrays.asList("Seger",
                "Bram",
                "Joshua",
                "Maria",
                "Ludo",
                "Ruben",
                "Bas",
                "Luka",
                "Niels",
                "Nicky");

        Collections.shuffle(seventeens);

        seventeens.forEach(System.out::println);
    }
}
