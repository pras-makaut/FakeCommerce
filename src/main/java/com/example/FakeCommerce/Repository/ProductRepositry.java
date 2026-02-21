package com.example.FakeCommerce.Repository;

import com.example.FakeCommerce.Schema.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositry extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);
}
