package com.example.AutoBase.controller;

import com.example.AutoBase.dto.MessageDto;
import com.example.AutoBase.model.Driver;
import com.example.AutoBase.service.registrationuserservice.RegistrationUserService;
import com.example.AutoBase.utils.HandleError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class AuthController {

    @Value("${value.title}")
    private String pageTitle;

    @Value("${value.alertTextColorSuccess}")
    private String alertTextColorSuccess;

    @Autowired
    private RegistrationUserService registrationUserService;


    @GetMapping(value = {"/login"})
    public String loginPage(Model model) {
        return "auth/login";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("userForm", new Driver());
        return "auth/registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute Driver driver, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return HandleError.handleRegistrationError(model, driver, "Binding results error");
        }
        if (!driver.getEncryptedPassword().equals(driver.getPasswordConfirm())) {
            return HandleError.handleRegistrationError(model, driver, "Passwords don't match");
        }

        String regMess = registrationUserService.registerUser(driver).orElse(null);
        if (regMess != null) {
            return HandleError.handleRegistrationError(model, driver, regMess);
        }

        return "redirect:/userInfo";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logout(Model model, RedirectAttributes redirectAttributes) {
        MessageDto messageDto = new MessageDto("Logout Successful!", alertTextColorSuccess);
        redirectAttributes.addFlashAttribute("message", messageDto);
        return "redirect:/main";
    }
}
