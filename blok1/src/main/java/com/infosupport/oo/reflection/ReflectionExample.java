package com.infosupport.oo.reflection;

import com.infosupport.oo.inneranonymous.Guitar;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;

public class ReflectionExample {
    static void main() {
        var guitar = new Guitar("Ibanez", "AZ-200", LocalDate.of(2000, 1, 1));

        Class<? extends Guitar> guitarClass = guitar.getClass();
        Field[] declaredFields = guitarClass.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));

        for (Field field : declaredFields) {
            System.out.printf("Field %s has the following annotations:%n", field.toString());
            System.out.println(Arrays.toString(field.getAnnotations()));
            // usually performed by an 'annotation processor'
        }
    }
}
