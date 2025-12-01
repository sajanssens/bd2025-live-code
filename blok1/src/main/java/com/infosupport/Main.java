package com.infosupport;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Math.abs;

public class Main {

    Person p = new Person();
    final Object lock = new Object();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    void main() {
        // import demo ------
        List<Integer> list = new ArrayList<>();
        int abs = abs(-20); // static import

        equalityDemo();

        // palindrome checker
        String str = "racecar";
        System.out.println(PalindromeChecker.isPalindrome(str));
        System.out.println(PalindromeChecker.isPalindromeSmart(str));

        Faker f = new Faker();
        System.out.println(f.hitchhikersGuideToTheGalaxy().quote());

        AtomicInteger i = new AtomicInteger();
        i.incrementAndGet();
    }

    private static synchronized void equalityDemo() {
        // equality
        var i = '0';
        // i = "90";
        // Wrong 🤮
        // if(i = 0){
        //
        // }

        // equality for primitive types
        if (i == 0) {
            System.out.println("i == 0");
        }

        // equality for reference types
        Integer sum = 0;
        if (sum.equals(2)) {
            System.out.println("sum equals 2");
        }
    }

    public /*synchronized*/ void update() {
        readWriteLock.writeLock().lock();
        // synchronized (this.lock) {
        // synchronized (this) {
        int oldAge = this.p.age;
        oldAge = oldAge + 1;
        this.p.age = oldAge;
        // }
        readWriteLock.writeLock().unlock();
    }

    public void ietsAnders() {
        readWriteLock.readLock().lock();
        System.out.println("Persons age is: " + p.age);
        readWriteLock.readLock().unlock();
    }
}
