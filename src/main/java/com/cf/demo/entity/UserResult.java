package com.cf.demo.entity;

import java.util.List;


public class UserResult {
    public int code;
    public String message;

    public UserResult() {
    }

    public UserResult(int code) {
        this.code = code;
    }

    public UserResult(int code, String msg) {
        this.code = code;
        this.message = msg;

    }

    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
