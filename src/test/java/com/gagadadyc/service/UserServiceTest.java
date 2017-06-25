package com.gagadadyc.service;

import com.gagadadyc.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by gagada on 2017/4/29.
 */

//使用注解启动Spring容器
@ContextConfiguration(locations = {"/spring/applicationContext.xml"})
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomerService userService;

    @Test
    public void hasLoginCustomer() {
        boolean b1 = userService.hasLoginCustomer("9628", "123456");
        boolean b2 = userService.hasLoginCustomer("9628", "1111");
        Assert.assertTrue(b1);
        Assert.assertTrue(!b2);
    }

    @Test
    public void findUserinfo() {

        Customer customer = userService.findUserInfo("9628");
        Assert.assertEquals(customer.getName(),"11");
    }

}
