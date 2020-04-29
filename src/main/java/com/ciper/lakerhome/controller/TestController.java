package com.ciper.lakerhome.controller;


import com.ciper.lakerhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
//@PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。
@Controller
public class TestController {
    final UserService userService;

    @Autowired
    public TestController(UserService userService){
        this.userService = userService;
    }

    //数据库连接测试
    @RequestMapping(value = "showUser", method = RequestMethod.GET)
    public String showUser(Model model){
        String tel = userService.SelectByUserId("ciperchou@qq.com").getTel();

        model.addAttribute("uid",tel);
        model.addAttribute("name","<span style='color:red'>Jerry</span>");
        return "showUser";
    }

}

