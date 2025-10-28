package com.infosupport.jpademo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
// @Table(name = "Person")
public class Person {

    @Id @GeneratedValue
    private long id;

    // @Column
    private String name;

    // @Column
    private int age;

    @Column(name = "genderId")
    private Gender gender;

    public Person() { }

    public Person(String name, int age) {
        this(name, age, Gender.Onbekend);
    }

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
