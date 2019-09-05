package com.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author：张鸿建
 * @time：2019/7/29 11:12
 * @desc：
 **/
public class MyReflection {
    public static void main(String[] args) throws Exception {
        Class<MyTest> c = MyTest.class;
        Method method = c.getMethod("output", new Class[]{});
        MyTest myTest = new MyTest();
        myTest.setAa("AAAAA");
        myTest.setBb("BBBBB");
        myTest = new MyReflection().getClear(myTest);
        /*Field[] declaredFields = myTest.getClass().getDeclaredFields();

        System.out.println(declaredFields.length);*/
        String name = "";
       /* for (Field field : declaredFields) {
            System.out.println("进入 field ------------");
            if(field.isAnnotationPresent(PassTerm.class)){
                System.out.println("fieldName : "+field.getName());
                name=field.getName();
                Class<?> type = field.getType();
                field.setAccessible(true);
                field.set(myTest,null);

            }
        }*/
        //Field field = myTest.getClass().getDeclaredField(name);
        System.out.println(myTest.toString());
        System.out.println("------------------------");
        //如果MyTest类名上有注解@MyAnnotation修饰，则为true
        if (MyTest.class.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("have annotation");
        }
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            method.invoke(myTest, null); //调用output方法
            //获取方法上注解@MyAnnotation的信息
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);

            String hello = myAnnotation.hello();
            String world = myAnnotation.world();
            System.out.println(hello + ", " + world);//打印属性hello和world的值
            System.out.println(myAnnotation.array().length);//打印属性array数组的长度
            System.out.println(myAnnotation.style());
        }
        //得到output方法上的所有注解，当然是被RetentionPolicy.RUNTIME修饰的
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName());
        }
    }

    private MyTest getClear(MyTest entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(PassTerm.class)) {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    switch (type.getName()) {
                        case "int":
                            field.setInt(entity, 0);
                            break;
                        case "double":
                            field.setDouble(entity, 0);
                            break;
                        case "float":
                            field.setFloat(entity, 0);
                            break;
                        case "long":
                            field.setLong(entity, 0);
                            break;
                        default:
                            field.set(entity, null);
                            break;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
