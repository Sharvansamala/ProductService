package com.sharvan.ProductService.mappers;

import com.sharvan.ProductService.dto.CategoryDTO;
import com.sharvan.ProductService.entity.CategoryEntity;

public class CategoryMapper {

    public static CategoryDTO toDto(CategoryEntity category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static CategoryEntity toEntity(CategoryDTO categoryDTO){
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .build();
    }
}
