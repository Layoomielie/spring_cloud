package com.example.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.example.entity.Person;
import com.example.entity.User;
import com.example.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author：张鸿建
 * @time：2019/10/28 16:17
 * @desc：
 **/
@Api(description = "用户服务")
@RestController
@RequestMapping("/users/")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("导入excel")
    @PostMapping("import-excel")
    public Map<String, Object> importExcel(@RequestParam("file") MultipartFile file){
        Map<String, Object> result = new HashMap<>();
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<User> userList = ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);

            /*List<Map> list = new ArrayList<>();
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("刘黑黑","可恶的刘嘿嘿");
            list.add(hashMap);
            Workbook sheets = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);*/
            userList.forEach(o -> System.out.println(o.toString()));
        } catch (Exception e) {
            result.put("code",HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.put("message",e.getMessage());
            log.error("导入excel{}", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response) throws Exception {

        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞","1",new Date());
        Person person2 = new Person("娜美","2", new Date());
        Person person3 = new Person("索隆","1", new Date());
        Person person4 = new Person("小狸猫","1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        ExcelUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);
    }
}