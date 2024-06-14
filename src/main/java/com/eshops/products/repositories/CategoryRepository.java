package com.eshops.products.repositories;

import com.eshops.products.dtos.CategoryResponseDto;
import com.eshops.products.models.Category;
import com.eshops.products.projections.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query(value = "select t.category_id as id, name, t.count as availableProducts from Category c JOIN (select category_id, " +
            "count(*) as count from products group by category_id) t on c.id=t.category_id",
            nativeQuery = true)
    List<CategoryProjection> findAllCategories();

}
