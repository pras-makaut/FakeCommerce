package com.example.FakeCommerce.Controller;


import com.example.FakeCommerce.Schema.Order;
import com.example.FakeCommerce.Schema.ProductOrder;
import com.example.FakeCommerce.Services.OrderService;
import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.CreateOrderRequestDtoByDiff;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.dtos.UpdateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    @PostMapping
//    boolean createOrder(@RequestBody List<CreateOrderRequestDto> createOrderRequestDtos){
//
//        return orderService.createOrder(createOrderRequestDtos);
//
//    }

    @PostMapping
    GetOrderResponseDto createOrderApi(@RequestBody CreateOrderRequestDtoByDiff createOrderRequestDto){

        return orderService.createOrderApi(createOrderRequestDto);

    }

    @PostMapping("/{id}")
    GetOrderResponseDto updateOrderApi(@PathVariable Long id,@RequestBody UpdateOrderRequestDto updateOrderRequestDto){

        return orderService.updateOrderApi(id,updateOrderRequestDto);

    }

    @GetMapping
    List<GetOrderResponseDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    GetOrderResponseDto getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }



//    @DeleteMapping("/{id}")
//    public boolean deleteOrder(@PathVariable Long id) {
//
//    }

}
