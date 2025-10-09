package com.infosupport.oo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {

    void main() {
        Person p1 = new Person("Bram", "Janssens", LocalDate.of(1979, 8, 22));
        Person p2 = new Person("Nicky", "Boeijen", LocalDate.of(1991, 5, 4));

        p1.loop();
        p2.loop();

        // Trainee t = new Trainee(1, 24, "Java");
        // System.out.println(t.toString());

        Trainee t2 = null;
        // String specialism = t2.getSpecialism(); // NullPointerException!
        // IO.println(specialism);

        Person p3 = new Person("Bram", "Janssens", LocalDate.of(1979, 8, 22));
        System.out.println(p3.hashCode());
        if (p1.equals(p3)) {
            System.out.println("We zijn gelijk!!!");
        } else {
            System.out.println("We zijn niet gelijk...");
        }

        Set<Person> personTable = new HashSet<>();
        personTable.add(p1);
        personTable.add(p2);
        personTable.add(p3);
        System.out.println(personTable.size());

        Teacher bram = new Teacher(100 + 42, "Info Support");
        int elligence = bram.elligence();
        System.out.println(bram);

        Teacher bramChanged = new Teacher(bram.elligence() + 10, bram.organization());
    }
}