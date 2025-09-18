package com.infosupport.oo.inheritance;

public class Trainee extends Person {

    private final int nr;

    public Trainee(String name, int nr) {
        super(name);
        this.nr = nr;
    }

    @Override
    public void walk() {
        System.out.println("Im happy walking");
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
}
