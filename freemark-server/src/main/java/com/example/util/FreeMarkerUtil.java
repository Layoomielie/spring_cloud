package com.example.util;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/25 15:43
 */

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

/**
 * @author：张鸿建
 * @time：2019/6/25
 * @desc：
 **/
public class FreeMarkerUtil {
    private FreeMarkerUtil() {
    }

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

    static {
        //这里比较重要，用来指定加载模板所在的路径
        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/templates"));
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }

    public static Template getTemplate(String templateName) throws IOException {
        Template template = null;
        try {
            template = CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            throw e;
        }
        return template;
    }

    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }


}
