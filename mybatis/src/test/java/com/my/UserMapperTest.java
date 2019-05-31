/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my;

import com.my.domain.User;
import com.my.mapper.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import javax.annotation.Resource;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月31日 17:17
 */
@SpringBootTest(classes = MybatisApplication.class)
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void getAllTest() {
        List<User> all = userMapper.getAll();
        System.out.println(all.size());
    }
}
