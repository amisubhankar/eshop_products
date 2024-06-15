package com.eshops.products.controller;

import com.eshops.products.dtos.ProductRequestDto;
import com.eshops.products.dtos.ProductResponseDto;
import com.eshops.products.exceptions.CategoryNotFoundException;
import com.eshops.products.exceptions.ProductNameAlreadyExist;
import com.eshops.products.exceptions.ProductNotFoundException;
import com.eshops.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){

        return ResponseEntity.ok().body(productService.getProducts());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto productRequestDto)
            throws ProductNameAlreadyExist, CategoryNotFoundException {
        productService.createProduct(productRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("Product created successfully !!");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequestDto productRequestDto)
            throws ProductNameAlreadyExist, CategoryNotFoundException, ProductNotFoundException {
        productService.updateProduct(productRequestDto);

        return ResponseEntity.ok().body("Product updated successfully !!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);

        return ResponseEntity.ok().body("Product deleted successfully !!");
    }

    @GetMapping("/{catgId}")
    public ResponseEntity<List<ProductResponseDto>> getProductByCategory(@PathVariable("catgId") Long catgId)
            throws CategoryNotFoundException {
        List<ProductResponseDto> products = productService.getProductByCategory(catgId);

        return ResponseEntity.ok().body(products);
    }

}
