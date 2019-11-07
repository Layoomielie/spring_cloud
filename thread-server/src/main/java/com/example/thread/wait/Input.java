package com.example.thread.wait;

/**
 * @author：张鸿建
 * @time：2019/11/7 10:52
 * @desc：
 **/
public class Input extends Thread {
    Person person;

    Input(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        int count=0;
        while (true){
        synchronized (person) {
            try {
                if (!person.isFlag()) {
                    person.wait();
                }

                if(count%2==1){
                    person.setPerson("李哥");
                    person.setSex("公");
                }else {
                    person.setPerson("雅姐");
                    person.setSex("母");
                }
                count++;
                person.setFlag(false);
                person.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }}
    }
}
