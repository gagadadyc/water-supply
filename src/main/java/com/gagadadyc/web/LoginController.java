package com.gagadadyc.web;

import com.gagadadyc.domain.Customer;
import com.gagadadyc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gagada on 2017/4/30.
 * 负责处理登陆请求，完成登陆业务，并根据登陆成功与否转向欢迎页或失败页
 */

//将此POJO类标注成SpringMVC 的控制器
@Controller
//类中的所有响应请求的方法都是以该地址作为父路径。
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private CustomerService customerService;

    //负责处理login.jsp的请求,向.JSP传送request对象
    @RequestMapping(value = "/login")
    public String LoginPage(HttpServletRequest request) {
        return "login";
    }

    //负责处理loginCheck.jsp的请求
    @RequestMapping(value = "/loginCheck")
    public ModelAndView loginChenk(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isValidUser =
                customerService.hasLoginCustomer(loginCommand.getUserName(), loginCommand.getPassWord());

        if (!isValidUser) {//失败，报错
            //"login"代表视图逻辑明，“error”代表数据模型名称，“用户...”代表数据模型对象
            return new ModelAndView("login", "error", "用户名或密码错误");
        } else {
            //成功，加载用户信息
            Customer customer = customerService.findUserInfo(loginCommand.getUserName());
            //将领域对象存入session，并取名为“customer”
            request.getSession().setAttribute("customer", customer);
            double prisum = 0.0;
            request.getSession().setAttribute("prisum", prisum);//订水总金额
            //ModelAndView对象既包括了视图信息又包括了视图渲染所需的模型数据信息,在此处可理解为其代表一个视图

            return new ModelAndView("redirect:/buyWatercon");
        }
    }

}
