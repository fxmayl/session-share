/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.annotation.helper;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月24日 14:06
 */
@Slf4j
public class UserDateFormat extends DateFormat {
    private static final long serialVersionUID = -3345511897639777195L;

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return new StringBuffer(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
        } catch (ParseException e) {
            log.info("不正确的日期格式!", e);
            throw new IllegalArgumentException("不正确的日期格式!");
        }
    }

    @Override
    public Object clone() {
        return new UserDateFormat();
    }
}
