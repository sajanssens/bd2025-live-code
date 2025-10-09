package com.infosupport.javafunc.functionalinterface;

import com.infosupport.oo.Person;
import com.infosupport.oo.Teacher;

import java.util.function.BiFunction;

public class Main {

    static int i = 42;

    void main() {

        Person joshua = new Person();
        Teacher bram = new Teacher(130, "Info Support");

        String methodeIQ = methode(joshua, bram);

        //  Function descriptor: Person, Teacher -> String
        PersonTeacherToString lambda = (Person p, Teacher t) -> p.getLastName() + " has IQ: " + t.elligence();
        TwoInOneOut<Person, Teacher, String> lambda1 = (Person p, Teacher t) -> p.getLastName() + " has IQ: " + t.elligence();
        BiFunction<Person, Teacher, String> lambda02 = (Person p, Teacher t) -> p.getLastName() + " has IQ: " + t.elligence() + i;

        String lambdaIQ0 = lambda.apply(joshua, bram);
        String lambdaIQ1 = lambda1.apply(joshua, bram);
        String lambdaIQ2 = lambda02.apply(joshua, bram);
    }

    public static String methode(Person p, Teacher t) {
        return p.getLastName() + " has IQ: " + t.elligence();
    }

    public void isDitGeldig() {

    }
}
