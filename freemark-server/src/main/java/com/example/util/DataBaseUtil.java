package com.example.util;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/25 15:52
 */

import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：张鸿建
 * @time：2019/6/25
 * @desc：
 **/
public class DataBaseUtil {
    String host, dbname, username, password;

    public DataBaseUtil(String host, String dbname, String username, String password) {
        this.host = host;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    /**
      * @author：张鸿建
      * @date: 2019/6/25
      * @desc:  获取数据库连接
      **/
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("尝试连接数据库 : host=" + this.host + ", dbname=" + this.dbname + ", username=" + this.username + ", password=" + this.password);
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + dbname + "?useUnicode=true&characterEncoding=utf8", username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("无法连接数据库 [" + host + "] username :" + username + " password :" + password);
        }
        return conn;
    }
    
    /**
      * @author：张鸿建
      * @date: 2019/6/25
      * @desc:  获取数据库表名称
      **/
    public static List<String> getTableNames(Connection conn) throws Exception {

        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getString("TABLE_NAME"));
        }
        return result;
    }
    
    /**
      * @author：张鸿建
      * @date: 2019/6/25
      * @desc:  获取所有字段名  并加上驼峰
      **/
    protected static String createSelectSQL(Connection conn, String tableName) throws Exception {
        String sql = "select * from " + tableName + " limit 0, 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rstable = ps.executeQuery();

        // 结果集元数据
        ResultSetMetaData meta = rstable.getMetaData();

        List<String> fields = new ArrayList<>();

        // 表列数量
        int columeCount = meta.getColumnCount();
        for (int i = 1; i <= columeCount; i++) {
            fields.add(BaseUtils.convertCamelCase(BaseUtils.cutFirst(meta.getColumnName(i).toLowerCase())));
        }

        String fieldStr = StringUtils.join(fields.toArray(), ",");

        return "select " + fieldStr + " from " + tableName;
    }


}
