package com.example.config;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/5/25 13:34
 */

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author：张鸿建
 * @time：2019/5/25
 * @desc：
 **/
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {



    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
    builder.up().withDetail("app", "Alive and Kicking")
            .withDetail("error", "Nothing! I'm good.");
    }
}
