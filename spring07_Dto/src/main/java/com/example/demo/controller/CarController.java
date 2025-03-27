package com.example.demo.controller;

import com.example.demo.model.CarDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping("/cars")
    public String showCars(Model model) {
        List<CarDto> carsList = new ArrayList<>();
        carsList.add(new CarDto(1, "セダン", 2590000, "2024-04-01"));
        carsList.add(new CarDto(2, "クーペ", 4990000, null));
        carsList.add(new CarDto(3, "SUV", 2990000, null));
        
        model.addAttribute("carsList", carsList);
        return "cars";
    }
}
