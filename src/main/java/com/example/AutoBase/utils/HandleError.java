package com.example.AutoBase.utils;

import com.example.AutoBase.dto.MessageDto;
import com.example.AutoBase.model.Driver;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

@UtilityClass
public class HandleError {

    @Value("${value.alertTextColorError}")
    private String alertTextColorError;

    public static String handleRegistrationError(Model model, Driver driver, String errorMessage) {
        MessageDto messageDto = new MessageDto();
        messageDto.setColor(alertTextColorError);
        messageDto.setMessage(errorMessage);
        model.addAttribute("message", messageDto);
        model.addAttribute("userForm", driver);
        return "auth/registration";
    }
}
