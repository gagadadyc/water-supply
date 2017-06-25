package com.gagadadyc.domain;

import java.io.Serializable;

/**
 * Created by gagada on 2017/4/25.
 */
//领域对象一般要实现Serializable接口，以便可以序列化

public class Customer implements Serializable {
    private String customerid;
    private String password;
    private String name;
    private String phone;
    private String address;   //地址
    private int ordersum;  //订水桶数

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrdersum() {
        return ordersum;
    }

    public void setOrdersum(int ordersum) {
        this.ordersum = ordersum;
    }
}
