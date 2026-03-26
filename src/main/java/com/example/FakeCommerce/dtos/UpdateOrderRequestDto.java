package com.example.FakeCommerce.dtos;

import com.example.FakeCommerce.Schema.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderRequestDto {

    private OrderStatus status;

    private List<OrderItemActionDto> orderItems;
}
