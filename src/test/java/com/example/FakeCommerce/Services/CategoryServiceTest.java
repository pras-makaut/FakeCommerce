package com.example.FakeCommerce.Services;

import com.example.FakeCommerce.Exeptions.ResourceNotFoundExeption;
import com.example.FakeCommerce.Repository.CategoryRepository;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest { // run the test run the service class

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService; // actual object --> inject mocks of the dependencies

    @Test
    void createCategory_savesAndReturnsCategory() {

        //ARRANGE
        CreateCategoryRequestDto dto = CreateCategoryRequestDto.builder().name("Test Category").build();
        Category testCategory = Category.builder().name("Test Category").build();
        testCategory.setId(1L);
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        //ACT

        Category category = categoryService.createCategory(dto);

        // ASSERT

        assertEquals("Test Category",category.getName());
        assertEquals(1L,category.getId());


    }

    @Test
    void getCategoryById_whenFound_returnCategory() {
        //ARRANGE
        Category category = Category.builder().name("Test Category").build();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        //ACT

        Category result = categoryService.getCategoryById(1L);

        // ASSERT

        assertEquals("Test Category",category.getName());
        assertEquals(1L,category.getId());

    }

    @Test
    void getCategoryById_whenNotFound_throwResourceNotFoundException(){

        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundExeption.class , () -> categoryService.getCategoryById(2L));
    }
}
