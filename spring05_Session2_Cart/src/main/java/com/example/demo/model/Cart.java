package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<String> items = new ArrayList<>();

    public List<String> getItems() {
        return items;
    }

    public void addItem(String car) {
        items.add(car);
    }
    
    public int getSize() {
    	 return items.size();
    }

    public void clear() {
        items.clear();
    }
}