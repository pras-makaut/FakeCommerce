package com.example.FakeCommerce.Services;

import com.example.FakeCommerce.Repository.ProductRepositry;
import com.example.FakeCommerce.Schema.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositry productRepositry;

    public List<Product> getAllProducts(){
        return productRepositry.findAll();
    }
}
