package com.example.javafx_with_jdbc.dto;

public class OrderDetailsDto {
    private int vehicleId;
    private int qty;
    private double price;

    public OrderDetailsDto(int vehicleId, int qty, double price) {
        this.vehicleId = vehicleId;
        this.qty = qty;
        this.price = price;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
