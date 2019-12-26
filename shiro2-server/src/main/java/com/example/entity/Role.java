package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:39
 * @desc：
 **/
@Data
public class Role implements Serializable {

    private String roleName;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
