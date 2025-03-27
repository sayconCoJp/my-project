package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReplaceController {
	@GetMapping("/replace")
	public String sample() {
		return "main";
	}
}