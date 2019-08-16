package com.example.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author：张鸿建
 * @time：2019/7/29 11:08
 * @desc：
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation
{
    String hello() default "gege";
    String world();
    int[] array() default { 2, 4, 5, 6 };
//    EnumTest.TrafficLamp lamp() ;
//    TestAnnotation lannotation() default @TestAnnotation(value = "ddd");
    Class style() default String.class;
}
