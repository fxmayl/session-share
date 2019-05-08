/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.my.domain.Article;
import com.my.domain.ExcelListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月06日 09:15
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Slf4j
public class MyTest {
    @Test
    public void test() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("C:\\Users\\zbcm\\Desktop\\article2.xls"));
            AnalysisEventListener<Article> listener = new ExcelListener<>();
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);

            Sheet sheet = new Sheet(1, 1, Article.class);
            reader.read(sheet);

            log.info("结果集大小:{}，第二个时间为:{},第一个字数:{}", ((ExcelListener)listener).getDatas().size(),
                ((Article)(((ExcelListener)listener).getDatas().get(0))).getDocPublishTime(),
                ((Article)(((ExcelListener)listener).getDatas().get(0))));
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
