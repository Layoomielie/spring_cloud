package com.example.stack;

/**
 * @author：张鸿建
 * @time：2019/8/13 18:00
 * @desc： 仅用于调试 将堆栈信息输出到错误流
 **/
public class DumpStack {
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
    public  void e(){
        int age=0;
        age=100;
        if(age==100){
            Thread.dumpStack();
        }
    }

    public static void main(String[] args) {
        new DumpStack().a();
    }
}
