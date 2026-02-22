package com.example.FakeCommerce.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDto {

    private String name;

    private String description;

    private BigDecimal price;

    private Long category_id;

    private String ratings;

    private String image;
}
