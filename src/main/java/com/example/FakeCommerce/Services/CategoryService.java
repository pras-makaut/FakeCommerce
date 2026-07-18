package com.example.FakeCommerce.Services;

import com.example.FakeCommerce.Exeptions.ResourceNotFoundExeption;
import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.Repository.CategoryRepository;
import com.example.FakeCommerce.Schema.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto requestDto){
        return categoryRepository.save(Category.builder().name(requestDto.getName()).build());
    }

    public Category getCategoryById(Long id){
        log.info("Get category by id called");
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Category with id = " + id + " not found"));
    }

    public List<Category> getAllCategory(){
        log.warn("Get all categories method called");
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id){
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
