package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
    @GetMapping("/video")
    public String showVideoPage(HttpSession session, Model model) {
        // セッションからユーザー情報を取得
        String loginUser = (String) session.getAttribute("userId");
        if (loginUser == null) {
            // 未ログイン → ログイン画面にリダイレクト
            return "redirect:/login";
        }
        // ログインしているユーザーIDを画面表示用にModelへセット
        model.addAttribute("userId", loginUser);
        return "video"; // video.html というテンプレートを返す
    }

}
 
