package com.example.feign;

import com.example.fallback.HystrixClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhanghongjian
 * @Date 2019/5/9 14:52
 * @Description
 */
@FeignClient(name = "hi-service",fallbackFactory = HystrixClientFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String home (@RequestParam String name);
}
