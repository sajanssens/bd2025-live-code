package com.infosupport;

public final class RecursionDemo {

    private RecursionDemo() { }

    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException();

        if (n == 0) { // stopconditie
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static long factorialLoop(int n) {
        if (n < 0) throw new IllegalArgumentException();

        if (n == 0) return 1;

        long result = n;
        for (int i = n - 1; i >= 1; i--) {
            result = result * i;
        }
        return result;
    }
}
