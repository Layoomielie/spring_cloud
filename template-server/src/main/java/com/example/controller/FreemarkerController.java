package com.example.controller;

import com.example.util.MySQLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

/**
 * @author：张鸿建
 * @time：2019/10/16 19:27
 * @desc：
 **/
@Controller
public class FreemarkerController {

    @Autowired
    MySQLUtils mySQLUtils;

    @GetMapping("index/database")
    public void createTemplate(String mysqlHost, String dbName, String userName, String password) {
        mySQLUtils.setParam(mysqlHost, dbName, userName, password);
        mySQLUtils.getConnect();
        try {
            mySQLUtils.queryAllTableNameByDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("index/table")
    public void createTableTemplate(String tbName) {
        String mysqlHost="10.100.23.106";
        String dbName="lano_gxcentro";
        String userName="bin_log";
        String password="Js!jfh3#jsd8Gsp0";
        mySQLUtils.setParam(mysqlHost, dbName, userName, password);
        mySQLUtils.getConnect();
        try {
            mySQLUtils.queryAllTableInfo(tbName,dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
