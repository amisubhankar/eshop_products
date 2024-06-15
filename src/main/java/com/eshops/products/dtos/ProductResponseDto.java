package com.eshops.products.dtos;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@JsonSerialize
public class ProductResponseDto implements Serializable{
    private Long id;
    private String name;
    private String description;
    private String image;
    private float price;
    private int availableQuantity;
    private Long categoryId;
}
