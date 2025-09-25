package com.infosupport.javafunc;

import com.infosupport.oo.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.time.LocalDate.now;
import static java.util.Collections.sort;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class FunctionalProgrammingDemo {

    void main() {
        Person p0 = new Person("Mieke", "Janssens", LocalDate.of(1979, 11, 14));
        Person p1 = new Person("Bram", "Janssens", LocalDate.of(1979, 8, 22));
        Person p2 = new Person("Nicky", "Boeijen", LocalDate.of(1991, 5, 4));
        Person p3 = new Person("Frank", "Boeijen", LocalDate.of(1957, 11, 27));

        List<Person> persons = Arrays.asList(p0, p1, p2, p3);

        // sort by natural (default) order (defined inside Person)
        sort(persons);
        persons.forEach(System.out::println);

        // sort by other order (defined outside Person in a Comparator)
        PersonDateDescComparator c = new PersonDateDescComparator();
        sort(persons, c);
        persons.forEach(System.out::println);

        sort(persons, new PersonLastNameComparator());
        persons.forEach(System.out::println);

        var byFirstName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        };
        sort(persons, byFirstName);
        persons.forEach(System.out::println);

        Comparator<Person> byLastName = (Person o1, Person o2) -> o1.getFirstName().compareTo(o2.getFirstName());
        sort(persons, byLastName);
        persons.forEach(System.out::println);

        sort(persons, (Person o1, Person o2) -> o1.getLastName().compareTo(o2.getLastName()));
        persons.forEach(System.out::println);

        sort(persons, comparing(Person::getLastName));
        persons.forEach(System.out::println);

        // What's a lambda?
        Function<Integer, Integer> f = x -> x * x;
        Function<Integer, Integer> g = x -> x + 1;
        var result1 = f.apply(2); // f(2)
        var result2 = g.apply(2); // g(2)

        // What's a stream?
        List<Integer> ages =
                persons.parallelStream() // 1: create
                        .filter(p -> p.getLastName().startsWith("J"))// 2: modify
                        .map(p -> Period.between(p.getDateOfBirth(), now()).getYears())
                        .collect(toList()); // 3: collect

        System.out.println(ages);
    }
}

