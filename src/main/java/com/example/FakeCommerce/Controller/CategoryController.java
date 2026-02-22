package com.example.FakeCommerce.Controller;

import com.example.FakeCommerce.DTO.CreateCategoryRequestDto;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PostMapping
    Category createCategory(@RequestBody CreateCategoryRequestDto requestDto){

        return categoryService.createCategory(requestDto);

    }

    @GetMapping("/{id}")
    Optional<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
