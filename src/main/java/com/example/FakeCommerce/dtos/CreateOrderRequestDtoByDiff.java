package com.example.FakeCommerce.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequestDtoByDiff {

    List<OrderItemRequestDto> orderItems;
}
