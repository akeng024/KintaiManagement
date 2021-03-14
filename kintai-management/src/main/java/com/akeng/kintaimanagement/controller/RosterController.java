package com.akeng.kintaimanagement.controller;

import com.akeng.kintaimanagement.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RosterController {
    @Autowired
    RosterService rosterService;

    @GetMapping("/roster")
    public String index(Model model){
        model.addAttribute("dataList", rosterService.getAll());
        return "roster";
    }
}
