package com.akeng.kintaimanagement.controller;

import com.akeng.kintaimanagement.form.LoginForm;
import com.akeng.kintaimanagement.service.PunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class PunchController {
    @Autowired
    PunchService punchService;

    @PostMapping("/punch-in")
    public String punchIn() {
        punchService.insertPunchIn(LocalDateTime.now());
        return "redirect:/punch";
    }

    @PostMapping("/punch-out")
    public String punchOut(){
        punchService.insertPunchOut(LocalDateTime.now());
        return "redirect:/punch";
    }
}
