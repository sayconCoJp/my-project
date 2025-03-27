package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello2Controller {

	@GetMapping("/hello2")
	public String hello2() {
		// GETリクエストを受け取った際の処理
		return "viewname";
	}
}
