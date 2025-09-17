package com.infosupport.oo;

public class Main {

    void main() {
        Person bram = new Person("Bram", "Janssens", 46);
        Person nicky = new Person("Nicky", "Boeijen", 34);

        bram.loop();
        nicky.loop();

        Trainee t = new Trainee(1, 24, "Java");
        System.out.println(t.toString());

        Trainee t2 = null;
        String specialism = t2.getSpecialism(); // NullPointerException!
        IO.println(specialism);
    }
}