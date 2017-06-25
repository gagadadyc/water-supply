package com.gagadadyc.service;

import com.gagadadyc.dao.CustomerDao;
import com.gagadadyc.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gagada on 2017/4/29.
 */

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    //检查用户名、密码的准确性
    public boolean hasLoginCustomer(String customerid, String password) {
        int mathchCount = customerDao.getLoginCount(customerid, password);
        return mathchCount > 0;
    }

    //以用户名为条件加载用户信息
    public Customer findUserInfo(String customerid) {
        return customerDao.findUserInfo(customerid);
    }

    //加载 用户、订单 信息
    public Map UserOrderInfo(Customer customer) {

        Map map = new HashMap();

        map.put("userid", customer.getCustomerid());
        map.put("username", customer.getName());
        map.put("phone", customer.getPhone());
        map.put("address", customer.getAddress());
        map.put("watersum",customer.getOrdersum());

        List list = customerDao.findOrderInfo(customer.getCustomerid());
        map.put("orderlist", list);

        return map;
    }

    //修改用户信息
    public  void updateUserInfo(Customer customer){
        customerDao.updateLoginInfo(customer);
    }

}
