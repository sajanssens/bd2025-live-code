package com.infosupport;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.infosupport.PalindromeChecker.isPalindromeSmart;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeCheckerTest {

    @Test
    void givenAPalindromeInputWhenCheckedThenItsAPalindrome() {
        // given
        String s = "racecar";

        // when
        boolean palindrome = isPalindromeSmart(s);

        // then
        assertTrue(palindrome);
    }

    @Test
    void givenNotPalindromeInputWhenCheckedThenItsNotAPalindrome() {
        // given
        String s = "racecart";

        // when
        boolean palindrome = isPalindromeSmart(s);

        // then
        assertFalse(palindrome);
    }

    @Test
    void givenBlah() {
        PalindromeChecker.som(1, 2, 3, 4, 5);
        PalindromeChecker.som(1, 2, 3);

        int[] nummers = new int[5];
        nummers[0] = 1;
        nummers[1] = 2;
        nummers[2] = 3;
        nummers[3] = 4;
        nummers[4] = 5;

        List<Integer> palindromes = new ArrayList<>();
        palindromes.add(1);
        palindromes.add(2);
        palindromes.add(3);
        palindromes.add(4);
        palindromes.add(5);

        PalindromeChecker.somArr(nummers);
        PalindromeChecker.somArr(new int[]{1, 2, 3});
    }
}