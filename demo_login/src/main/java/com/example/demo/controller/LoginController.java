package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@PostMapping("/login1")
	public String login(@RequestParam("id") String id,
			@RequestParam("pass") String pass,
			Model model) {

		if ("imai".equals(id) && "p".equals(pass)) {
			model.addAttribute("id", id);
			return "member-only"; // templates/member-only.html に遷移
		} else {
			return "redirect:/login-error"; // ログイン失敗時のページ
		}
	}

	@GetMapping("/login-error")
	public String loginError() {
		return "login-error"; // templates/login-error.html を表示
	}
}
