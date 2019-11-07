package com.example.thread.wait;

/**
 * @author：张鸿建
 * @time：2019/11/7 10:52
 * @desc：
 **/
public class Person {
    String Person;
    String sex;
    boolean flag=true;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
