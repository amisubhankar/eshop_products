package com.eshops.products.repositories;

import com.eshops.products.models.Category;
import com.eshops.products.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByName(String name);
    Optional<Products> findByNameAndIdNot(String name, Long id);

    Optional<List<Products>> findByCategory(Category category);

    @Query(value = "update products set available_quantity = :updatedQuantity where id = :prodId", nativeQuery = true)
    @Modifying
    @Transactional
    void updateQuantity(Long prodId, int updatedQuantity);
}
