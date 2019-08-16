package com.example.insecurity.demo2;

/**
 * @author：张鸿建
 * @time：2019/8/13 16:52
 * @desc：
 **/
public class MyThread extends  Thread{
     private Integer count=5;

     // synchronized  synchronized 确保多个线程 只能有一个在执行这个方法
    @Override
    synchronized public void run() {
        super.run();
        count--;

        System.out.println("当前对象名称为： "+this.getName()+"  线程名为："+this.currentThread().getName()+" 计算 ： count="+count);
    }
}
