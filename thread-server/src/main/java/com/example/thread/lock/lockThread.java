package com.example.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Person {
    String name;
    String sex;
    boolean flag;

    Lock lock = new ReentrantLock();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class Input extends Thread {
    Person person;
    Condition condition;
    Input(Person person,Condition condition) {
        this.person = person;
        this.condition=condition;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                person.lock.lock();
                if(person.isFlag()){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count == 0) {
                    person.setName("小黑");
                    person.setSex("男");
                } else {
                    person.setSex("女");
                    person.setName("小红");
                }
                count = (count + 1) % 2;
                person.setFlag(true);
                condition.signal();
            } catch (Exception e) {

            } finally {
                person.lock.unlock();
            }
        }
    }
}

class Output extends Thread {
    Person person;
    Condition condition;
    Output(Person person,Condition condition) {
        this.person = person;
        this.condition=condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                person.lock.lock();
                if(!person.isFlag()){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(person.getName() + " : " + person.getSex());
                person.setFlag(false);
                condition.signal();
            } finally {
                person.lock.unlock();
            }

        }
    }
}

public class lockThread {
    public static void main(String[] args) {
        Person person = new Person();
        Condition condition = person.lock.newCondition();
        Input input = new Input(person,condition);
        Output output = new Output(person,condition);
        input.start();
        output.start();
    }
}
