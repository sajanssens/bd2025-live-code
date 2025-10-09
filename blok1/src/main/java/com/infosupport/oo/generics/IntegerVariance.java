package com.infosupport.oo.generics;

import java.util.ArrayList;
import java.util.List;

public class IntegerVariance {
    static void main() {
        List<Double> doubles = List.of(1.0, 2.0, 3.0);
        List<Integer> integers = List.of(1, 2, 3);
        List<Float> floats = List.of(1.0f, 2.0f, 3.0f);

        sumNumbers(doubles);
        sumNumbers(integers);
        sumNumbers(floats);
    }

    // covariance, passend wanneer je het generieke type nodig hebt in een leesoperatie
    private static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number number : numbers) {
            // we lezen hier Number in
            sum += number.doubleValue();
        }
        return sum;
    }

    // contravariance, passend wanneer je het generieke type nodig hebt in een schrijfoperatie
    private static void contravariance() {
        addNumbersTo(new ArrayList<Number>());
        addNumbersTo(new ArrayList<Integer>());
        addNumbersTo(new ArrayList<Object>());
    }

    private static void addNumbersTo(List<? super Integer> numbers) {
        for (int i = 0; i < 10; i++) {
            // We schrijven hier naar een List van Integers (of Objecten)
            numbers.add(i);
        }
    }
}
