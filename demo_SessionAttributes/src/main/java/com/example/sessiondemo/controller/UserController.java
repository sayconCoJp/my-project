package com.example.sessiondemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.sessiondemo.model.User;

@Controller
@SessionAttributes("user") // "user" オブジェクトをセッションに保存
public class UserController {

    @ModelAttribute("user")
    public User setUserSession() {
        return new User(); // セッションがない場合、新しいUserを作成
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html を表示
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, Model model) {
        User user = new User(username, "USER"); // 仮のロール設定
        model.addAttribute("user", user); // セッションに保存
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(@ModelAttribute("user") User user, Model model) {
        if (user.getUsername() == null) {
            return "redirect:/login"; // セッションがない場合はログインへ
        }
        model.addAttribute("username", user.getUsername());
        return "home"; // home.html を表示
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // セッションをクリア
        return "redirect:/login";
    }
}
