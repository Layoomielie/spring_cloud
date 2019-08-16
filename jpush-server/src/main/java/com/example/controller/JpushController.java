package com.example.controller;

import cn.jpush.api.push.PushResult;
import com.example.service.JpushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/6/28 15:51
 * @desc：极光推送接口
 **/
@Controller
@RequestMapping("/jpush")
public class JpushController {

    @Autowired
    JpushService jpushService;

    /**
      *
      * @author：张鸿建
      * @date: 2019/6/28
      * @desc: 自定义推送
      **/
    @RequestMapping("/custom/push")
    public PushResult sendCustomPush(String title, String content, Map<String, String> extrasMap, String[] alias){
       return jpushService.sendCustomPush(title,content,extrasMap,alias);
    }
    /**
      *
      * @author：张鸿建
      * @date: 2019/6/28
      * @desc: 原生推送方式
      **/
    @RequestMapping("/origin/push")
    public PushResult sendPush(String title, String content, Map<String, String> extrasMap, String[] alias) {
            return jpushService.sendPush( title, content,extrasMap, alias);
    }

    /**
      *
      * @author：张鸿建
      * @date: 2019/6/28
      * @desc: 异步推送
      **/
    @RequestMapping("/callback/push")
    public void sendPushWithCallback(String title, String content, Map<String, String> extrasMap, String[] alias) {
        jpushService.sendPushWithCallback(title,content,extrasMap,alias);
    }

}
