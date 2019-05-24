/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.annotation.helper;

import com.my.domain.User;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月23日 15:03
 */
public class JsonUtils {
    private final static ObjectMapper mapper =
        new ObjectMapper().setAnnotationIntrospector(new UserFieldSerializer());

    public static String toJson(User user) throws IOException {
        mapper.setDateFormat(new UserDateFormat());
        return mapper.writeValueAsString(user);
    }

    public static User toUser(String json) throws IOException {
        mapper.setDateFormat(new UserDateFormat());
        return mapper.readValue(json, User.class);
    }

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setId(1);
        user.setUsername("fangxiaoming");
        user.setTrueName("fangxiaoming");
        user.setBirthday(new Date());
        user.setAge(1);
        System.out.println(JsonUtils.toJson(user));

        String json = new String(
            "{\"id\":1,\"USER_NAME\":\"fangxiaoming\",\"TRUE_NAME\":\"fangxiaoming\",\"birthday\":1558595308694,\"age\":1}"
                .getBytes(), "UTF-8");
        System.out.println(JsonUtils.toUser(json));
    }
}
