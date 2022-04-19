package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {
    private static final Category CAT2 = new Category(2L, "Other category 2", null);
    private static final Category CAT3 = new Category(3L, "Other category 3", null);

    private static final User USER1 = new User(1L, "User 1", "user1@bob.com", "1111", null, User.Role.USER, null);
    private static final User USER2 = new User(2L, "User 2", "user2@bob.com", "2222", null, User.Role.ADMIN, null);

    private static final Post POST1 = new Post(1L, "Post 1", "Blah", USER1, null);
    private static final Post POST2 = new Post(2L, "Post 2", "Blah", USER2, null);
    private static final Post POST3 = new Post(3L, "Post 3", "Blah", USER1, null);


    @GetMapping
    private Category getPostsByCategory(@RequestParam String categoryName) {
        // plug some posts into a category and return the category
        Category cat1 = new Category(1L, categoryName, null);
        cat1.setPosts(Arrays.asList(POST1, POST2, POST3));
        return cat1;
    }
}