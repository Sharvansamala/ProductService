package com.sharvan.Spring_ecom.service;


import com.sharvan.Spring_ecom.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO getCategoryByName(String name);

    List<CategoryDTO> getAllCategories();
}
