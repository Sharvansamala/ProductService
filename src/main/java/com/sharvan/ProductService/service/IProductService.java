package com.sharvan.ProductService.service;

import com.sharvan.ProductService.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductDTO dto);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();
}
