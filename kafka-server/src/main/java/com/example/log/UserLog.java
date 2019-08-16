package com.example.log;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhanghongjian
 * @Date 2019/5/6 10:54
 * @Description
 */
@Data
@Accessors(chain = true)
public class UserLog {
    private String username;
    private String userid;
    private String state;

    public UserLog() {
    }

    public UserLog(String username, String userid, String state) {
        this.username = username;
        this.userid = userid;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
