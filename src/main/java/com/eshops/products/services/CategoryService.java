package com.eshops.products.services;

import com.eshops.products.dtos.CategoryRequestDto;
import com.eshops.products.dtos.CategoryResponseDto;
import com.eshops.products.exceptions.CategoryAlreadyPresentException;
import com.eshops.products.models.Category;
import com.eshops.products.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(CategoryRequestDto categoryRequestDto) throws CategoryAlreadyPresentException {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryRequestDto.getName());

        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyPresentException("Category with this name is already present");
        }

        Category category = new Category();
        category.setName(categoryRequestDto.getName());

        categoryRepository.save(category);

    }
}
