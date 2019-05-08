/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.config;

import com.my.annotation.EnableUserService;
import com.my.service.impl.UserServiceImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月07日 09:40
 */
@Configuration
//@Import(value = {UserServiceImpl.class})
//@Import(value = {UserServiceBeanDefinitionRegistrar.class})
@EnableUserService(name = "test")
public class Config {
}
