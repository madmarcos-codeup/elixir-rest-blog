package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {
    private final PostsRepository postRepository;

    private static final User USER1 = new User(1L, "User 1", "user1@bob.com", "1111", null, User.Role.USER, null);
    private static final User USER2 = new User(2L, "User 2", "user2@bob.com", "2222", null, User.Role.ADMIN, null);
    private static final User USER3 = new User(3L, "User 3", "user3@bob.com", "3333", null, User.Role.USER, null);

    private static final Category CAT1 = new Category(1L, "CAT 1", null);
    private static final Category CAT2 = new Category(2L, "CAT 2", null);
    private static final Category CAT3 = new Category(3L, "CAT 3", null);

    public PostsController(PostsRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    private List<Post> getAll() {
        return postRepository.findAll();
    }

    @GetMapping("{postId}")
    private Optional<Post> getById(@PathVariable Long postId) {
        return postRepository.findById(postId);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        System.out.println("Backend wants to create: " + newPost);
    }

    @PutMapping("{postId}")
    private void updatePost(@PathVariable Long postId, @RequestBody Post newPost) {
        System.out.printf("Backend wants to update post id %d with %s\n", postId, newPost);
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId) {
        System.out.printf("Backend wants to delete post id %d\n", postId);
    }

}