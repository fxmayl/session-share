/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.introspection;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * Description:Java内省<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年07月22日 19:57
 */
public class JavaBeansDemo {
    public static void main(String[] args) throws ClassNotFoundException, IntrospectionException {
        Class<?> clazz = Class.forName("com.my.introspection.User");
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);

        System.out.println("-------------beanDescriptor---------------");
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);

        System.out.println("-------------methodDescriptors---------------");
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);

        System.out.println("-------------propertyDescriptors---------------");
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);

        System.out.println("-------------propertyDescriptors  setValue---------------");
        User user = new User();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            String propertyDescriptorName = propertyDescriptor.getName();
            if ("id".equals(propertyDescriptorName)) {
                propertyDescriptor.setPropertyEditorClass(IdPropertyEditor.class);
                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(user);
                propertyEditor.addPropertyChangeListener(event -> {
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    try {
                        setMethod.invoke(user, ((PropertyEditor)event.getSource()).getValue());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
                propertyEditor.setAsText("2");
            } else if ("birthday".equals(propertyDescriptorName)) {
                propertyDescriptor.setPropertyEditorClass(DatePropertyEditor.class);
                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(user);
                propertyEditor.addPropertyChangeListener(event -> {
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    try {
                        setMethod.invoke(user, ((PropertyEditor)event.getSource()).getValue());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
                propertyEditor.setAsText("2019-07-22 12:12:12");
            } else {
                propertyDescriptor.setPropertyEditorClass(PropertyEditorSupport.class);
                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(user);
                propertyEditor.setAsText("fxm");
            }
        });
        System.out.println(user);

    }
}
