package com.eshops.products.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Products extends BaseModel{
    private String description;
    private String image;
    private float price;
    private int availableQuantity;

    @ManyToOne
    private Category category;
}
