package com.infosupport;

import org.junit.jupiter.api.Test;

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
    void name2() {

    }
}