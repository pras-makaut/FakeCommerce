package com.example.FakeCommerce.Controller;

import com.example.FakeCommerce.Utils.ApiResponse;
import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.Schema.Product;
import com.example.FakeCommerce.Services.ProductService;
import com.example.FakeCommerce.dtos.GetProductExtraDetailResponseDto;
import com.example.FakeCommerce.dtos.GetProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    ResponseEntity<ApiResponse<List<GetProductResponseDto>>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(productService.getAllProducts(), "All products fetched successfully"));
    }

    @PostMapping
    ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody CreateProductRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(productService.createProduct(requestDto), "Product created successfully"));
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(productService.getProductById(id), "Product fetched successfully"));
    }

    @GetMapping("/search")
    ResponseEntity<ApiResponse<List<Product>>> getProductByCategory(@RequestParam("categoryName") String categoryName){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(productService.getProductByCategoryName(categoryName), "Products fetched successfully"));
    }

    @GetMapping("/categories")
    ResponseEntity<ApiResponse<List<String>>> getAllDistictCategory(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(productService.getAllCategories(), "All categories fetched successfully"));
    }

    @GetMapping("/{id}/details")
    ResponseEntity<ApiResponse<GetProductExtraDetailResponseDto>> getProductExtraDetail(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(productService.getProductExtraDetail(id), "Product details fetched successfully"));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null, "Product deleted successfully"));
    }
}
