package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.LoginDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginPage() {
        return "login"; // `login.html` を表示
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("id") String id,
            @RequestParam("pass") String pass,
            HttpServletRequest request,
            Model model) {

        // DAOを手動でインスタンス化
        LoginDao loginDao = new LoginDao();

        // 入力チェック
        if (id.isEmpty() || pass.isEmpty()) {
            model.addAttribute("error", "ユーザーIDまたはパスワードを入力してください");
            return "login-error"; // `login-error.html` に遷移
        }

        // 認証処理
        if (loginDao.login(id, pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id); // セッションにユーザーIDを保存
            return "redirect:/member-only2"; // ログイン成功時、メンバー専用ページへリダイレクト
        } else {
            model.addAttribute("error", "ユーザーIDまたはパスワードが間違っています");
            return "login-error"; // `login-error.html` に遷移
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // セッションを無効化
        }
        return "redirect:/login"; // ログアウト後にログインページへ
    }
}
