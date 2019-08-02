/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年08月02日 20:22
 */
public class IOCTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);

        Object bean = context.getBean("colorFactoryBean");
        System.out.println("bean类型:  " + bean.getClass());

        Object bean1 = context.getBean("&colorFactoryBean");
        System.out.println("bean类型:  " + bean1.getClass());
    }
}
