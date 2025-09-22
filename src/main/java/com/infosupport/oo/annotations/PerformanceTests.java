package com.infosupport.oo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface PerformanceTests {
    PerformanceTest[] value();
}
