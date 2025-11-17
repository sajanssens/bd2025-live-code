package com.infosupport.jeedemo;

import com.github.javafaker.Faker;
import com.infosupport.jeedemo.domain.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

public final class DB {

    private DB() { }

    public static final List<Beer> beers = new ArrayList<>();

    static {
        Faker f = new Faker();
        beers.addAll(
                DoubleStream.generate(() -> new Random().nextDouble(3, 10))
                        .limit(10)
                        .mapToObj(d -> new Beer(f.beer().name(), Math.round(d * 10.0) / 10.0))
                        .toList());
    }
}
