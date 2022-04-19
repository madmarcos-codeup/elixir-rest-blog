package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {
    private static final Category CAT1 = new Category(1L, "CAT 1", null);
    private static final Category CAT2 = new Category(2L, "CAT 2", null);
    private static final Category CAT3 = new Category(3L, "CAT 3", null);

    private static final Post POST1 = new Post(1L, "Post 1", "Blah", null, Arrays.asList(CAT1, CAT2));
    private static final Post POST2 = new Post(2L, "Post 2", "Blah", null, Arrays.asList(CAT2, CAT3));
    private static final Post POST3 = new Post(3L, "Post 3", "Blah", null, Arrays.asList(CAT1, CAT3));
    private static final Post POST4 = new Post(4L, "Post 4", "Blah", null, Arrays.asList(CAT1, CAT2));
    private static final Post POST5 = new Post(5L, "Post 5", "Blah", null, Arrays.asList(CAT2, CAT3));
    private static final Post POST6 = new Post(6L, "Post 6", "Blah", null, Arrays.asList(CAT1, CAT3));

    @GetMapping
    private Category getPostsByCategory() {
        // plug some posts into a category and return the category
        CAT1.setPosts(Arrays.asList(POST1, POST3, POST4, POST6));
        return CAT1;
    }



}