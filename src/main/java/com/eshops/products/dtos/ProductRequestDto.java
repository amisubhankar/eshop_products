package com.eshops.products.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private float price;
    private int availableQuantity;
    private Long categoryId;
}
