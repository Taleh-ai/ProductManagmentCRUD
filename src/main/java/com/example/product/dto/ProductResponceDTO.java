package com.example.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponceDTO {
    private String name;
    private double price;
    private int quantity;
}
