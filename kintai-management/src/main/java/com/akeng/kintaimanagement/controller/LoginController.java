package com.akeng.kintaimanagement.controller;

import com.akeng.kintaimanagement.form.LoginForm;
import com.akeng.kintaimanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("form", new LoginForm());
        return "login";
    }

    @GetMapping("/punch")
    public String punch(LoginForm form){
        return "punch";
    }
}
