package com.sharvan.ProductService.services;


import com.sharvan.ProductService.dto.CategoryDTO;
import com.sharvan.ProductService.entity.CategoryEntity;
import com.sharvan.ProductService.repository.CategoryRepository;
import com.sharvan.ProductService.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //enables mockito to use with junit for unit testing
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    private CategoryDTO categoryDTO;
    private CategoryEntity category1;
    private CategoryEntity category2;
    private CategoryEntity category3;

    @BeforeEach
    void setup() {
        categoryDTO = CategoryDTO.builder().name("Electronics").build();

        category1 = CategoryEntity.builder().name("Electronics").build();
        category1.setId(1L);
        category2 = CategoryEntity.builder().name("Books").build();
        category2.setId(2L);
        category3 = CategoryEntity.builder().name("Clothing").build();
        category3.setId(3L);
    }

    @Test  // mark this function for testing
    @DisplayName("should return all categories successfully")
    void getAllCategories_shouldReturnAllCategories() {
        //Arrange
        List<CategoryEntity> categoryEntities = new ArrayList<>();

        categoryEntities.add(category1);
        categoryEntities.add(category2);
        categoryEntities.add(category3);
        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        //Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        //Assert
        assertEquals(3, result.size());
        verify(categoryRepository, times(1)).findAll();

    }


    @AfterEach
    void tearDown() {
        categoryDTO = null;
        category1 = null;
        category2 = null;
        category3 = null;
    }

    @Test
    @DisplayName("should return category name")
    void getCategoryByName_shouldReturnCategoryName() {

        when(categoryRepository.findByName(any(String.class))).thenReturn(Optional.of(category1));

        CategoryDTO categoryDTO = categoryService.getCategoryByName("Clothing");

        assertEquals(category1.getName(), categoryDTO.getName());
        verify(categoryRepository, times(1)).findByName(any(String.class));
    }

    @Test
    @DisplayName("should return empty list when no categories exist")
    void getAllCategories_shouldReturnEmptyListWhenNoCategoriesExist() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        //Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        //Assert
        assertEquals(0, result.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("should create a new category successfully")
    void createCategory_shouldCreateNewCategorySuccessfully() {
        // Arrange

        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(category1);

        // Act
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        // Assert
        assertEquals(categoryDTO.getName(), createdCategory.getName());
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

}
