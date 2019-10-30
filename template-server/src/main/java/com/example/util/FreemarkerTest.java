package com.example.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.template.PutTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author：张鸿建
 * @time：2019/10/16 16:16
 * @desc：
 **/
@Component
public class FreemarkerTest {

    @Autowired
    JestClient jestClient;

    static Template template;

    static {
        //freemarker的配置类
        Configuration configuration = new Configuration();
        //因为是springBoot项目，指定模板在templates路径下
        configuration.setClassForTemplateLoading(configuration.getClass(), "/templates");
        try {
            //获取模板类
            template = configuration.getTemplate("es.txt", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createTemplateFile(String patterns, String data_type_content, String dbName) {
        //给定xml文件输出路径
        Writer writer = null;
        String path = "E:/test/" + dbName + "/";
        String tbName = patterns.replace("-*", "");
        try {
            StringWriter stringWriter = new StringWriter();
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            writer = new FileWriter(path + tbName + ".txt");
            //按照用法，来个map集合
            Map<String, String> map = new HashMap<String, String>();
            map.put("patterns", tbName);
            map.put("data_type_content", data_type_content);
            //调用process方法
            template.process(map, writer);
            template.process(map, stringWriter);
            createTemplate(tbName,stringWriter.toString());
            System.out.println(tbName + "文件生成成功");
            writer.flush();
            writer.close();
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createTemplate(String tbName, String contengTemplate) {
        JestResult jestResult = null;
        try {
            jestResult = jestClient.execute(new PutTemplate.Builder(tbName, contengTemplate).build());
            System.out.println("result:" + jestResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
