package com.sin.shirotutorial.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "/user/addUser";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "/user/updateUser";
    }

    @RequestMapping("/user/login")
    public String loginRequest(){
        return "/user/login";
    }

    @RequestMapping("/loginRequest")
    public String login(Model model, String username, String password){
        // 获取当前的用户
        Subject user = SecurityUtils.getSubject();
        // 封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try{
            user.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e){
            model.addAttribute("msg", "Unknown Account Or Incorrect Password");
            return "/user/login";
        }
        return "/index";
    }

    @RequestMapping("/noauth")
    public String unauthorized(Model model){
        model.addAttribute("msg", "Unauthorized");
        return "/index";
    }
}
