package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    // ログイン画面を表示（GET）
	@GetMapping("/login")
	public String showLoginForm() {
		return "login"; // login.html を返す
	}

    // ログイン処理（POST）
    @PostMapping("/login")
    public String doLogin(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        // ユーザーIDとパスワードを判定
        if ("imai".equals(userId) && "p".equals(password)) {
            // ログイン成功 → セッションにユーザーIDを保存
            session.setAttribute("userId", userId);

            // 直接 home.html を表示
            return "home";  
        } else {
            // ログイン失敗 → 再度ログイン画面へ
            return "login";
        }
    }

    // ログイン後のホーム画面（GET）
    @GetMapping("/home")
    public String showHome(HttpSession session) {
        // セッションからユーザー情報を取得
        String loginUser = (String) session.getAttribute("userId");

        if (loginUser == null) {
            // 未ログインの場合、ログイン画面へリダイレクト
            return "redirect:/login";
        }

        return "home";  // video.html を表示
    }
    

    // ログアウト処理（POST）
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // セッションを無効化
        session.invalidate();
        // ログイン画面にリダイレクト
        return "redirect:/login";
    }
}
