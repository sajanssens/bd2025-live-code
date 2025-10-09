package com.infosupport.oo.generics;

import com.infosupport.oo.inheritance.Teacher;
import com.infosupport.oo.inheritance.Trainee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericsInMethods {
    static void main() {
        printListCovariant(List.of(new Trainee("Bram", 1), new Trainee("Hanno", 2)));
        printListCovariant(List.of(new Object(), new Object()));

        List<Number> numbers = List.of(1, 2, 3.14);
        List<Integer> integers = List.of(1, 2, 3);

        List<? extends Number> result = mergeLists(numbers, integers);
        System.out.println(result);
    }

    private static <T> void printList(List<T> list) {
        list.forEach(System.out::println);
    }

    // use case for wildcards in generic method argument
    private static <T> List<? extends T> mergeLists(List<T> list1, List<? extends T> list2) {
        var list = new ArrayList<T>();

        list.addAll(list1);
        list.addAll(list2);

        return list;
    }

    private static <T> void printListCovariant(List<? extends T> list) {
        list.forEach(System.out::println);
    }

    private static <T, U> void printMapValues(Map<T, U> map) {
        map.values().forEach(x -> System.out.println(x));
    }
}
