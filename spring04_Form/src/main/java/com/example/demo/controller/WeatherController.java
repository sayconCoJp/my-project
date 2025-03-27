package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

	@GetMapping("/link")
	public String link() {
		return "link"; // link.html
	}

	@GetMapping("/button")
	public String button() {
		return "button"; // button.html
	}

	@GetMapping("/weather")
	public String weather2(@RequestParam("weather") String weather, Model model) {
		model.addAttribute("weather", weather);
		return "weather"; // weather.html
	}
}