package com.gagadadyc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by gagada on 2017/5/1.
 */
@ContextConfiguration(locations = {"/spring/applicationContext.xml"})
public class BuyWaterServiceTest {

    @Autowired
    private BuyWaterService buyWaterService ;


    @Test
    public void buyWaterInfo(){

        Assert.assertNotNull(buyWaterService.buyWaterInfo());
    }
}
