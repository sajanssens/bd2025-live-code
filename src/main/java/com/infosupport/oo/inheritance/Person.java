package com.infosupport.oo.inheritance;

public abstract class Person implements Walkable, Comparable<Person> {

    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
