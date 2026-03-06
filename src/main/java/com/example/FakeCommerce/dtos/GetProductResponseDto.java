package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetProductResponseDto {
    private String name;

    private String description;

    private BigDecimal price;

    private BigDecimal ratings;

    private String image;
}
