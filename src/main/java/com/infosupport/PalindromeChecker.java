package com.infosupport;

public class PalindromeChecker {

    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

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
}
