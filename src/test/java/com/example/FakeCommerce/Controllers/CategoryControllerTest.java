package com.example.FakeCommerce.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.FakeCommerce.Controller.CategoryController;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.Services.CategoryService;
import org.springframework.http.MediaType; // Correct
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @Test
    void createCategory_returns201() throws Exception {
        // arrange.
        Category testCategory = Category.builder().name("Test Category").build();
        testCategory.setId(1L);
        when(categoryService.createCategory(any())).thenReturn(testCategory);

        // act & assert.
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Category\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Test Category"));

    }
}
