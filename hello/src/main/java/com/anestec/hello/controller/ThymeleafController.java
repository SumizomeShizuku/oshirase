package com.anestec.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String showIndex() {
        // public String showIndex(Model model) {
        // model.addAttribute("name", "小沢");
        return "index"; // 返回index.html页面
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";  // 显示 login.html 页面
    }

    @GetMapping("/result")
    public String showResult(Model model) {
        return "result";  // 显示 result.html 页面
    }

    @GetMapping("/")
    public String defultPage() {
        return "login";  // 显示 login.html 页面
    }

}
