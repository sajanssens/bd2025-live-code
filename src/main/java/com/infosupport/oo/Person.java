package com.infosupport.oo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static java.lang.IO.println;
import static java.time.LocalDate.EPOCH;
import static java.time.LocalDate.now;

public class Person implements Comparable<Person> { // POJO
    // STATE: -------------------
    private final String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    // BEHAVIOR: -------------------

    // CONSTRUCTOR

    public Person() {
        // WET:
        // this.firstName = "Unknown";
        // this.lastName = "Unknown";
        // this.dateOfBirth = LocalDate.EPOCH;

        // vervangen door:
        // DRY:
        this("Unknown", "Unknown", EPOCH); // constructor chaining
    }

    public Person(String firstName, String lastName) {
        // this.firstName = firstName;
        // this.lastName = lastName;
        // this.dateOfBirth = LocalDate.EPOCH;
        // vervangen door:
        this(firstName, lastName, EPOCH);
    }

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int loop() {
        if (Period.between(this.dateOfBirth, now()).getYears() < 40) {
            println("Gaat lekker...");
            return 13;
        }

        println("HIJG HIJG");
        return 8;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public int compareTo(Person other) {
        return this.dateOfBirth.compareTo(other.dateOfBirth);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }
}
