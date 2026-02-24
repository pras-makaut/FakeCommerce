package com.example.FakeCommerce.Repository;

import com.example.FakeCommerce.Schema.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
