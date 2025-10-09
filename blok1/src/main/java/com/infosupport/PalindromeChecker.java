package com.infosupport;

import static java.util.Arrays.stream;

public class PalindromeChecker {

    /**
     * Checks whether the give string is a palindrome.
     * A palindrome is a word, phrase, number, or sequence of characters
     * that reads the same forward and backward
     * (ignoring spaces, punctuation, and capitalization).
     * For example, "madam" and "racecar" are palindromes.
     *
     * @param s the string to check
     * @return true if s is a palindrome
     */
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given string is a palindrome by comparing it to its reversed version.
     * This method does not ignore spaces, punctuation, or capitalization.
     *
     * @param s the string to check
     * @return true if s is a palindrome
     */
    public static boolean isPalindromeSmart(String s) {
        return s.contentEquals(new StringBuilder(s).reverse());
    }

    public void geeftNiks(int i) {
        System.out.println("Hallo");

        if (i == 42) {
            System.out.println("Truth!");
            return;
        }

        System.out.println("Don't know...");
    }

    public static long som(int... nummers) {
        long result = 0;
        for (int nummer : nummers) {
            result += nummer;
        }
        return result;
    }

    public static long somArr(int[] nummers) {
        return stream(nummers).sum();
    }
}
