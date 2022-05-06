package com.example.restblog.web;

import com.example.restblog.data.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {
    private final CategoriesRepository categoryRepository;

    public CategoriesController(CategoriesRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping()
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    // had to use findById
    // https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
    @GetMapping("{categoryName}")
    public Category getPostsByCategory(@PathVariable String categoryName) {
        return categoryRepository.findByName(categoryName);
    }
}