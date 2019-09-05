package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author：张鸿建
 * @time：2019/8/28 9:25
 * @desc：
 **/
@Inherited

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PassTerm {

}
