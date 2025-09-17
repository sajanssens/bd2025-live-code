package com.infosupport.oo;

public class Main {

    public static void main(String[] args) {
        Person bram = new Person("Bram", "Janssens", 46);
        Person nicky = new Person("Nicky", "Boeijen", 34);

        bram.loop();
        nicky.loop();

        Trainee t = new Trainee(1, 24, "Java");
        System.out.println(t.toString());

        Trainee t2 = null;
        t2.getSpecialism();
    }
}
