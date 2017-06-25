package com.gagadadyc.service;


import com.gagadadyc.dao.buyWaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gagada on 2017/5/1.
 */
@Service
public class BuyWaterService {

    @Autowired
    private buyWaterDao buyWaterDao;


    //出售矿泉水页面的矿泉水信息
    public List buyWaterInfo() {

        List list = buyWaterDao.findWaterInfo();
        return list;

    }


    //用户购买矿泉水,调用buyWaterDao中的 写入订单功能和修改库存标记 的函数
    public void buyWater(List buyWaterList, String cusID) {

        buyWaterDao.buyWater(buyWaterList);//标记出库时间

        buyWaterDao.addOrder(buyWaterList, cusID);//写订单

    }
}
