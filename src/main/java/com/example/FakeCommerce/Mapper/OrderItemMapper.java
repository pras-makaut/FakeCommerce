package com.example.FakeCommerce.Mapper;

import com.example.FakeCommerce.Schema.ProductOrder;
import com.example.FakeCommerce.dtos.OrderItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.id",    target = "productId")
    @Mapping(source = "product.name",  target = "productName")
    @Mapping(source = "product.price", target = "productPrice")
    @Mapping(source = "product.image", target = "productImage")
    @Mapping(source = "quantity",      target = "quantity")
    @Mapping(target = "subTotal", expression = "java(calculateSubTotal(productOrder))")
    OrderItemResponseDto toOrderItemResponseDto(ProductOrder productOrder);

    @Named("calculateSubTotal")
    default BigDecimal calculateSubTotal(ProductOrder productOrder) {
        return productOrder.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(productOrder.getQuantity()));
    }
}