package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：张鸿建
 * @time：2019/10/16 16:35
 * @desc：
 **/
@Component
public class MySQLUtils {
    @Autowired
    FreemarkerTest freemarkerTest;

    String mysqlHost, dbname, username, password;
    Connection conn = null;

    /*public MySQLUtils(String mysqlHost, String dbname, String username, String password) {
        this.mysqlHost = mysqlHost;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }*/

    public void setParam(String mysqlHost, String dbname, String username, String password) {
        this.mysqlHost = mysqlHost;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    public void getConnect() {
        try {
            System.out.println("尝试连接数据库 : host=" + mysqlHost + ", dbname=" + dbname + ", username=" + username + ", password=" + password);
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + mysqlHost + ":3306/" + dbname + "?useUnicode=true&characterEncoding=utf8", username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("无法连接数据库 [" + mysqlHost + "] username :" + username + " password :" + password);
        }
    }

    public void queryAllTableNameByDataBase() throws SQLException {
        String queryAllTableSql = "select table_name from information_schema.tables where TABLE_SCHEMA = '" + dbname + "'";
        PreparedStatement ps = conn.prepareStatement(queryAllTableSql);
        ResultSet rstable = ps.executeQuery();
        while (rstable.next()) {
            String table_name = rstable.getString("table_name");
            queryAllTableInfo(table_name, dbname);
        }
    }

    public void queryAllTableInfo(String tbName, String database) throws SQLException {
        String queryAllTableSql = "select column_name, data_type from information_schema.columns where table_name='" + tbName + "' and table_schema='" + database + "'";
        PreparedStatement ps = conn.prepareStatement(queryAllTableSql);
        ResultSet rstable = ps.executeQuery();
        StringBuffer data_type_content = new StringBuffer();
        int flag = 0;
        while (rstable.next()) {
            String column_name = rstable.getString("column_name");
            String data_type = rstable.getString("data_type");
            column_name = column_name.toLowerCase();
            String[] str = column_name.split("_");
            StringBuffer buffer = new StringBuffer();
            String newStr = "";
            for (int i = 1; i < str.length; i++) {
                if (i == 1) {
                    newStr = str[i];
                } else {
                    newStr = str[i].substring(0, 1).toUpperCase() + str[i].substring(1);
                }
                buffer.append(newStr);
            }
            String part = select_data_type(buffer.toString(), data_type);
            if (flag == 0) {
                part = part.replace(",{", "{");
            }
            data_type_content.append(part);
            flag++;
        }
        freemarkerTest.createTemplateFile(tbName.substring(2) + "-*", data_type_content.toString(), dbname);
    }

    public String select_data_type(String column_name, String data_type) {
        String content = ",{\n" +
                "            \"${column_name}_fields\" : {\n" +
                "              \"match\" : \"${column_name}\",\n" +
                "              \"mapping\" : {\n" +
                "                \"type\" : \"${data_type}\"\n" +
                "              }\n" +
                "            }\n" +
                "          }";
        content = content.replace("${column_name}", column_name);
        switch (data_type) {
            case "varchar":
                return content.replace("${data_type}", "keyword");
            case "text":
                return content.replace("${data_type}", "text");
            case "int":
                return content.replace("${data_type}", "long");
            case "datetime":
                return content.replace("${data_type}", "time");
            case "float":
                return content.replace("${data_type}", "float");
            case "timestamp":
                return content.replace("${data_type}", "time");
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        /*MySQLUtils mySQLUtils = new MySQLUtils("10.100.23.92","scrapy","canal","123456","10.100.23.92");
        mySQLUtils.getConnect();
        mySQLUtils.queryAllTableNameByDataBase();*/
    }
}
