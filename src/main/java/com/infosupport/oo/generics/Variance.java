package com.infosupport.oo.generics;

import com.infosupport.oo.inheritance.Person;
import com.infosupport.oo.inheritance.Teacher;
import com.infosupport.oo.inheritance.Trainee;

import java.util.List;

import static com.infosupport.oo.generics.NoGenerics.printPeople;

public class Variance {
    static void main() {
        invariance();
        covariance();
    }

    private static void covariance() {
        Teacher bram = new Teacher("Bram", "Hitchhiker's Guide to the Galaxy");
        Teacher hanno = new Teacher("Hanno", "Singing");
        Teacher maarten = new Teacher("Maarten", "Food");

        List<Teacher> teachers = List.of(bram, hanno, maarten);
        List<Person> persons = List.of(bram, hanno, maarten);

        // Generics in Java are invariant, but can be made covariant:
//        printPeople(teachers);
        printPeopleCovariant(teachers);
        printPeopleContravariant(teachers);

        printPeople(persons);
        printPeopleCovariant(persons);
        printPeopleContravariant(persons);

    }

    private static void printPeopleContravariant(List<? super Teacher> persons) {
        for (Object person : persons) {
            System.out.println(person);
        }
    }

    private static void printPeopleCovariant(List<? extends Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }




    private static void invariance() {
        Teacher bram = new Teacher("Bram", "Hitchhiker's Guide to the Galaxy");
        Teacher hanno = new Teacher("Hanno", "Singing");
        Teacher maarten = new Teacher("Maarten", "Food");

        List<Teacher> teachers = List.of(bram, hanno, maarten);

        // Generics in Java are invariant:

//        printPeople(teachers);
    }
}
