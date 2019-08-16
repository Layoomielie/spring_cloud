package com.example.entity;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/25 16:08
 */

/**
 * @author：张鸿建
 * @time：2019/6/25
 * @desc：
 **/
public class LogstashJdbcEntity {
    String jdbcId;
    String jdbcConnect;
    String userName;
    String password;
    String sql;
    String indexName;

    public String getJdbcId() {
        return jdbcId;
    }

    public void setJdbcId(String jdbcId) {
        this.jdbcId = jdbcId;
    }

    public String getJdbcConnect() {
        return jdbcConnect;
    }

    public void setJdbcConnect(String jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}
