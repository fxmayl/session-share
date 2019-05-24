/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.annotation.helper;

import com.my.annotation.UserField;

import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月23日 14:42
 */
public class UserFieldSerializer extends JacksonAnnotationIntrospector {
    @Override
    public boolean isHandled(Annotation ann) {
        return ann.annotationType() == UserField.class;
    }

    @Override
    public String findSerializablePropertyName(AnnotatedField af) {
        UserField userField = af.getAnnotation(UserField.class);
        if (userField != null && !StringUtils.isEmpty(userField.value())) {
            return userField.value();
        }
        return super.findSerializablePropertyName(af);
    }

    @Override
    public String findDeserializablePropertyName(AnnotatedField af) {
        UserField userField = af.getAnnotation(UserField.class);
        if (userField != null && !StringUtils.isEmpty(userField.value())) {
            return userField.value();
        }
        return super.findDeserializablePropertyName(af);
    }
}
