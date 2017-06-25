package com.gagadadyc.dao;

import com.gagadadyc.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by gagada on 2017/4/25.
 * 访问user的持久化类
 */

@Repository
public class CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据用户名密码获取匹配的用户数，1表示用户名密码正确，0表示用户名密码错误。
    public int getLoginCount(String customerID, String password) {

        String sql = " SELECT count(*) FROM customer " +
                " WHERE customerid =? and custpassword=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{customerID, password}, Integer.class);
    }

    //根据用户名查询用户信息
    public Customer findUserInfo(final String customerid) {

        String sql = "SELECT customerid,customername,customerphone,customeraddress,ordersum " +
                " FROM customer  WHERE customerid=? ";
        final Customer cus = new Customer();
        jdbcTemplate.query(sql, new Object[]{customerid},

                //使用匿名类回调函数
                new RowCallbackHandler() {
                    //processRow函数负责将查询结果从ResultSet装载到用户domain实例中
                    public void processRow(ResultSet resultSet) throws SQLException {
                        cus.setCustomerid(customerid);
                        cus.setName(resultSet.getString("customername"));
                        cus.setPhone(resultSet.getString("customerphone"));
                        cus.setAddress(resultSet.getString("customeraddress"));
                        cus.setOrdersum(resultSet.getInt("ordersum"));
                    }
                });
        return cus;
    }

    //修改用户信息（目前仅提供修改姓名电话住址信息），将用户domain实例中的参数写入数据库
    public void updateLoginInfo(Customer cus) {
        String sql = "UPDATE customer SET customername=?,customerphone=?,customeraddress=? " +
                " WHERE  customerid =?";
        jdbcTemplate.update(sql, new Object[]{cus.getName(), cus.getPhone(), cus.getAddress(),cus.getCustomerid()});
    }

    //查询用户订单信息
    public List findOrderInfo(String customerid) {

        String sql = " SELECT  orderid " +
                ",customerid " +
                ",workerid " +
                ",delivertime " +
                ",arrivaltime " +
                "FROM orderinfo where customerid = ? ";

        List list = jdbcTemplate.queryForList(sql, customerid);

        //再往List里插入以逗号为分隔的waterid和订单总额
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i); //先取出订单号作为查询条件
            int orderid = Integer.parseInt(String.valueOf(map.get("orderid"))); //Object转int

            String sqlwn = "select watername,waterprice from mineralwater " +
                    " inner join orderdetails on mineralwater.waterid=orderdetails.waterid " +
                    " inner join orderinfo on orderinfo.orderid=orderdetails.orderid and orderinfo.orderid= ? ";

            List listwn = jdbcTemplate.queryForList(sqlwn, orderid);
            String watername = "";
            Double waterprice = 0.0;
            for (int j = 0; j < listwn.size(); j++) {
                Map mapwn = (Map) listwn.get(j);
                watername = watername + mapwn.get( "watername");
                //若不是最后一个，都加上逗号
                if (j < list.size()-1 ){
                    watername = watername+",";
                }
                waterprice = waterprice +  Double.parseDouble(mapwn.get("waterprice").toString());//Object转Double
            }

            map.put("watername",watername);
            map.put("waterprice",waterprice);
        }


        return list;
    }
}
