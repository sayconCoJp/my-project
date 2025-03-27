package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String sample(Model model) {
		model.addAttribute("msg", "<script>alert('攻撃！');</script>");

		model.addAttribute("age", 19);

		model.addAttribute("user", null);

		model.addAttribute("name", "");

		model.addAttribute("bloodType", "AB");

		ArrayList<String> names = new ArrayList<>();
		names.add("imai");
		names.add("matsuda");
		names.add("tabuchi");
		names.add("hashi");
		model.addAttribute("names", names);

		model.addAttribute("price", 2590000);
		
		model.addAttribute("today", LocalDate.now());

		return "sample";
	}
}