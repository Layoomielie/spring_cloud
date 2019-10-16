package com.example;

import io.searchbox.client.JestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
//exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class}
@SpringBootApplication()
@ConditionalOnClass(JestClient.class)
public class TemplateServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateServerApplication.class, args);
    }

}
