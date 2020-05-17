package com.ciper.lakerhome.controller;


import com.ciper.lakerhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


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

    @GetMapping("showUser.html")
    public String index(){
        return "showUser";
    }


    //登录之后，新闻，视频都提示去个人主页模块选择喜爱的球星
    // 在个人主页部分选择你喜爱的球星，提交后
    // 你的新闻和你的视频两个模块开始展示你订阅的新闻以及视频内容
    @GetMapping("select")
    public String select_test(HttpServletRequest request){
        String[] list = request.getParameterValues("stars");

        for(String stars : list){
            //通过名字查询StarsID
            //（session.user_id, starsId)->插入用户球星关联表
            System.out.println(stars);
        }

        return "ok";
    }

}

