package com.example.FakeCommerce.Controller;

import com.example.FakeCommerce.DTO.CreateProductRequestDto;
import com.example.FakeCommerce.Schema.Product;
import com.example.FakeCommerce.Services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    Product createProduct(@RequestBody CreateProductRequestDto requestDto){
        return productService.createProduct(requestDto);
    }
}
