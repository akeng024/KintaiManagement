package com.akeng.kintaimanagement.controller;

import com.akeng.kintaimanagement.auth.LoginUser;
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
    public String index(){
        return "punch";
    }

    @PostMapping("/punch-in")
    public String punchIn(@AuthenticationPrincipal LoginUser loginUser) {
        var today = LocalDateTime.now();
        rosterService.insertPunchIn(today, loginUser.getUserId());
        return "redirect:/punch";
    }

    @PostMapping("/punch-out")
    public String punchOut(@AuthenticationPrincipal LoginUser loginUser) {
        var today = LocalDateTime.now();
        rosterService.insertPunchOut(today, loginUser.getUserId());
        return "redirect:/punch";
    }

    // 動作確認用: DBに今年1年分の日付をセット
    @PostMapping("/set-days")
    public String setDays(@AuthenticationPrincipal LoginUser loginUser) {
        var today = LocalDateTime.now();
        rosterService.setDays(today, loginUser.getUserId());
        return "redirect:/punch";
    }

    // 動作確認用: DBに今年1年分の月をセット
    @PostMapping("/set-months")
    public String setMonths(@AuthenticationPrincipal LoginUser loginUser) {
        var today = LocalDateTime.now();
        rosterService.setMonths(today, loginUser.getUserId());
        return "redirect:/punch";
    }
}
