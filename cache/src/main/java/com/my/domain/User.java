/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.domain;

import com.my.annotation.UserField;

import java.util.Date;

import lombok.Data;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月23日 14:35
 */
@Data
public class User {
    private Integer id;

    @UserField(value = "USER_NAME")
    private String username;

    @UserField(value = "TRUE_NAME")
    private String trueName;

    private Date birthday;

    private Integer age;
}
