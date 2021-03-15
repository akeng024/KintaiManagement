package com.akeng.kintaimanagement.controller;

import com.akeng.kintaimanagement.auth.LoginUser;
import com.akeng.kintaimanagement.form.LoginForm;
import com.akeng.kintaimanagement.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String punchIn(@AuthenticationPrincipal LoginUser loginUser) {
        rosterService.insertPunchIn(LocalDateTime.now(), loginUser.getUser().getId());
        return "redirect:/punch";
    }

    @PostMapping("/punch-out")
    public String punchOut(@AuthenticationPrincipal LoginUser loginUser){
        rosterService.insertPunchOut(LocalDateTime.now(), loginUser.getUser().getId());
        return "redirect:/punch";
    }
}
