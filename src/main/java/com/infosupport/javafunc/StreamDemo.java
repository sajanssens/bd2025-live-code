package com.infosupport.javafunc;

import com.infosupport.oo.Person;

import java.time.LocalDate;
import java.util.List;

public class StreamDemo {

    void main() {
        Person p0 = new Person("Mieke", "Janssens", LocalDate.of(1979, 11, 14));
        Person p1 = new Person("Bram", "Janssens", LocalDate.of(1979, 8, 22));
        Person p2 = new Person("Nicky", "Boeijen", LocalDate.of(1991, 5, 4));

        List<Person> persons = List.of(p0, p1, p2);

        persons.stream()
                .filter(p -> "Janssens".equals(p.getLastName()))
                .findAny()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No person found")
                );
    }
}
