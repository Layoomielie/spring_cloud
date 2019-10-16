package com.example;

import io.searchbox.client.JestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootApplication
@ConditionalOnClass(JestClient.class)
public class ElasticsearchServerApplication {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public static void main(String[] args) {

        SpringApplication.run(ElasticsearchServerApplication.class, args);
    }

}
