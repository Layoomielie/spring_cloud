package com.example.annotation;

/**
 * @author：张鸿建
 * @time：2019/7/29 11:12
 * @desc：
 **/
@MyAnnotation(hello = "beijing", world="shanghai",array={},style=int.class)
public class MyTest
{
    @MyAnnotation( world = "shanghai",array={1,2,3})
    @Deprecated
    @SuppressWarnings("")
    public void output()
    {
        System.out.println("output something!");
    }
}
