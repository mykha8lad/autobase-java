package com.example.AutoBase.controller;

import com.example.AutoBase.service.securityservice.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DriverController {

    @Value("${value.title}")
    private String pageTitle;

    @Autowired
    private SecurityService securityService;


    @GetMapping(value = {"/userInfo"})
    public String loginPage(Model model) {
        model.addAttribute("title", pageTitle);
        model.addAttribute("userInfoDto", securityService.getCurrentUserInfo().get());
        return "userInfo";
    }
}
