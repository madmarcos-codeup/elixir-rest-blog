package com.example.restblog.web;

import com.example.restblog.data.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;

    public PostsController(PostsRepository postsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping
    private List<Post> getAll() {
        return postsRepository.findAll();
    }

    @GetMapping("{postId}")
    private Optional<Post> getById(@PathVariable Long postId) {
        return postsRepository.findById(postId);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        newPost.setAuthor(usersRepository.getById(1L));
        List<Category> categories = new ArrayList<>();
        for (Category category : newPost.getCategories()) {
            if (categoriesRepository.findCategoryByName(category.getName()) != null) {
                categories.add(categoriesRepository.findCategoryByName(category.getName()));
            } else {
                Category newCat = new Category();
                newCat.setName(category.getName());
                categoriesRepository.save(newCat);
                categories.add(categoriesRepository.findCategoryByName(newCat.getName()));
            }
        }
        newPost.setCategories(categories);
        postsRepository.save(newPost);
        System.out.println("New post created");
    }

    @PutMapping("{postId}")
    private void updatePost(@PathVariable Long postId, @RequestBody Post newPost) {
        Post postToUpdate = postsRepository.getById(postId);
        postToUpdate.setTitle(newPost.getTitle());
        postToUpdate.setContent(newPost.getContent());
        postsRepository.save(postToUpdate);
        System.out.println("Post updated!");
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId) {
        Post postToDelete = postsRepository.getById(postId);
        postsRepository.delete(postToDelete);
    }

    @GetMapping("searchByCategory")
    private List<Post> searchPostsByCategory(@RequestParam String category) {
        return postsRepository.findAllByCategories(categoriesRepository.findCategoryByName(category));
    }

    @GetMapping("searchByTitle")
    private List<Post> searchPostsByTitleKeyword(@RequestParam String keyword) {
        return postsRepository.searchByTitleLike(keyword);
    }
}