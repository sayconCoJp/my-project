package com.example.demo.model;


import java.io.Serializable;

public class CarDto implements Serializable {
    private int carId;
    private String name;
    private int price;
    private String deletedAt;

    public CarDto() {}

    public CarDto(int carId, String name, int price, String deletedAt) {
        this.carId = carId;
        this.name = name;
        this.price = price;
        this.deletedAt = deletedAt;
    }

    public int getCarId() { return carId; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getDeletedAt() { return deletedAt; }
}
