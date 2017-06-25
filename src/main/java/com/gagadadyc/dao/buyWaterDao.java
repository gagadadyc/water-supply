package com.gagadadyc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by gagada on 2017/5/1.
 */
@Repository
public class buyWaterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据矿泉水id查询矿泉水信息
    public List findWaterInfo() {

        //watername字段相同的记录仅显示一条,仅取出出库时间为null（既未售出）的矿泉水
        String sql = "SELECT waterid,watername,waterprice " +
                " FROM mineralwater  WHERE waterid IN " +
                " (SELECT max(waterid) FROM mineralwater GROUP BY watername)" +
                "and selltime IS NULL ";

        //将查询出来的结果集每一行转换成一个Map（key为字段名），将所有的Map放进list中
        List list = jdbcTemplate.queryForList(sql);

        return list;
    }

    //根据矿泉水id标记出库时间
    public void buyWater(List<String> buyWaterList) {
        for (int i = 0; i < buyWaterList.size(); i++) {

            String waterid = buyWaterList.get(i);
            String sql = "UPDATE mineralwater SET selltime=now() " +
                    " WHERE  waterid =?";
            jdbcTemplate.update(sql, new Object[]{waterid});
        }
    }

    //根据以矿泉水id与用户id来添加订单信息
    public void addOrder(List<String> buyWaterList, String cusID) {

        //使用用户名先在订单表生成一个订单，再获取订单id填写订单详情表
        String sqlor = "INSERT INTO orderinfo(customerid,delivertime) " +
                " VALUES(?,now()) ";
        jdbcTemplate.update(sqlor, new Object[]{cusID});

        //获取最后一次插入的主键，也就是订单ID
        String sqlid = "SELECT LAST_INSERT_ID()";
        List list = jdbcTemplate.queryForList(sqlid);
        Map<String,BigInteger> map = (Map) list.get(0);
        BigInteger orderid =  map.get("LAST_INSERT_ID()");

        //遍历队列以获得矿泉水id插入订单详情表
        for (int i = 0; i < buyWaterList.size(); i++) {

            String waterid = buyWaterList.get(i);
            String sql = "INSERT INTO orderdetails(orderid,waterid) " +
                    " VALUES(?,?) ";
            jdbcTemplate.update(sql, new Object[]{orderid, waterid});
        }
    }
}
