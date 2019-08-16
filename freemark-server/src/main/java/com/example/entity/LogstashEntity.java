package com.example.entity;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/25 16:07
 */

/**
 * @author：张鸿建
 * @time：2019/6/25
 * @desc：
 **/
public class LogstashEntity {
    private String  inputText;
    private String esHost;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getEsHost() {
        return esHost;
    }

    public void setEsHost(String esHost) {
        this.esHost = esHost;
    }
}
