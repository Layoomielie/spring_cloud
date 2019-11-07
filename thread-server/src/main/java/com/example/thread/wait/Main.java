package com.example.thread.wait;

/**
 * @author：张鸿建
 * @time：2019/11/7 11:05
 * @desc：
 **/
public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        Input input = new Input(person);
        OutPut outPut = new OutPut(person);
        input.start();
        outPut.start();
    }
}
