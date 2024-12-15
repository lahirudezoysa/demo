package com.example.javafx_with_jdbc.tm;

public class OrderTM {
    private String brand;
    private String model;
    private int orderQty;
    private double unitPrice;
    private double totalPrice;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderTM(String brand, String model, int orderQty, double unitPrice, double totalPrice) {
        this.brand = brand;
        this.model = model;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }
}
