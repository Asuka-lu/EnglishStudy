package com.studyEnglish.controller;

import com.studyEnglish.entity.User;
import com.studyEnglish.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public String register(User user, Model model) {
        user.setActiveStatus(1);
        user.setActiveCode(null);

        userService.addUser(user);
        System.out.println("增加成功");
        model.addAttribute("msg", "注册成功，请直接登录");
        return "index";
    }

    @RequestMapping("/user/checkCode")
    public String active(@RequestParam(required = false) String activeCode, Model model) {
        if (activeCode == null || activeCode.trim().isEmpty()) {
            model.addAttribute("msg", "当前系统已取消邮箱激活，注册后可直接登录");
            return "index";
        }

        User user = userService.queryUserByActiveCode(activeCode);
        if (user != null) {
            model.addAttribute("msg", "当前系统已取消邮箱激活，账号可直接登录");
            user.setActiveStatus(1);
            user.setActiveCode(null);
            userService.updateUser(user);
        } else {
            model.addAttribute("msg", "无效的激活码（系统已取消邮箱激活）");
        }
        return "index";
    }
    @RequestMapping("/toforget")
    public String toForget() {
        return "user/register/page-forget";
    }
}