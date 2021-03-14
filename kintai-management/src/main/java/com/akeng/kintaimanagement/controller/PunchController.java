package com.akeng.kintaimanagement.controller;

import com.akeng.kintaimanagement.form.LoginForm;
import com.akeng.kintaimanagement.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class PunchController {
    @Autowired
    RosterService rosterService;

    @GetMapping("/punch")
    public String index(LoginForm form){
        return "punch";
    }

    @PostMapping("/punch-in")
    public String punchIn() {
        rosterService.insertPunchIn(LocalDateTime.now());
        return "redirect:/punch";
    }

    @PostMapping("/punch-out")
    public String punchOut(){
        rosterService.insertPunchOut(LocalDateTime.now());
        return "redirect:/punch";
    }
}
