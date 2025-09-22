package com.infosupport.oo.generics;

import com.infosupport.oo.inheritance.Person;
import com.infosupport.oo.inheritance.Teacher;
import com.infosupport.oo.inheritance.Trainee;

import java.util.ArrayList;
import java.util.List;

public class NoGenerics {
    static void main() {
        arrays();
        collectionsBeforeGenerics();
    }

    private static void collectionsBeforeGenerics() {
        // Before generics: < Java 5
        // Program against interfaces, not implementations
        List teachers = new ArrayList();

        Teacher bram = new Teacher("Bram", "Hitchhiker's Guide to the Galaxy");
        Teacher hanno = new Teacher("Hanno", "Singing");
        Teacher maarten = new Teacher("Maarten", "Food");

        teachers.add(bram);
        teachers.add(hanno);
        teachers.add(maarten);

        Object firstTeacher = teachers.get(0);
        System.out.println(((Teacher) firstTeacher).getSpecialism());
    }

    private static void arrays() {
        Teacher bram = new Teacher("Bram", "Hitchhiker's Guide to the Galaxy");
        Teacher hanno = new Teacher("Hanno", "Singing");
        Teacher maarten = new Teacher("Maarten", "Food");

        Teacher[] teachers = {bram, hanno, maarten};
        printPeople(teachers);

        Trainee niels = new Trainee("Niels", 1337);
        Trainee maria = new Trainee("Maria", 101);
        Trainee luca = new Trainee("Luca", 42);

        Trainee[] trainees = {niels, maria, luca};
        printPeople(trainees);
    }

    static void printPeople(Person[] people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }

    static void printPeople(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
