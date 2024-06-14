package com.eshops.products.controller;

import com.eshops.products.dtos.CategoryRequestDto;
import com.eshops.products.dtos.CategoryResponseDto;
import com.eshops.products.exceptions.CategoryAlreadyPresentException;
import com.eshops.products.exceptions.CategoryNotFoundException;
import com.eshops.products.models.Category;
import com.eshops.products.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequestDto categoryRequestDto)
            throws CategoryAlreadyPresentException {

        categoryService.createCategory(categoryRequestDto);

        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody Category category) throws CategoryNotFoundException {
        categoryService.updateCategory(category);

        return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);

        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

}
