package com.infosupport.javafunc;

import com.infosupport.oo.Person;

import java.util.Comparator;

public class PersonDateDescComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getDateOfBirth().isBefore(p2.getDateOfBirth()))
            return 1;
        else if (p1.getDateOfBirth().isAfter(p2.getDateOfBirth())) {
            return -1;
        } else {
            return 0;
        }
    }
}
