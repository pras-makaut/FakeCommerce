package com.example.FakeCommerce.Repository;

import com.example.FakeCommerce.Schema.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositry extends JpaRepository<Product, Long> {
}
