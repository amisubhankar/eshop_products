package com.eshops.products.services;

import com.eshops.products.dtos.ProductRequestDto;
import com.eshops.products.dtos.ProductResponseDto;
import com.eshops.products.exceptions.CategoryNotFoundException;
import com.eshops.products.exceptions.ProductNameAlreadyExist;
import com.eshops.products.exceptions.ProductNotFoundException;
import com.eshops.products.models.Category;
import com.eshops.products.models.Products;
import com.eshops.products.repositories.ICategoryRepository;
import com.eshops.products.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    RedisTemplate redisTemplate;

    public void createProduct(ProductRequestDto productRequestDto) throws ProductNameAlreadyExist, CategoryNotFoundException {
        Optional<Products> optionalProduct = productRepository.findByName(productRequestDto.getName());

        if(optionalProduct.isPresent()){
            throw new ProductNameAlreadyExist();
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productRequestDto.getCategoryId());
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException();
        }

        Products product = new Products(productRequestDto.getDescription(), productRequestDto.getImage(),
                                        productRequestDto.getPrice(), productRequestDto.getAvailableQuantity(),
                                        optionalCategory.get());
        product.setName(productRequestDto.getName());

        productRepository.save(product);
    }

    public void updateProduct(ProductRequestDto productRequestDto) throws ProductNotFoundException, ProductNameAlreadyExist, CategoryNotFoundException {
        Optional<Products> optionalProduct = productRepository.findById(productRequestDto.getId());

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }

        if(!optionalProduct.get().getName().equals(productRequestDto.getName())){
            //check this new name already exists or not
            Optional<Products> optionalNamedProd = productRepository.findByNameAndIdNot(productRequestDto.getName(),
                    productRequestDto.getId());

            if(optionalNamedProd.isPresent()){
                throw new ProductNameAlreadyExist();
            }
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productRequestDto.getCategoryId());
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException();
        }

        Products product = optionalProduct.get();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setAvailableQuantity(productRequestDto.getAvailableQuantity());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(optionalCategory.get());

        productRepository.save(product);

    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Products> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }

        productRepository.deleteById(id);
    }

    public List<ProductResponseDto> getProducts(){
        if(redisTemplate.opsForHash().hasKey("products", "something") == false) {
            List<ProductResponseDto> products = new ArrayList<>();

            for (Products product : productRepository.findAll()) {
                products.add(new ProductResponseDto(product.getId(), product.getName(), product.getDescription(),
                        product.getImage(), product.getPrice(), product.getAvailableQuantity(),
                        product.getCategory().getId()));
            }

            redisTemplate.opsForHash().put("products", "something", products);
        }

        return (List<ProductResponseDto>) redisTemplate.opsForHash().get("products", "something");
    }

    public List<ProductResponseDto> getProductByCategory(Long catgId) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(catgId);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException();
        }

        Optional<List<Products>> optionalProducts = productRepository.findByCategory(optionalCategory.get());

        List<ProductResponseDto> products = new ArrayList<>();

        for (Products product : optionalProducts.get()) {
            products.add(new ProductResponseDto(product.getId(), product.getName(), product.getDescription(),
                    product.getImage(), product.getPrice(), product.getAvailableQuantity(),
                    product.getCategory().getId()));
        }

        return products;
    }
}
