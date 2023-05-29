package com.example.product.dto;

import lombok.Data;

@Data

public class ProductRequestDTO {
    private String name;
    private double cost;
    private double price;
    private int quantity;
}
