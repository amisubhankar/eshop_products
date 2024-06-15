package com.eshops.products.controlleradvice;

import com.eshops.products.dtos.CategoryRequestDto;
import com.eshops.products.exceptions.CategoryAlreadyPresentException;
import com.eshops.products.exceptions.CategoryNotFoundException;
import com.eshops.products.exceptions.ProductNameAlreadyExist;
import com.eshops.products.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnyControllerAdvice {

    @ExceptionHandler(CategoryAlreadyPresentException.class)
    public ResponseEntity<String> handleDuplicateCategory(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already exists !!");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleInvalidCategory(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found !!");
    }

    @ExceptionHandler(ProductNameAlreadyExist.class)
    public ResponseEntity<String> handleDuplicateProductName(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name already exists !!");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found !!");
    }
}
