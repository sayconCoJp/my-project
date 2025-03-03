package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.dao.LoginUserDao;
import model.entity.LoginUser;

@Controller
public class LoginController {
    private final LoginUserDao loginUserDao = new LoginUserDao();

    @GetMapping("/login")
    public String loginPage() {
    	System.out.println("you are here");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String loginId, @RequestParam String password, Model model) {
        LoginUser user = loginUserDao.findByLoginId(loginId);

        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("message", "ログイン成功");
            return "home";
        } else {
            model.addAttribute("error", "ログインIDまたはパスワードが違います");
            return "login";
        }
    }
}

