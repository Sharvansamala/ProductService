package com.sharvan.ProductService.service;


import com.sharvan.ProductService.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO getCategoryByName(String name);

    List<CategoryDTO> getAllCategories();
}
