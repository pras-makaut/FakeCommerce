package com.example.FakeCommerce.Adapters;

import com.example.FakeCommerce.Repository.OrderRepository;
import com.example.FakeCommerce.Repository.ProductOrderRepository;
import com.example.FakeCommerce.Schema.Order;
import com.example.FakeCommerce.Schema.ProductOrder;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.dtos.OrderItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAdapters {

    private final ProductOrderRepository productOrderRepository;


    public List<GetOrderResponseDto> mapToGetOrderResponseDtoList(List<Order> orders){
        return orders.stream().map(
                this::mapToGetOrderResponseDto
        ).collect(Collectors.toList());
    }

    public GetOrderResponseDto mapToGetOrderResponseDto(Order order){

        List<ProductOrder> productOrders = productOrderRepository.findByOrderId(order.getId());
        List<OrderItemResponseDto> items = mapToOrderItemResponseDto(productOrders);

        return GetOrderResponseDto.builder()
                .id(order.getId())
                .orderStatus(order.getStatus())
                .items(items)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();

    }

    public List<OrderItemResponseDto> mapToOrderItemResponseDto(List<ProductOrder> productOrders) {
        return productOrders.stream().map(
                op -> OrderItemResponseDto.builder()
                        .productId(op.getProduct().getId())
                        .productName(op.getProduct().getName())
                        .productImage(op.getProduct().getImage())
                        .productPrice(op.getProduct().getPrice())
                        .quantity(op.getQuantity())
                        .subTotal(op.getProduct().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
                        .build())
                        .collect(Collectors.toList());

    }
}
