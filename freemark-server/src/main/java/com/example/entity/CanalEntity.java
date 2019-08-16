package com.example.entity;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/25 15:48
 */

/**
 * @author：张鸿建
 * @time：2019/6/25
 * @desc：
 **/
public class CanalEntity {
    String sourceKey;
    String indexName;
    String sql;

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
