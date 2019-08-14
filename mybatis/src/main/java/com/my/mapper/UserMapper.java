/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.mapper;

import com.my.domain.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月31日 17:00
 */
@Mapper
public interface UserMapper {

    List<User> getAll();

    /**同时使用@Select与xml时，抛出异常Mapped Statements collection already contains value for com.my.mapper.UserMapper.getUserById*/
//    @Select("SELECT * FROM USER WHERE id = 1")
//    @ResultMap("com.my.mapper.UserMapper.userMap")
    User getUserById(int id);
}
