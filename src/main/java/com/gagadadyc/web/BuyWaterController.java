package com.gagadadyc.web;

import com.gagadadyc.domain.Customer;
import com.gagadadyc.service.BuyWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gagada on 2017/5/1.
 */
@Controller

//类中的所有响应请求的方法都是以该地址作为父路径。
@RequestMapping(value = "/")

public class BuyWaterController {

    @Autowired()
    private BuyWaterService buyWaterService;

    //展示矿泉水
    @RequestMapping(value = "/buyWatercon")
    public ModelAndView buyWaterInfo() {
        List list = buyWaterService.buyWaterInfo();
        //将矿泉水表格传给view

        return new ModelAndView("buyWater", "waterlist", list);
    }

    //添加购物车进Cookie
    @RequestMapping(value = "/buyCheck")
    public ModelAndView buyWaterCheck(HttpServletRequest request) throws UnsupportedEncodingException {


        request.setCharacterEncoding("utf-8");

        String waterid = request.getParameter("waterid");
        String watername = request.getParameter("watername");
        double waterprice =  Double.valueOf(request.getParameter("waterprice"));
        HttpSession session = request.getSession();
        List buyWaterList=new ArrayList();//向dao层传多个矿泉水ID所用
        Map<String,Double>map = new HashMap<String,Double>();  //向界面传递session中购物车的矿泉水ID，矿泉水名
        double prisum = Double.valueOf(session.getAttribute("prisum").toString())+waterprice;
        session.setAttribute("prisum",prisum);

        //判断Session里的List（购物车）是否为空，空则新建，非空则添加
        if(session.getAttribute("buyWaterList")==null){
            buyWaterList.add(waterid);
            map.put(watername,waterprice);
            session.setAttribute("buyWaterList",buyWaterList);
            session.setAttribute("map",map);
        }else {
            buyWaterList = (List) session.getAttribute("buyWaterList");
            map = (Map<String,Double>)session.getAttribute("map");
            buyWaterList.add(waterid);
            map.put(watername,waterprice);
            session.setAttribute("buyWaterList",buyWaterList);
            session.setAttribute("map",map);
        }


        return new ModelAndView("redirect:/buyWatercon");
    }

    //购买购物车里的矿泉水
    @RequestMapping(value = "/buyWatercar")
    public ModelAndView buyWater(HttpServletRequest request){
        HttpSession session = request.getSession();
        List buyWaterList=new ArrayList();
        //判断Session里的List（购物车）是否为空，空则报错，非空则下单
        if(session.getAttribute("buyWaterList")==null){
            return new ModelAndView("forward:/buyWatercon", "error", "购物车里暂无商品哦");
        }else {
            buyWaterList = (List) session.getAttribute("buyWaterList");
            Customer customer = (Customer) session.getAttribute("customer");//获取id
            buyWaterService.buyWater(buyWaterList,customer.getCustomerid());
            session.removeAttribute("buyWaterList");//下单完成，清除购物车
            session.removeAttribute("map");
            session.setAttribute("prisum",0);
        }
        return new ModelAndView("redirect:/userInfo");
    }

}
