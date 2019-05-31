package com.my.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-05-27 21:15
 **/
@Component
public class UserConstants {
    public static String USER_NAME;

    public static String TRUE_NAME;

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct注解方法!");
    }

    public UserConstants() {
        System.out.println("常量类构造函数!");
    }

    private String getUserName() {
        return USER_NAME;
    }

    @Value("${user.username}")
    private void setUserName(String userName) {
        System.out.println("注入username!");
        USER_NAME = userName;
    }

    private String getTrueName() {
        return TRUE_NAME;
    }

    @Value("${user.trueName}")
    private void setTrueName(String trueName) {
        System.out.println("注入trueName!");
        TRUE_NAME = trueName;
    }
}
