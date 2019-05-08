/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.adapter;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年04月29日 11:23
 */
public class Adapter extends Source implements Target {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    public static void main(String[] args) {
        Target target = new Adapter();
        target.method1();
        target.method2();
    }
}
