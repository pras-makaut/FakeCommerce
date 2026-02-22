package com.example.FakeCommerce.Services;

import com.example.FakeCommerce.DTO.CreateProductRequestDto;
import com.example.FakeCommerce.Repository.ProductRepositry;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.Schema.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositry productRepositry;
    private final CategoryService categoryService;

    public List<Product> getAllProducts(){
        return productRepositry.findAll();
    }

    public Product createProduct(CreateProductRequestDto requestDto){
        Optional<Category> category = categoryService.getCategoryById(requestDto.getCategory_id());
        Category categoryObjec = category.orElseThrow(IllegalAccessError::new);
        return productRepositry.save(Product.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .image(requestDto.getImage())
                .description(requestDto.getDescription())
                .ratings(requestDto.getRatings())
                .category(categoryObjec)
                .build());
    }

    public Optional<Product> getProductById(long id){
        return productRepositry.findById(id);
    }

    public List<Product> getProductByCategoryName(String categoryName){
        return productRepositry.findByCategory(categoryName);

    }

    public List<String> getAllCategories(){
        return productRepositry.getAllDistinctCategories();
    }
}
