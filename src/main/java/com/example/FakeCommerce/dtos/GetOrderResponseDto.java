package com.example.FakeCommerce.dtos;


import com.example.FakeCommerce.Schema.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrderResponseDto {

    private Long id;

    private OrderStatus orderStatus;

    private List<OrderItemResponseDto> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
