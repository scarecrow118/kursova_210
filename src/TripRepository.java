package com.example.taxicompany.controller;

import com.example.taxicompany.entity.Car;
import com.example.taxicompany.service.CarService;
import com.example.taxicompany.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {
    @Autowired
    private TripService tripService;
    @Autowired
    private CarService carService;

    @GetMapping("/cars/register")
    public String showCarRegistrationForm(Model model) {
        model.addAttribute("car", new Car());
        return "account";
    }

    @PostMapping("/cars/register")
    public String registerCar(@ModelAttribute("car") Car car, Model model) {
        try {
            car.setActive(true);
            carService.saveCar(car);
            model.addAttribute("message", "Ви подали заявку на реєстрацію!");
        } catch (SecurityException e) {
            model.addAttribute("error", "Потрібно увійти в систему для реєстрації автомобіля.");
        }
        return "account";
    }
}