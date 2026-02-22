package com.example.FakeCommerce.Services;

import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.Repository.ProductRepositry;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.Schema.Product;
import com.example.FakeCommerce.dtos.GetProductExtraDetailResponseDto;
import com.example.FakeCommerce.dtos.GetProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositry productRepositry;
    private final CategoryService categoryService;

    public List<GetProductResponseDto> getAllProducts(){
        return productRepositry.
                findAll().
                stream().
                map(product -> GetProductResponseDto.builder()
                        .name(product.getName())
                        .ratings(product.getRatings())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .image(product.getImage())
                        .build()).collect(Collectors.toList());
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

    public GetProductExtraDetailResponseDto getProductExtraDetail(Long id){
        Product product  = productRepositry.findProductWithDetailById(id).get(0);

        return GetProductExtraDetailResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .category(product.getCategory().getName())
                .description(product.getDescription())
                .ratings(product.getRatings())
                .build();
    }
}
