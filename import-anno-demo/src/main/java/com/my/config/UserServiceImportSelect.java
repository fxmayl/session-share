/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.config;

import com.my.annotation.EnableUserService;
import com.my.service.impl.UserServiceImpl;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月07日 11:58
 */
public class UserServiceImportSelect implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        MultiValueMap<String, Object> attributes =
            annotationMetadata.getAllAnnotationAttributes(EnableUserService.class.getName(), true);
        for (Map.Entry<String, List<Object>> entry : attributes.entrySet()) {
            System.out.println("key is : " + entry.getKey() + "   value is : " + entry.getKey());
        }
        return new String[]{UserServiceImpl.class.getName()};
    }
}
