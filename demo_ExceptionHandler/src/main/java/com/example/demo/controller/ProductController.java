package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.ProductNotFoundException;

@Controller
@RequestMapping("/products")
public class ProductController {

    // ダミーデータ
    private static final Map<Integer, String> products = new HashMap<>();
    
    static {
        products.put(1, "ノートパソコン");
        products.put(2, "スマートフォン");
        products.put(3, "タブレット");
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id, Model model) {
        String product = products.get(id);

        if (product == null) {
            throw new ProductNotFoundException("商品ID " + id + " は見つかりませんでした。");
        }

        model.addAttribute("productName", product);
        return "product";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(ProductNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}

