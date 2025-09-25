package com.infosupport.javafunc;

import com.infosupport.oo.Person;

import java.util.Comparator;

public class PersonLastNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getLastName().compareTo(p2.getLastName());
    }
}
