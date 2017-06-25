package com.gagadadyc.domain;

import java.io.Serializable;

/**
 * Created by gagada on 2017/5/1.
 */
public class Water implements Serializable {

    private int waterid;
    private String watername;
    private double waterprice;//售价
    private double primeprice;//进价
    private String wuppliername;//供应商名
    private String operatetime;//入库时间
    private String selltime;//出库时间


    public int getWaterid() {
        return waterid;
    }

    public void setWaterid(int waterid) {
        this.waterid = waterid;
    }

    public String getWatername() {
        return watername;
    }

    public void setWatername(String watername) {
        this.watername = watername;
    }

    public double getWaterprice() {
        return waterprice;
    }

    public void setWaterprice(double waterprice) {
        this.waterprice = waterprice;
    }

    public double getPrimeprice() {
        return primeprice;
    }

    public void setPrimeprice(double primeprice) {
        this.primeprice = primeprice;
    }

    public String getWuppliername() {
        return wuppliername;
    }

    public void setWuppliername(String wuppliername) {
        this.wuppliername = wuppliername;
    }

    public String getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(String operatetime) {
        this.operatetime = operatetime;
    }

    public String getSelltime() {
        return selltime;
    }

    public void setSelltime(String selltime) {
        this.selltime = selltime;
    }
}
