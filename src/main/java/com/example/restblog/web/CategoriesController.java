package com.example.restblog.web;

import com.example.restblog.data.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {
    private final CategoriesRepository categoryRepository;

    public CategoriesController(CategoriesRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // had to use findById
    // https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
    @GetMapping
    private Optional<Category> getPostsByCategory(@RequestParam String categoryName) {
        return categoryRepository.findById(1L);
    }
}