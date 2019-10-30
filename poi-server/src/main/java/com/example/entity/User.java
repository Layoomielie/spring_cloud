package com.example.entity;
import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * @author：张鸿建
 * @time：2019/10/28 16:14
 * @desc：
 **/
public class User {

    @Excel(name = "姓名", orderNum = "0")
    private String name;

    @Excel(name = "性别", replace = {"男_1", "女_0"}, orderNum = "1")
    private String sex;

    @Excel(name = "出生日期", exportFormat = "yyyy-MM-dd", orderNum = "2")
    private Date birthday;

    // 请自行添加get/set方法


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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
