package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello3Controller {

	@GetMapping("/hello3")
	public String hello2(Model model) {
		model.addAttribute("message", "Hello World");
		return "result"; // "result.html"が表示される
	}
}
