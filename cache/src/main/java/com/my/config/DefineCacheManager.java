///*
// * Copyright: 浙报传媒控股集团有限公司版权所有
// * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
// */
//
//package com.my.config;
//
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//
///**
// * Description:默认使用内存进行缓存<BR>
// *
// * @author 方小明
// * @version 1.0.0
// * @department 产品研发中心
// * @date 2019年05月16日 10:18
// */
//public class DefineCacheManager extends ConcurrentMapCacheManager {
//    private long expireTime = 30;
//
//    private long maximumSize = 100;
//
//    public DefineCacheManager(long expireTime, long maximumSize) {
//        if (expireTime > 0) {
//            this.expireTime = expireTime;
//        }
//        if (maximumSize > 0) {
//            this.maximumSize = maximumSize;
//        }
//    }
//}
