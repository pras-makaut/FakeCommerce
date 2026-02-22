package com.example.FakeCommerce.Services;

import com.example.FakeCommerce.DTO.CreateCategoryRequestDto;
import com.example.FakeCommerce.Repository.CategoryRepository;
import com.example.FakeCommerce.Schema.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto requestDto){
        return categoryRepository.save(Category.builder().name(requestDto.getName()).build());
    }

    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
