package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/home")
    public String home(Model model) {
        String username = "yusei"; // ここでは仮のユーザー名
        model.addAttribute("username", username);
        return "home"; // home.html を表示
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/home"; // ログアウト後、ホームへリダイレクト
    }
}
