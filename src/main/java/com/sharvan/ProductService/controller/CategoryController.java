package com.sharvan.Spring_ecom.controller;

import com.sharvan.Spring_ecom.dto.CategoryDTO;
import com.sharvan.Spring_ecom.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Qualifier("categoryService")
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(@RequestParam(required = false) String name){
        if(name!=null && !name.isBlank()){
            CategoryDTO categoryDTO = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(categoryDTO);
        }else {
            List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
            return ResponseEntity.ok(categoryDTOS);
        }
    }

}

