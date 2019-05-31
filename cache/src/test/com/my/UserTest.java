package com.my;

import com.my.domain.UserConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-05-27 21:21
 **/
@SpringBootTest(classes = CacheApplication.class)
@RunWith(SpringRunner.class)
public class UserTest {

    @Test
    public void test() {
        System.out.println("用户真实姓名: " + UserConstants.TRUE_NAME);
        System.out.println("用户名称: " + UserConstants.USER_NAME);
    }
}
