/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.service.impl;

import com.my.domain.User;
import com.my.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月07日 09:36
 */
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public int save(User user) {
        log.info("调用的当前save方法,user对象值为{}", user);
        return 1;
    }
}
