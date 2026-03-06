package com.example.FakeCommerce.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequestDto {

    private Long productId;

    private Integer quantity;
}
