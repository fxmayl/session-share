/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月05日 14:21
 */
@Setter
@Getter
@ToString(exclude = {"content"})
public class Article extends BaseRowModel {
    private Integer id;

    @ExcelProperty(value = "DOCCHANNEL", index = 0)
    private String docChannel;

    @ExcelProperty(value = "CONTENT", index = 1)
    private String content;

    @ExcelProperty(value = "DOCPUBTIME", index = 2)
    private String docPubTime;

    @ExcelProperty(value = "ZB_ZYZXFILD", index = 3)
    private String filed;

    @ExcelProperty(value = "ZB_SOURCE_SITE", index = 4)
    private String sourceSite;

    @ExcelProperty(value = "ZB_KEYWORDS5_CHAR", index = 5)
    private String keyWord;

    @ExcelProperty(value = "ENTITY_NAME", index = 6)
    private String personName;

    @ExcelProperty(value = "ENTITY_PLACE", index = 7)
    private String placeName;

    @ExcelProperty(value = "ENTITY_ORG", index = 8)
    private String organizationName;

    @ExcelProperty(value = "DOCTITLE", index = 9)
    private String docTitle;

    @ExcelProperty(value = "TXS", index = 10)
    private String wordCount;

    public Date getDocPublishTime() throws ParseException {
        return StringUtils.isEmpty(docPubTime) ? null : new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            .parse(docPubTime);
    }

    public Integer getWordCountNum() {
        return Integer.valueOf(StringUtils.isEmpty(wordCount) ? "0" : wordCount);
    }
}
