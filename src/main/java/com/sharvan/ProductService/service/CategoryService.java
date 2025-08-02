package com.sharvan.Spring_ecom.service;

import com.sharvan.Spring_ecom.dto.CategoryDTO;
import com.sharvan.Spring_ecom.entity.CategoryEntity;
import com.sharvan.Spring_ecom.mappers.CategoryMapper;
import com.sharvan.Spring_ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.save(CategoryMapper.toEntity(categoryDTO));
        return CategoryMapper.toDto(categoryEntity);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("category not found at id: " + id));
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        CategoryEntity category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found on name: " + name));
//        Pageable pageRequest = PageRequest.of()
        return CategoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(entity -> CategoryMapper.toDto(entity))
                .toList();
    }
}
