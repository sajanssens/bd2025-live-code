package com.infosupport.oo;

public class Person { // POJO
    // STATE: -------------------
    private final String firstName;
    private String lastName;
    private int age;

    // BEHAVIOR: -------------------

    // CONSTRUCTOR
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int loop() {
        if (this.age < 40) {
            IO.println("Gaat lekker...");
            return 13;
        }

        IO.println("HIJG HIJG");
        return 8;
    }

    // Getters/setters: ----

    // Java bean convention in action
    public String getFirstName() {
        return firstName;
    }

    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
