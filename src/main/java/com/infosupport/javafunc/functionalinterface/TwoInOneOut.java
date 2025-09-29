package com.infosupport.javafunc.functionalinterface;

@FunctionalInterface
public interface TwoInOneOut<T, U, R> {
    R apply(T p, U t);
}
