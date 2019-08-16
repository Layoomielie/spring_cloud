package com.example.fallback;

import com.example.feign.UserFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhanghongjian
 * @Date 2019/5/10 9:51
 * @Description
 */
@Component
public class HystrixClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);


    @Override
    public UserFeignClient create(Throwable throwable) {
        return new HystrixClientWithFallbackFactory() {
            @Override
            public String home(String name) {
                logger.warn("this is feign hystrix : ",throwable);
                return "this is feign hystrix";
            }
        };
    }
}
