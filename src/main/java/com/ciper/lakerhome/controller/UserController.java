package com.ciper.lakerhome.controller;

import com.ciper.lakerhome.entity.User;
import com.ciper.lakerhome.mapper.UserMapper;
import com.ciper.lakerhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {
    final UserService userService;
    final UserMapper userMapper;


    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //首页
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("index.html")
    public String return_index(){
        return "index";
    }

    //数据库连接测试
    @RequestMapping(value = "showUser", method = RequestMethod.GET)
    public String showUser(Model model){
        String tel = userService.SelectByUserId("ciperchou@qq.com").getTel();

        model.addAttribute("uid",tel);
        model.addAttribute("name","<span style='color:red'>Jerry</span>");
        return "showUser";
    }

    //登录界面
    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String login_index(){
        return "login";
    }

    @PostMapping("logIn")
    public ModelAndView logIn(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String root_email= "admin@qq.com";
        String root_password = "root";

        User user = userService.Login(email, password);
        if(user == null) {
            mav.clear();
            mav.setViewName("index");
        } else {
            if(email.equals(root_email)&&password.equals(root_password)){
                mav.setViewName("admin_index");
            }else {
                session.setAttribute("user_email", user.getEmail());
                mav.setViewName("user_index");
            }
        }
        return mav;
    }

    //注册界面
    @RequestMapping(value = "register.html",  method = RequestMethod.GET)
    public String register_index(){
        return "register";
    }

    @PostMapping("Register")
    public ModelAndView Register(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");

        User user = userService.SelectByUserId(email);

        if(user!=null){
            mav.setViewName("register");
        }else {
            userMapper.Register(email, password, tel);
        }

        return mav;
    }

    //管理员用户管理
    @RequestMapping(value = "admin_index.html",  method = RequestMethod.GET)
    public String admin_index(){
        return "admin_index";
    }

    @RequestMapping(value = "handle_user.html",  method = RequestMethod.GET)
    public String handle_user(){
        return "handle_user";
    }

    //用户管理——账户数据显示
    @GetMapping(value ="show_all_user")
    public String selectAll(Model model){
        List<User> user_list = userMapper.selectAll();
        model.addAttribute(user_list);
        return "handle_user";
    }

    //用户管理——查询账户
    @PostMapping(value = "handle_user_search")
    public String selectUserByUserId(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        List<User> user_list = userMapper.selectByPrimaryKey(email);
        model.addAttribute(user_list);
        return "handle_user";
    }

    //用户管理——删除账户
    @GetMapping("handle_user_delete/{email}")
    public String deleteUserByUserId(@PathVariable("email") String email){
        userMapper.deleteByPrimaryKey(email);
        return "handle_user";
    }

}
