package com.eshops.products.controlleradvice;

import com.eshops.products.dtos.CategoryRequestDto;
import com.eshops.products.exceptions.CategoryAlreadyPresentException;
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
}
