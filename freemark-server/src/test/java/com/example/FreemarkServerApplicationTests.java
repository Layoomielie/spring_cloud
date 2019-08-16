package com.example;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreemarkServerApplicationTests {

    @Test
    public void contextLoads() {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        try {
            cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
