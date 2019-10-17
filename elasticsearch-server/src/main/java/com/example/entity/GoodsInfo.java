package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author zhanghongjian
 * @Date 2019/4/25 14:01
 * @Description
 */

@Document(indexName = "goods-*", type = "doc")
//@Mapping(mappingPath = "goods.json")
public class GoodsInfo implements Serializable {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    //@Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    @Field(ignoreFields = "name")
    private String name;
    private Integer price;

    private String time;
    @Field(ignoreFields = {"description"}, type = FieldType.Keyword, store = false, index = false)
    private String description;

    public GoodsInfo(Integer id, String name, Integer price, String time, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.time = time;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    /*public GoodsInfo(Long id, String name, Integer price, Date time, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.time = time;
        this.description = description;
    }*/

    /*public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }*/

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoodsInfo() {
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
