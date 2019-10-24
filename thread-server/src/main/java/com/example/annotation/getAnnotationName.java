package com.example.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @author：张鸿建
 * @time：2019/10/23 16:17
 * @desc：
 **/
public class getAnnotationName {
    public static void main(String[] args) throws NoSuchFieldException {
        Student student = new Student();
        student.setId(1);
        student.setName("AA");
        Field[] declaredFields = student.getClass().getDeclaredFields();
        System.out.println(student.getClass().getDeclaredField("name").getName());
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            System.out.println(name);
        }
    }
}
@Inherited

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface PassField {

}

@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation
{
    String hello() default "gege";
    String world();
    int[] array() default { 2, 4, 5, 6 };
    //    EnumTest.TrafficLamp lamp() ;
//    TestAnnotation lannotation() default @TestAnnotation(value = "ddd");
    Class style() default String.class;
}

@MethodAnnotation(world = "word")
class Student{
    @PassField
    private String name;
    @PassField
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

