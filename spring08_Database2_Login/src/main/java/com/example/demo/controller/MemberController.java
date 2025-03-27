package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@GetMapping("/member-only2")
	public String memberPage(HttpSession session, Model model) {
		// セッションがない場合、またはIDがない場合はログインページへリダイレクト
		if (session.getAttribute("id") == null) {
			return "redirect:/login";
		}

		return "member-only2"; // `member-only2.html` を表示
	}

	@GetMapping("/video")
	public String videoPage(HttpSession session, Model model) {
		// セッションがない場合、またはIDがない場合はログインページへリダイレクト
		if (session.getAttribute("id") == null) {
			return "redirect:/login";
		}
		
		return "video"; // `video.html` を表示
	}
}