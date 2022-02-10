package com.sin.shirotutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @RequestMapping(value = "/user/add")
    public String add(){
        return "/user/addUser";
    }

    @RequestMapping(value = "/user/update")
    public String update(){
        return "/user/updateUser";
    }
}
