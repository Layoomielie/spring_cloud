package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.annotation.PassToken;
import com.example.annotation.UserLoginToken;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.Cons;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhanghongjian
 * @Date 2019/5/8 9:23
 * @Description
 */
@RestController
public class hiController {

    @Value("${server.port}")
    String port;

    @Autowired
    UserService userService;

    @Autowired
    Cons cons;

    @RequestMapping("/hi")
    @PassToken
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }

    @RequestMapping("/getJson")
    @PassToken
    public Object getJson() {
        return cons.getAudiences();
    }



    //登录
    @PostMapping("/login")
    public Object login(@RequestBody User userForBase) {
        JwtUtil jwtUtil = new JwtUtil();
        JSONObject jsonObject = new JSONObject();
        if (userForBase == null) {
            jsonObject.put("message", "检查参数 ......");
            return jsonObject;
        } else {
            if (!userForBase.getPassword().equals("44253432")) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = jwtUtil.getToken();
                jsonObject.put("token", token);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }


}
