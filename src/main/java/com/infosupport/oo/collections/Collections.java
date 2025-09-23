package com.infosupport.oo.collections;

import com.infosupport.oo.inheritance.Trainee;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SequencedSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collections {
    static void main() {
        lists();
        sets();
        maps();
        queues();
    }

    private static void queues() {
        Queue<Trainee> coffeeQueue = new PriorityQueue<>();
        coffeeQueue.add(new Trainee("Bram", 1));
        coffeeQueue.add(new Trainee("Hanno", 2));
        coffeeQueue.add(new Trainee("Maarten", 3));

        System.out.println(coffeeQueue.poll());
        System.out.println(coffeeQueue.poll());
        System.out.println(coffeeQueue.poll());
        System.out.println(coffeeQueue.poll());

        Deque<Trainee> teaQueue = new ArrayDeque<>();
        teaQueue.add(new Trainee("Bram", 1));
        teaQueue.add(new Trainee("Hanno", 2));
        teaQueue.add(new Trainee("Maarten", 3));

        System.out.println(teaQueue.pollFirst());
        System.out.println(teaQueue.pollLast());
        System.out.println(teaQueue.poll());
    }

    private static void maps() {
        Map<Integer, Trainee> trainees = new HashMap<>();
        trainees.put(1, new Trainee("Bram", 1));
        trainees.put(2, new Trainee("Hanno", 2));
        trainees.put(3, new Trainee("Maarten", 3));

        var hanno = trainees.get(2);
        trainees.put(4, new Trainee("Nicky", 4));
        var nicky = trainees.get(4);

        trainees.putIfAbsent(4, new Trainee("Ludo", 4));
    }

    private static void sets() {
        var bram = new Trainee("Bram", 1);
        var hanno = new Trainee("Hanno", 2);
        var ookBram = new Trainee("Bram", 1);

        Set<Trainee> trainees = new HashSet<>();

        trainees.add(bram);
        trainees.add(hanno);
        trainees.add(ookBram);

        for (Trainee trainee : trainees) {
            System.out.println(trainee);
        }

        SequencedSet<Trainee> sortedSet = new TreeSet<>();

    }

    private static void lists() {
        // Program against interfaces, not implementations!
        List<Trainee> trainees = retrieveTrainees();
        var firstTrainee = trainees.getFirst();
    }

    static List<Trainee> retrieveTrainees() {
        List<Trainee> trainees = new CopyOnWriteArrayList<>();
        trainees.add(new Trainee("Bram", 1));

        return trainees;
    }
}
