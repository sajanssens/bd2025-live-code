package com.infosupport.oo.inheritance;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

public class App {
    void main() {
        // Iets doen met enkele Persons: --------------------------
        // Person per = new Person(); // mag niet want Person is abstract
        Person tea = new Teacher("Bram", "Java");
        Person tra = new Trainee("Niels", 1337);
        doeIetsMet(tea);
        doeIetsMet(tra);

        // Iets doen met lijst van Persons: --------------------------
        List<Person> persons = List.of(new Boss("Donald"), new Teacher("Bram", "Java"), new Trainee("Niels", 1337));
        doeIetsMet(persons);

        // Lijst van apples sorteren: --------------------------------
        List<Apple> apples = Arrays.asList(new Apple(100), new Apple(10), new Apple(60), new Apple(200));
        Collections.sort(apples); // op default volgorde, bepaald door implements Comparable IN Apple
        System.out.println(apples);

        apples.sort(comparing(Apple::getName)); // op een andere volgorde, bepaald door de comparator hier meegegeven
        System.out.println(apples);
    }

    public static void doeIetsMet(Person p) {
        p.walk();
    }

    public static void doeIetsMet(List<Person> persons) {
        for (Person person : persons) {
            person.walk();

            // Code smell: niet uitbreidbaar voor andere soorten Persons.aa
            if (person instanceof Trainee t) {
                t.proggen();
            }
        }
    }
}
