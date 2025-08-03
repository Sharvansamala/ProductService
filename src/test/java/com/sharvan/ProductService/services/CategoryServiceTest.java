package com.sharvan.ProductService.services;


import com.sharvan.ProductService.dto.CategoryDTO;
import com.sharvan.ProductService.entity.CategoryEntity;
import com.sharvan.ProductService.repository.CategoryRepository;
import com.sharvan.ProductService.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //enables mockito to use with junit for unit testing
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test  // mark this function for testing
    @DisplayName("should return all categories successfully")
    void getAllCategories_shouldReturnAllCategories() {
        //Arrange
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        CategoryEntity category = CategoryEntity.builder()
                .name("Electronics").build();
        category.setId(1L);
        CategoryEntity category1 = CategoryEntity.builder()
                .name("Books").build();
        category1.setId(2L);

        categoryEntities.add(category);
        categoryEntities.add(category1);
        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        //Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        //Assert
        assertEquals(2, result.size());
        verify(categoryRepository,times(1)).findAll();
    }
}
