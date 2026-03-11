package com.example.FakeCommerce.Services;

import com.example.FakeCommerce.Exeptions.ResourceNotFoundExeption;
import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.Repository.CategoryRepository;
import com.example.FakeCommerce.Schema.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto requestDto){
        return categoryRepository.save(Category.builder().name(requestDto.getName()).build());
    }

    public Category getCategoryById(Long id){

        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Category with id = " + id + " not found"));
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id){
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
