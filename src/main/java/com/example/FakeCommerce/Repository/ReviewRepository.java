package com.example.FakeCommerce.Repository;

import com.example.FakeCommerce.Schema.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find all reviews for a given product id
    List<Review> findByProductId(Long productId);

    // Find all reviews for a given order id
    List<Review> findByOrderId(Long orderId);
}
