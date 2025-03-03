package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CarDto;
import com.example.demo.model.Cart;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
    private final List<CarDto> cars = Arrays.asList(
        new CarDto(1, "Toyota Prius", 2500000, null),
        new CarDto(2, "Honda Civic", 2200000, null),
        new CarDto(3, "Mazda CX-5", 3000000, null)
    );

    private CarDto findById(int id) {
        for (CarDto car : cars) {
            if (car.getCarId() == id) {
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
    public String addToCart(@RequestParam("id") int id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        CarDto car = findById(id);
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

