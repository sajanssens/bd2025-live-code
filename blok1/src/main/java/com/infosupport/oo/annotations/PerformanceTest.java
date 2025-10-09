package com.infosupport.oo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Repeatable(PerformanceTests.class)
public @interface PerformanceTest {
    int numberOfTries();
    int maxTime();
}
