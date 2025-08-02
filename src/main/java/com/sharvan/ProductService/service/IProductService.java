package com.sharvan.Spring_ecom.service;

import com.sharvan.Spring_ecom.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductDTO dto);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();
}
