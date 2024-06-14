package com.eshops.products.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonSerialize
public class CategoryResponseDto implements Serializable {
    private Long id;
    private String name;
    private int availableProducts;
}
