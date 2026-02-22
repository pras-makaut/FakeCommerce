package com.example.FakeCommerce.Repository;

import com.example.FakeCommerce.Schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
