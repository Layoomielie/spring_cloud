package com.example.controller;

import com.example.util.ReturnResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:09
 * @desc：
 **/
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("login")
    public ReturnResult login(String username, String password) {
        logger.info("用户名：" + username + " 密码：" + password + "  开始登陆");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ReturnResult.errorMessage("账号或密码为空！");
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String failMsg = "";
        Map<Object, Object> hashMap = new HashMap<>();
        try {
            subject.login(token);
            hashMap.put("token", subject.getSession().getId());
            return ReturnResult.ok(hashMap);
        } catch (UnknownAccountException e) {
            failMsg = "用户不存在";
        } catch (IncorrectCredentialsException e) {
            failMsg = "密码错误！";
        } catch (LockedAccountException e) {
            failMsg = "登录失败，该用户已被冻结";
        } catch (Exception e) {
            logger.error("系统内部异常！！{}", e);
            return ReturnResult.error(e);
        }
        return ReturnResult.error(failMsg);
    }

    @GetMapping("unAuth")
    public ReturnResult unAuth(){
        return ReturnResult.error("未授权！");
    }
}
