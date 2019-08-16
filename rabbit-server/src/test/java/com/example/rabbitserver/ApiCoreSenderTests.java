package com.example.rabbitserver;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 13:46
 */

import com.example.rabbitserver.provider.ApiCoreSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：张鸿建
 * @time：2019/6/11
 * @desc：
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiCoreSenderTests {
    @Autowired
    private ApiCoreSender sender;

    @Test
    public void test_user() {
        sender.user("用户管理！");
    }

    @Test
    public void test_userQuery() {
        sender.userQuery("查询用户信息！");
    }
}
