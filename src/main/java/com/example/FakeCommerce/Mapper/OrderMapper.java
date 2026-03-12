package com.example.FakeCommerce.Mapper;

import com.example.FakeCommerce.Schema.Order;
import com.example.FakeCommerce.Schema.ProductOrder;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(source = "order.status", target = "orderStatus")
    @Mapping(source = "order.id", target = "id")
    @Mapping(source = "order.createdAt", target = "createdAt")
    @Mapping(source = "order.updatedAt", target = "updatedAt")
    @Mapping(source = "productOrders", target = "items")
    GetOrderResponseDto toGetOrderResponseDto(Order order, List<ProductOrder> productOrders);
}