package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

	@GetMapping("/weather")
	public String weather(@RequestParam(name = "weather") String weather, Model model) {
		// 天気に応じて背景色を設定
		String backgroundColor;
		switch (weather) {
		case "曇り":
			backgroundColor = "gray";
			break;
		case "雨":
			backgroundColor = "blue";
			break;
		default: // 晴れ
			backgroundColor = "yellow";
			break;
		}

		// Modelに値をセット
		model.addAttribute("weather", weather);
		model.addAttribute("bgColor", backgroundColor);

		return "weather";
	}

	@GetMapping("/link")
	public String showLink() {
		return "link";
	}
}
