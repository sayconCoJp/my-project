package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CarDto> items = new ArrayList<>();

    public List<CarDto> getItems() {
        return items;
    }

    public void addItem(CarDto car) {
        items.add(car);
    }

    public void clear() {
        items.clear();
    }
}