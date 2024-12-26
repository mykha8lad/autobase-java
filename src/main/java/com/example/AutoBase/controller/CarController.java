package com.example.AutoBase.controller;

import com.example.AutoBase.dto.CarDto;
import com.example.AutoBase.dto.CarFilterDto;
import com.example.AutoBase.dto.MessageDto;
import com.example.AutoBase.exceptions.CarIsNotFoundException;
import com.example.AutoBase.service.CarFromBrokeDownService;
import com.example.AutoBase.service.busines.carservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CarController {

    @Value("${value.title}")
    private String pageTitle;

    @Value("${value.alertTextColorError}")
    private String alertTextColorError;

    @Value("${value.alertTextColorSuccess}")
    private String alertTextColorSuccess;

    @Autowired
    private CarService carService;

    @Autowired
    private CarFromBrokeDownService carFromBrokeDownService;


    @GetMapping(value = {"/cars/get"})
    public String getCars(@ModelAttribute CarFilterDto carFilterDto, Model model) {
        System.out.println(carFilterDto);

        List<CarDto> carsDto = (carFilterDto.getIsFree() == null && carFilterDto.getIsBroker() == null)
                ? carService.findAllDto()
                : carService.findByFilter(carFilterDto);

        model.addAttribute("title", pageTitle);
        model.addAttribute("carsDto", carsDto);
        model.addAttribute("carFilterDto", carFilterDto);
        return "cars";
    }

    @PostMapping(value = {"/car/from-broker"})
    public String carFromBroker(@RequestParam("carId") int carId, Model model, RedirectAttributes redirectAttributes) {
        MessageDto messageDto = new MessageDto();

        try {
            carFromBrokeDownService.carFromBrokeDown(carId);  // домтаём из ремонта
            messageDto.setMessage("The car has successfully from broker down");
            messageDto.setColor(alertTextColorSuccess);
        } catch (CarIsNotFoundException e) {
            messageDto.setMessage(e.getMessage());
            messageDto.setColor(alertTextColorError);
        }
        redirectAttributes.addFlashAttribute("message", messageDto);

        return "redirect:/cars/get";
    }
}
