package com.sharvan.ProductService.service;

import com.sharvan.ProductService.dto.ProductDTO;
import com.sharvan.ProductService.entity.CategoryEntity;
import com.sharvan.ProductService.entity.ProductEntity;
import com.sharvan.ProductService.mappers.ProductMapper;
import com.sharvan.ProductService.repository.CategoryRepository;
import com.sharvan.ProductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {

        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not found"));

        ProductEntity product = ProductMapper.toEntity(dto,category);
        product = productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }
}
