/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.domain;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月05日 14:37
 */
@Slf4j
public class Main {
    //    public static void main(String[] args) {
    //        InputStream inputStream = null;
    //        try {
    //            inputStream = new FileInputStream(new File("C:\\Users\\zbcm\\Desktop\\article2.xls"));
    //            AnalysisEventListener<Article> listener = new ExcelListener<>();
    //            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
    //
    //            Sheet sheet = new Sheet(1, 1, Article.class);
    //            reader.read(sheet);
    //
    //            log.info("结果集大小:{},第一个时间为:{},第一个字数:{}", ((ExcelListener)listener).getDatas().size(),
    //                ((Article)(((ExcelListener)listener).getDatas().get(0))).getDocPublishTime(),
    //                ((Article)(((ExcelListener)listener).getDatas().get(0))).getWordCountNum());
    //        } catch (FileNotFoundException | ParseException e) {
    //            e.printStackTrace();
    //        } finally {
    //            if (inputStream != null) {
    //                try {
    //                    inputStream.close();
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //        }
    //
    //    }

    //    public static void main(String[] args) throws FileNotFoundException {
    //        FileOutputStream outputStream =
    //            new FileOutputStream(new File("C:\\Users\\zbcm\\Desktop\\article1.xlsx"));
    //        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
    //        Sheet sheet = new Sheet(1, 0, Article.class);
    //        writer.write(null, sheet);
    //        writer.finish();
    //    }

    public static void main(String[] args) throws IOException {
        List<String[]> list = new ArrayList<>();
        CsvReader reader =
            new CsvReader("C:\\Users\\zbcm\\Desktop\\dev.csv", ',', Charset.forName("UTF-8"));
        reader.readHeaders();

        while (reader.readRecord()) {
            System.out.println(reader.getRawRecord());
            String[] values = reader.getValues();
            list.add(values);
        }
        reader.close();

        System.out.println("结果集大小:" + list.size() + "第一个时间为:" + list.get(0)[0]);
    }
}
