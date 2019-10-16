package com.example;

import com.example.util.FreemarkerTest;
import com.example.util.MySQLUtils;
import freemarker.template.TemplateException;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.template.PutTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateServerApplicationTests {
    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() throws IOException, TemplateException, SQLException {
        /*MySQLUtils mySQLUtils = new MySQLUtils("10.100.23.92","scrapy","canal","123456","10.100.23.92");
        mySQLUtils.getConnect();
        mySQLUtils.queryAllTableNameByDataBase();*/
    }


}
