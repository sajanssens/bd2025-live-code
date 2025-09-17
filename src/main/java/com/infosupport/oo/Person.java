package com.infosupport.oo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static java.time.LocalDate.now;

public class Person { // POJO
    // STATE: -------------------
    private final String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    // BEHAVIOR: -------------------

    // CONSTRUCTOR

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int loop() {
        if (Period.between(this.dateOfBirth, now()).getYears() < 40) {
            IO.println("Gaat lekker...");
            return 13;
        }

        IO.println("HIJG HIJG");
        return 8;
    }

    @Override public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }

    public String getLastName() {
        return lastName;
    }

    @Override public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
