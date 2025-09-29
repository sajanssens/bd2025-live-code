package com.infosupport.javafunc.functionalinterface;

import com.infosupport.oo.Person;
import com.infosupport.oo.Teacher;

@FunctionalInterface
public interface PersonTeacherToString {
    String apply(Person p, Teacher t);
}
