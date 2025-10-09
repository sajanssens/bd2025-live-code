package com.infosupport.oo.generics;

import com.infosupport.oo.inheritance.Apple;
import com.infosupport.oo.inheritance.Boss;
import com.infosupport.oo.inheritance.Teacher;

import java.util.List;

public class WithGenerics {
    static void main() {
        collections();
        databaseTable();

    }

    private static void databaseTable() {
        // DatabaseTable<Teacher> databaseOfTeachers = new DatabaseTable<>();
        var databaseOfTeachers = new DatabaseTable<Teacher>();

        Teacher teacher = databaseOfTeachers.retrieve(1);

        var databaseOfBosses = new DatabaseTable<Boss>();
        Boss boss = databaseOfBosses.retrieve(1);

        var databaseOfApples = new DatabaseTable<Apple>();
        Apple apple = databaseOfApples.retrieve(1);
    }

    private static void collections() {
        Teacher bram = new Teacher("Bram", "Hitchhiker's Guide to the Galaxy");
        Teacher hanno = new Teacher("Hanno", "Singing");
        Teacher maarten = new Teacher("Maarten", "Food");

        List<Teacher> teachers = List.of(bram, hanno, maarten);
        Teacher teacher = teachers.get(0);
        System.out.println(teacher.getSpecialism());
    }
}
