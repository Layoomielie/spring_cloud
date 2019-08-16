package com.example.insecurity.demo1;

/**
 * @author：张鸿建
 * @time：2019/8/13 16:38
 * @desc：不共享数据情况下  各个线程拥有独立的值
 **/
public class Mythread  extends Thread{
    private int count = 5 ;

    public Mythread(String  name) {
        super();
        // 设置线程名称
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count>0){
            count--;
            System.out.println("线程名为："+this.currentThread().getName()+" 计算 ： count="+count);
        }
    }
}
