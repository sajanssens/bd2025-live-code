package com.infosupport;

import org.junit.jupiter.api.Test;

import static com.infosupport.PalindromeChecker.isPalindromeSmart;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeCheckerTest {

    @Test void givenAPalindromeInputWhenCheckedThenItsAPalindrome() {
        // given
        String s = "racecar";

        // when
        boolean palindrome = isPalindromeSmart(s);

        // then
        assertTrue(palindrome);
    }

    @Test void givenNotPalindromeInputWhenCheckedThenItsNotAPalindrome() {
        // given
        String s = "racecart";

        // when
        boolean palindrome = isPalindromeSmart(s);

        // then
        assertFalse(palindrome);
    }

    @Test void givenTwoIntArraysWhenSummedThenCorrectSumsAreReturned() {
        // given
        int[] nummers1 = {1, 2, 3};

        int[] nummers2 = new int[5];
        nummers2[0] = 1;
        nummers2[1] = 2;
        nummers2[2] = 3;
        nummers2[3] = 4;
        nummers2[4] = 5;

        // when
        long som1 = PalindromeChecker.som(1, 2, 3);
        long som2 = PalindromeChecker.som(1, 2, 3, 4, 5);
        long som3 = PalindromeChecker.somArr(nummers1);
        long som4 = PalindromeChecker.somArr(nummers2);

        // then
        assertTrue(som1 == 6);
        assertTrue(som2 == 15);
        assertTrue(som3 == 6);
        assertTrue(som4 == 15);
    }
}