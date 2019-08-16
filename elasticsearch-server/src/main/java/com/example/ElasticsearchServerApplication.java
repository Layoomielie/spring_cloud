package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootApplication
public class ElasticsearchServerApplication {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public static void main(String[] args) {

        SpringApplication.run(ElasticsearchServerApplication.class, args);
    }

}
