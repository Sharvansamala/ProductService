package com.sharvan.Spring_ecom.mappers;

import com.sharvan.Spring_ecom.dto.CategoryDTO;
import com.sharvan.Spring_ecom.entity.CategoryEntity;

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
