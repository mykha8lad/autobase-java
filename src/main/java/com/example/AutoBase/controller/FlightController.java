package com.example.AutoBase.controller;

import com.example.AutoBase.dto.FlightDto;
import com.example.AutoBase.dto.MessageDto;
import com.example.AutoBase.exceptions.FlightCannotBeCanceledException;
import com.example.AutoBase.exceptions.FlightIsNotFoundByException;
import com.example.AutoBase.service.CarBrokeDownService;
import com.example.AutoBase.service.PassedOneDayFlightService;
import com.example.AutoBase.service.busines.flightservice.FlightService;
import com.example.AutoBase.service.securityservice.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class FlightController {

    @Value("${value.title}")
    private String pageTitle;

    @Value("${value.alertTextColorError}")
    private String alertTextColorError;

    @Value("${value.alertTextColorSuccess}")
    private String alertTextColorSuccess;


    @Autowired
    private FlightService flightService;

    @Autowired
    private PassedOneDayFlightService passedOneDayFlightService;

    @Autowired
    private CarBrokeDownService carBrokeDownService;

    @Autowired
    private SecurityService securityService;


    @GetMapping(value = "flights/get")
    public String getFlights(Model model) {
        List<FlightDto> flightsDto = flightService.findAllDto();

        model.addAttribute("title", pageTitle);
        model.addAttribute("flightsDto", flightsDto);

        return "flights";
    }

    @PostMapping(value = "flight/passed-day-way")
    public String passedDayWay(@RequestParam("flightId") int flightId, Model model, RedirectAttributes redirectAttributes) {
        MessageDto messageDto = new MessageDto();
        try {
            String message = (passedOneDayFlightService.passedOneDayFlight(flightId))
                    ? "Completed flights"
                    : "Successfully passed day way";
            messageDto.setMessage(message);
            messageDto.setColor(alertTextColorSuccess);
        } catch (FlightIsNotFoundByException | FlightCannotBeCanceledException | NullPointerException e) {
            messageDto.setMessage(e.getMessage());
            messageDto.setColor(alertTextColorError);
        }
        redirectAttributes.addFlashAttribute("message", messageDto);

        return "redirect:/flights/get";
    }

    @PostMapping(value = "flight/car-broke-down")
    public String carBrokeDown(@RequestParam("flightId") int flightId, Model model, RedirectAttributes redirectAttributes) {
        MessageDto messageDto = new MessageDto();
        try {
            carBrokeDownService.carBrokerDown(flightId);
            messageDto.setMessage("Flight processing with a broken car was successful");
            messageDto.setColor(alertTextColorSuccess);
        } catch (FlightIsNotFoundByException | NullPointerException e) {
            messageDto.setMessage(e.getMessage());
            messageDto.setColor(alertTextColorError);
        }
        redirectAttributes.addFlashAttribute("message", messageDto);

        return "redirect:/flights/get";
    }

    @GetMapping(value = "flight/get")
    public String getFlight(Model model) {
        MessageDto messageDto = null;

        // найти у нашего пользователя flight
        int driverId = securityService.getCurrentUserId();

        if (driverId == -1) {
            messageDto = new MessageDto();
            messageDto.setMessage("Driver not found");
            messageDto.setColor(alertTextColorError);
        }

        // находим flight нашего пользователя
        FlightDto flightFto = flightService.findByDriverId(driverId).orElse(null);
        if (flightFto == null) {
            messageDto = new MessageDto();
            messageDto.setMessage("Driver has not flight");
            messageDto.setColor(alertTextColorError);
        }

        model.addAttribute("flightDto", flightFto);
        model.addAttribute("message", messageDto);

        return "flightInfo";
    }
}
