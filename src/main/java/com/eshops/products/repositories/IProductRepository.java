package com.eshops.products.repositories;

import com.eshops.products.models.Category;
import com.eshops.products.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByName(String name);
    Optional<Products> findByNameAndIdNot(String name, Long id);

    Optional<List<Products>> findByCategory(Category category);
}
