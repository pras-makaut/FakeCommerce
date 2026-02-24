package com.example.FakeCommerce.Repository;

import com.example.FakeCommerce.Schema.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder,Long> {


}
