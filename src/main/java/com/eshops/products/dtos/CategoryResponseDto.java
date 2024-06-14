package com.eshops.products.dtos;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
    private int availableProducts;
}
