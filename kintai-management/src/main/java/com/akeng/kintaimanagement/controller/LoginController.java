package com.akeng.kintaimanagement.controller;

import com.akeng.kintaimanagement.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("form", new LoginForm());
        return "login";
    }

    @PostMapping("/loginCheck")
    public String login() {
        return "forward:/login";
    }
}
