package com.gagadadyc.web;

import com.gagadadyc.domain.Customer;
import com.gagadadyc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by gagada on 2017/5/1.
 */

@Controller
//类中的所有响应请求的方法都是以该地址作为父路径。
@RequestMapping(value = "/")
public class userInfoController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/userInfo")
    public ModelAndView userInfoProcessing(HttpServletRequest request) {

        Customer customer = (Customer) request.getSession().getAttribute("customer");//取得Session中的用户信息
        Map map = customerService.UserOrderInfo(customer);

        return new ModelAndView("userInfo", "map", map);
    }

    //修改信息
    @RequestMapping(value = "/modifyInfo")
    public ModelAndView modifyInformation(HttpServletRequest request) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAddress(address);
        //更新session中的用户信息
        request.getSession().setAttribute("customer", customer);

        customerService.updateUserInfo(customer);

        return new ModelAndView("redirect:/userInfo");

    }
}
