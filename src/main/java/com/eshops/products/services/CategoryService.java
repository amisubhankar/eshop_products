package com.eshops.products.services;

import com.eshops.products.dtos.CategoryRequestDto;
import com.eshops.products.dtos.CategoryResponseDto;
import com.eshops.products.exceptions.CategoryAlreadyPresentException;
import com.eshops.products.exceptions.CategoryNotFoundException;
import com.eshops.products.models.Category;
import com.eshops.products.projections.ICategoryProjection;
import com.eshops.products.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public void createCategory(CategoryRequestDto categoryRequestDto) throws CategoryAlreadyPresentException {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryRequestDto.getName());

        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyPresentException("Category with this name is already present");
        }

        Category category = new Category();
        category.setName(categoryRequestDto.getName());

        categoryRepository.save(category);

    }

    public void updateCategory(Category newCategory) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(newCategory.getId());

        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException();
        }

        Category category = optionalCategory.get();
        category.setName(newCategory.getName());

        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException();
        }

        categoryRepository.deleteById(id);
    }

    public List<CategoryResponseDto> getCategories() {

        if(redisTemplate.opsForHash().hasKey("foo", "something") == false) {
            List<ICategoryProjection> categories = categoryRepository.findAllCategories();
            List<CategoryResponseDto> result = new ArrayList<>();

            for (ICategoryProjection category : categories) {
                result.add(new CategoryResponseDto(category.getId(), category.getName(), category.getAvailableProducts()));
            }

            redisTemplate.opsForHash().put("foo", "something", result);
        }

        return (List<CategoryResponseDto>) redisTemplate.opsForHash().get("foo", "something");
    }
}
