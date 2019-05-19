/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月16日 08:56
 */
@RestController
@Slf4j
public class CacheController {
    @GetMapping("/test")
    @Cacheable(value = "test", key = "targetClass+ '->' + methodName + '->' + #p0")
    public String test(String name) {
        log.info("打印日志，没有使用缓存!姓名: {} ", name);
        return "Hello " + name;
    }
}
