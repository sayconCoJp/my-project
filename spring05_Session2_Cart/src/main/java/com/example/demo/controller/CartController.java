package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cart;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
    private final List<String> cars = Arrays.asList( "セダン","クーペ",  "SUV");

    private String findByName(String carName) {
        for (String car : cars) {
            if (car.equals(carName)) {
                return car;
            }
        }
        return null;
    }

    @GetMapping("/cars")
    public String showCars(Model model) {
        model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart.getItems());
        return "cart";
    }

    @GetMapping("/cart/add")
    public String addToCart(@RequestParam("name") String name, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        String car = findByName(name);
        if (car != null) {
            cart.addItem(car);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        return "redirect:/cart";
    }
}