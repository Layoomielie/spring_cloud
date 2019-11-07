package com.example.thread.wait;

/**
 * @author：张鸿建
 * @time：2019/11/7 10:52
 * @desc：
 **/
public class OutPut extends Thread {
    Person person;

    public OutPut(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
        synchronized (person) {
            try {
                if (person.isFlag()) {
                    person.wait();
                }
                System.out.println(person.getPerson()+"  "+person.getSex());
                person.setFlag(true);
                person.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }}
    }
}
