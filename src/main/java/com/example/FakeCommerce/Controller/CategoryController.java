package com.example.FakeCommerce.Controller;

import com.example.FakeCommerce.Utils.ApiResponse;
import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Category>>> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.getAllCategory(),"All data fetched successfully"));
    }

    @PostMapping
    ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody CreateCategoryRequestDto requestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(categoryService.createCategory(requestDto),"Category created successfully"));

    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.getCategoryById(id),"Category fetched successfully"));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null, "Category deleted successfully"));
    }
}
