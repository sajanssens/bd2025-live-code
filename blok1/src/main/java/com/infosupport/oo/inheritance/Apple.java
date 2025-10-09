package com.infosupport.oo.inheritance;

public class Apple implements Comparable<Apple> {
    private int weightGrams;
    private String name;

    public Apple(int weightGrams) {
        this(weightGrams, "Unknown");
    }

    public Apple(int weightGrams, String name) {
        this.weightGrams = weightGrams;
        this.name = name;
    }

    @Override
    public int compareTo(Apple o) {
        return this.weightGrams - o.weightGrams;
    }

    public String getName() {
        return name;
    }
}
