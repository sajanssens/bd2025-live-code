package com.infosupport;

public class ShortCircuitDemo {

    void main(String[] args) {
        int i = 10, j = 20;

        if (i < 10 && j < 20) { //  LAZY en SHORT CIRCUITING
            System.out.println(i);
            System.out.println(j);
        }

        if ((i & j) >= 100) {
            System.out.println(i);
            System.out.println(j);
        }

        if (i < 10 & j < 20) { // NIET LAZY NIET SHORT CIRCUITING!!!!
            System.out.println(i);
            System.out.println(j);
        }

        if ((i++ == 0) & (j-- == 0)) { // NIET LAZY NIET SHORT CIRCUITING!!!!
            System.out.println(i);
            System.out.println(j);
        } else {
            System.out.println(i);
            System.out.println(j);
        }
    }
}