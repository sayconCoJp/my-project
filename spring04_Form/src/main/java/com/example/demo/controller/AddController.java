package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {

    @GetMapping("/add")
    public String doGetAdd(
         @RequestParam(name="num1") String num1Str,
         @RequestParam(name="num2") String num2Str,
         Model model
    ) {
        // nullチェックや例外処理は適宜追加
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        model.addAttribute("ans", num1 + num2);

        return "ans";
    }
}
