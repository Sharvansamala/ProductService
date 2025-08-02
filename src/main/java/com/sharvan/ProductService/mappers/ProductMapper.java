package com.sharvan.Spring_ecom.mappers;

import com.sharvan.Spring_ecom.dto.ProductDTO;
import com.sharvan.Spring_ecom.entity.CategoryEntity;
import com.sharvan.Spring_ecom.entity.ProductEntity;

public class ProductMapper {

    public static ProductDTO toDto(ProductEntity product){
        return ProductDTO.builder()
                .id(product.getId())
                .image(product.getImage())
                .color(product.getColor())
                .price(product.getPrice())
                .description(product.getDescription())
                .discount(product.getDiscount())
                .model(product.getModel())
                .title(product.getTitle())
                .brand(product.getBrand())
                .popular(product.isPopular())
                .categoryId(product.getCategory().getId())
                .build();
    }

    public static ProductEntity toEntity(ProductDTO dto, CategoryEntity category) {
        return ProductEntity.builder()
                .image(dto.getImage())
                .color(dto.getColor())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .discount(dto.getDiscount())
                .model(dto.getModel())
                .title(dto.getTitle())
                .brand(dto.getBrand())
                .popular(dto.isPopular())
                .category(category)
                .build();
    }
}
