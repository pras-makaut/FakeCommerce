package com.example.FakeCommerce.Controller;

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
    ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
    }

    @PostMapping
    ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequestDto requestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(requestDto));

    }

    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
