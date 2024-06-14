package com.eshops.products.controller;

import com.eshops.products.dtos.CategoryRequestDto;
import com.eshops.products.dtos.CategoryResponseDto;
import com.eshops.products.exceptions.CategoryAlreadyPresentException;
import com.eshops.products.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequestDto categoryRequestDto)
            throws CategoryAlreadyPresentException {

        categoryService.createCategory(categoryRequestDto);

        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);

    }

}
