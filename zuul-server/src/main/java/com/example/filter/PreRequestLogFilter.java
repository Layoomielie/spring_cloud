package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author：张鸿建
 * @time：2019/7/20 11:51
 * @desc： 自定义zuul过滤器
 **/
public class PreRequestLogFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(PreRequestLogFilter.class);
    @Override
    public String filterType() {
        logger.info("zuul自定义过滤器 ...   filterType");
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        logger.info("zuul自定义过滤器 ...   filterOrder");
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        logger.info("zuul自定义过滤器 ...   shouldFilter");
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("zuul自定义过滤器 ...   run");
        logger.info(String.format("send %s request to %s  ...",request.getMethod(),request.getRequestURL().toString()));
        return null;
    }
}
