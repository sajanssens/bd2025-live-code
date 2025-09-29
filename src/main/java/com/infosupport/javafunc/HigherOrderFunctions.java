package com.infosupport.javafunc;

import java.util.function.Function;

public class HigherOrderFunctions {

    public static int f(int x, Function<Integer, Integer> g) {
        return x + g.apply(x);
    }

    void main() {
        int x;
        Function<Integer, Integer> g;
        int result;

        x = 2;
        g = y -> y * 2;
        result = f(x, g);
        System.out.println(result);

        x = 3;
        g = y -> y * y;
        result = f(x, g);
        System.out.println(result);
    }
}
