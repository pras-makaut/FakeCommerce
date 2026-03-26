package com.example.FakeCommerce.Repository;

import com.example.FakeCommerce.Schema.Order;
import com.example.FakeCommerce.Schema.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder,Long> {

    List<ProductOrder> findByOrderId(Long orderId);


    @Query("SELECT op FROM ProductOrder op JOIN FETCH op.product WHERE op.order = :order")
    List<ProductOrder> findByOrdertWithProduct(Order order);
}
