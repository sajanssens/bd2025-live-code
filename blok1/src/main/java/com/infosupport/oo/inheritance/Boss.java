package com.infosupport.oo.inheritance;

public class Boss extends Person {

    public Boss(String name) {
        super(name);
    }

    @Override
    public void walk() {
        System.out.println("I'm the boss here, listen to me...");
    }

    @Override public int compareTo(Person o) {
        return 0;
    }
}
