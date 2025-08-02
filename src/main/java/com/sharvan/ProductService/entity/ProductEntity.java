package com.sharvan.ProductService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseEntity{
    private String image;
    private String color;
    private int price;
    private String description;
    private int discount;
    private String model;
    private String title;
    private String brand;
    private boolean popular;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;
}
