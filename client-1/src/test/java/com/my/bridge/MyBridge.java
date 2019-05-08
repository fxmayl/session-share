/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.bridge;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年04月29日 14:46
 */
public class MyBridge extends Bridge {
    @Override
    public void method() {
        getSourceable().method();
    }

    public static void main(String[] args) {
        MyBridge myBridge = new MyBridge();

        FirstSource firstSource = new FirstSource();
        myBridge.setSourceable(firstSource);
        myBridge.method();

        SecondSource secondSource = new SecondSource();
        myBridge.setSourceable(secondSource);
        myBridge.method();
    }
}
