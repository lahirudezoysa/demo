package com.example.javafx_with_jdbc.dto;

import java.util.ArrayList;

public class OrderDto {
   private String orderDate;

   private double subTotal;

   private ArrayList<OrderDetailsDto> orderDetailsDtos;

    public OrderDto(String orderDate, double subTotal, ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderDate = orderDate;
        this.subTotal = subTotal;
        this.orderDetailsDtos = orderDetailsDtos;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public ArrayList<OrderDetailsDto> getOrderDetailsDtos() {
        return orderDetailsDtos;
    }

    public void setOrderDetailsDtos(ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderDetailsDtos = orderDetailsDtos;
    }
}
