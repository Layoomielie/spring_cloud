package com.example.stack;

/**
 * @author：张鸿建
 * @time：2019/8/13 17:50
 * @desc：  输出堆栈信息
 **/
public class Test1 {
    public void a() {
        b();
    }

    public void b() {
        c();
    }

    public void c() {
        d();
    }

    public void d() {
        e();
    }

    public void e() {
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                StackTraceElement stackTraceElement = array[i];
                System.out.println("className: " + stackTraceElement.getClassName() + " methodName: " + stackTraceElement.getMethodName() + " fileName: " + stackTraceElement.getFileName() + " lineNum: " + stackTraceElement.getLineNumber());
            }
        }
    }

    public static void main(String[] args) {
        new Test1().a();
    }
}
