package com.example.controller;

import com.example.util.MySQLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author：张鸿建
 * @time：2019/10/16 19:27
 * @desc：
 **/
@RestController
public class FreemarkerController {

    @Autowired
    MySQLUtils mySQLUtils;

    @GetMapping("index/database")
    public void createTemplate(String mysqlHost, String dbName, String userName, String password, int flag) {
        mySQLUtils.setParam(mysqlHost, dbName, userName, password, flag);
        mySQLUtils.getConnect();
        try {
            mySQLUtils.queryAllTableNameByDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("index/table")
    public void createTableTemplate(String dbName, String tbName, int flag) {
        String mysqlHost = "10.100.23.106";
        String userName = "bin_log";
        String password = "Js!jfh3#jsd8Gsp0";

        mysqlHost = "rm-uf6h10w84d8f1wy4p7o.mysql.rds.aliyuncs.com";
        userName = "root";
        password = "Sink44253432";
        mySQLUtils.setParam(mysqlHost, dbName, userName, password, flag);
        mySQLUtils.getConnect();

        try {
            mySQLUtils.queryAllTableInfo(tbName, dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
