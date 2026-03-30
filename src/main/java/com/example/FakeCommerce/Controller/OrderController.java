package com.example.FakeCommerce.Controller;
import com.example.FakeCommerce.Services.OrderService;
import com.example.FakeCommerce.Utils.ApiResponse;
import com.example.FakeCommerce.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<ApiResponse<GetOrderResponseDto>> createOrderApi(@RequestBody CreateOrderRequestDtoByDiff createOrderRequestDto){

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orderService.createOrderApi(createOrderRequestDto),"Order Created SuccesFully"));

    }

    @PostMapping("/{id}")
    ResponseEntity<ApiResponse<GetOrderResponseDto>> updateOrderApi(@PathVariable Long id,@RequestBody UpdateOrderRequestDto updateOrderRequestDto){

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orderService.updateOrderApi(id,updateOrderRequestDto),"Order Updated SuccesFully"));

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

    @GetMapping("/{id}/summary")
    public ResponseEntity<ApiResponse<GetOrderSummaryResponseDto>> getOrderSummary(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orderService.getOrderSummary(id),"Order Summary fetch successfully"));
    }

}
