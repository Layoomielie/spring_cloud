package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author zhanghongjian
 * @Date 2019/5/5 19:56
 * @Description
 */
@Document(indexName = "lano-t_course",type = "doc")
public class Course {
    private String c_classify_name;
    private String c_name;
    @Id
    private String c_id;

    public Course() {
    }

    public Course(String c_classify_name, String c_name, String c_id) {
        this.c_classify_name = c_classify_name;
        this.c_name = c_name;
        this.c_id = c_id;
    }

    public String getC_classify_name() {
        return c_classify_name;
    }

    public void setC_classify_name(String c_classify_name) {
        this.c_classify_name = c_classify_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
