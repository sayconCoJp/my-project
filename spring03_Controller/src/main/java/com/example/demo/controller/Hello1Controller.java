package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello1Controller {

    @GetMapping("/hello1")
    public String helloWorld() {
        System.out.println("Hello World");
        return "blank"; 
        // blank.html(Thymeleafファイル)を表示する想定
    }
}

