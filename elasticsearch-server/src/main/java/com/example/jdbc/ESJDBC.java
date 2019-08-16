package com.example.jdbc;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/5/23 10:24
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author：张鸿建
 * @time：2019/5/23
 * @desc：
 **/
public class ESJDBC {
    static String  driver = "org.elasticsearch.xpack.sql.jdbc.jdbc.JdbcDriver";
    static String elasticsearchAddress = "10.100.23.92:9200";

    public static Properties connectionProperties(){
        Properties properties = new Properties();
//        properties.put("user", "test_admin");
//        properties.put("password", "x-pack-test-password");
        return properties;
    }

   /* public static void main(String[] args) {

        String address = "jdbc:es://http://" + elasticsearchAddress;
        Properties connectionProperties = connectionProperties();
        try  {
            Connection connection = DriverManager.getConnection(address, connectionProperties);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(
                    "SELECT * FROM goods");
            while(results.next()){
                System.out.println(results.getString("price"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }*/
}
