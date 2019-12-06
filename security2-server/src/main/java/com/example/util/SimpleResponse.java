package com.example.util;

/**
 * @author：张鸿建
 * @time：2019/12/6 15:08
 * @desc：
 **/
public class SimpleResponse {

    /**
     * 返回 内容 （json格式）
     */
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
