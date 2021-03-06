/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年04月25日 09:13
 */
@RestController
public class Client2Controller {
    @Value("${server.port}")
    private int port;

    @RequestMapping("/createSession")
    public String createSession(HttpSession session, @RequestParam("name") String name) {
        session.setAttribute("name", name);
        return "当前项目端口为:" + port + "   当前sessionId为:" + session.getId() + "  成功创建session!";
    }

    @RequestMapping("/getSession")
    public String getSession(HttpSession session) {
        return "当前项目端口为:" + port + "   当前sessionId为:" + session.getId() + "  获取姓名为:" +
            session.getAttribute("name");
    }

}
