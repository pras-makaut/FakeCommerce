package com.example.FakeCommerce.Controller;


import com.example.FakeCommerce.Schema.Order;
import com.example.FakeCommerce.Schema.ProductOrder;
import com.example.FakeCommerce.Services.ProductOrderService;
import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    @PostMapping
    boolean createOrder(@RequestBody List<CreateOrderRequestDto> createOrderRequestDtos){

        return productOrderService.createOrder(createOrderRequestDtos);

    }

    @GetMapping
    List<ProductOrder> getAllProductOrder(){
        return productOrderService.getAllProductOrder();
    }

}
