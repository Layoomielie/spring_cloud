package com.example.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author：张鸿建
 * @time：2019/7/20 14:19
 * @desc：
 **/
@Component
public class MyFallbackProvider implements FallbackProvider {

    Logger logger = LoggerFactory.getLogger(MyFallbackProvider.class);
    @Override
    public String getRoute() {
        // 表名为哪个微服务提供回退   *表示全部
        return "*";
    }


    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
       if(cause instanceof HystrixTimeoutException){
           return response(HttpStatus.GATEWAY_TIMEOUT);
       }else {
           return this.fallbackResponse();
       }
    }
    public ClientHttpResponse fallbackResponse(){
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus status){
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                logger.info("自定义zuul回退  .....");
                return new ByteArrayInputStream("服务不可用 请稍后再试 ！！".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mediaType);
                return headers;
            }
        };

    }
}
