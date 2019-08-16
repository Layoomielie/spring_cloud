package com.example.service;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/21 19:48
 */

import com.example.entity.CanalEntity;
import com.example.util.DataBaseUtil;
import com.example.util.FileUtil;
import com.example.util.FreeMarkerUtil;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author：张鸿建
 * @time：2019/6/21
 * @desc：
 **/
@Service
public class FreeMarkerService {

    String currentFolderPath, host, dbname, username, password, sourceKey;

    DataBaseUtil dataBaseUtil;

    /*public static void main(String[] args) {
        if (args.length < 6) {
            help();
            System.exit(-1);
        }
        String currentFolderPath = args[0];
        String host = args[1];
        String username = args[2];
        String password = args[3];
        String dbname = args[4];
        String sourceKey = args[5];
        String type = args[6];

        FreeMarkerService e = new FreeMarkerService(currentFolderPath, host, username, password, dbname, sourceKey);

    }*/

    public FreeMarkerService(String currentFolderPath, String host, String dbname, String username, String password, String sourceKey) {
        this.currentFolderPath = currentFolderPath;
        this.host = host;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        this.sourceKey = sourceKey;
        this.dataBaseUtil = new DataBaseUtil(host,dbname,username,password);
    }

    public static void help() {
        System.out.println("参数：[mysql服务器ip地址] [账号]  [密码]");
    }


    public void writefile(){
        CanalEntity canalEntity = new CanalEntity();
        canalEntity.setIndexName("index");
        canalEntity.setSourceKey("key");
        canalEntity.setSql("select * from key");
        try {
            Template temp = FreeMarkerUtil.getTemplate("canal.ftl");
            FileWriter fw = FileUtil.getfileStream("D:\\template\\hello.yml");
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(canalEntity, bw);
            bw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
