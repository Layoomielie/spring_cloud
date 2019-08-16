package com.example.annotation;

import java.lang.reflect.Method;

/**
 * @author：张鸿建
 * @time：2019/7/29 10:26
 * @desc：
 **/
public class MyTargetTest {
    @Mytarget
    public void doSomething()
    {
        System.out.println("hello world");
    }

    public static void main(String[] args) throws Exception
    {
        Method method = MyTargetTest.class.getMethod("doSomething",null);
        if(method.isAnnotationPresent(Mytarget.class))//如果doSomething方法上存在注解@MyTarget，则为true
        {
            System.out.println("调用了注解方法");
            System.out.println(method.getAnnotation(Mytarget.class));
        }
    }
}
