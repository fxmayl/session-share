/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年08月02日 20:22
 */
@Configuration
public class MainConfig {

    /**
     * 默认根据getBean("colorFactoryBean")获取到的是getObject()所返回的bean
     *
     *
     * getBean("&colorFactoryBean")可以获得colorFactoryBean   bean
     *
     * @return
     */
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
