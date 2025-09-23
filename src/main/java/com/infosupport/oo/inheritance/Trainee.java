package com.infosupport.oo.inheritance;

import java.util.Objects;

public class Trainee extends Person {

    private final int nr;

    public Trainee(String name, int nr) {
        super(name);
        this.nr = nr;
    }

    @Override
    public void walk() {
        System.out.println("I'm happy walking and learning new things");
    }

    public void proggen() {
        System.out.println("public class Main { .... }");
    }

    @Override
    public int compareTo(Person o) {
        Trainee other = (Trainee) o;

        // if (this.nr < other.nr) {
        //     return -1;
        // } else if (this.nr > other.nr) {
        //     return 1;
        // } else {
        //     return 0;
        // }

        // Sneller is dit:
        return this.nr - other.nr;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Trainee trainee = (Trainee) o;
        return nr == trainee.nr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nr);
    }

    @Override
    public String toString() {
        return "Trainee{" +
               "nr=" + nr +
               '}';
    }
}
