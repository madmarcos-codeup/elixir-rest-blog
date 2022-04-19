package com.example.restblog.web;

import com.example.restblog.data.Category;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {

    @GetMapping
    private Category getPostsByCategory() {
        return null;
    }



}