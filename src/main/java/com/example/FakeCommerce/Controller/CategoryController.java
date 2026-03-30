package com.example.FakeCommerce.Controller;

import com.example.FakeCommerce.Utils.ApiResponse;
import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("category deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null, "Category deleted successfully"));

    }
}
