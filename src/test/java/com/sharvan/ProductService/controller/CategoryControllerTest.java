package com.sharvan.ProductService.controller;

import com.sharvan.ProductService.dto.CategoryDTO;
import com.sharvan.ProductService.exception.GlobalExceptionHandler;
import com.sharvan.ProductService.service.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private ICategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Initialize MockMvc or any other setup needed for the tests
         mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                 .setControllerAdvice(new GlobalExceptionHandler()) // Assuming you have a GlobalExceptionHandler
                 .build();
    }


    @Test
    @DisplayName("GET /api/categories should return all categories")
    void getAllCategories_shouldReturnAllCategories() throws Exception {

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryDTOS.add(CategoryDTO.builder().name("Electronics").build());
        categoryDTOS.add(CategoryDTO.builder().name("Books").build());
        categoryDTOS.add(CategoryDTO.builder().name("Clothing").build());
        // Mock the service call
        when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

        mockMvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].name").value("Books"))
                .andExpect(jsonPath("$[2].name").value("Clothing"));


        verify(categoryService,times(1)).getAllCategories();
    }

    @Test
    @DisplayName("GET /api/categories/{id} should return category by id")
    void getCategoryById_shouldReturnCategoryById() throws Exception {
        Long categoryId = 1L;
        CategoryDTO categoryDTO = CategoryDTO.builder().id(categoryId).name("Electronics").build();

        when(categoryService.getCategoryById(any(Long.class))).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/categories/{id}", categoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryId))
                .andExpect(jsonPath("$.name").value("Electronics"));

        verify(categoryService, times(1)).getCategoryById(categoryId);
    }

    @Test
    void createCategory_shouldCreateCategory() throws Exception {
        CategoryDTO createdCategory = CategoryDTO.builder().id(1L).name("Electronics").build();

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(createdCategory);

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Electronics\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Electronics"));

        verify(categoryService, times(1)).createCategory(any(CategoryDTO.class));
    }
}
