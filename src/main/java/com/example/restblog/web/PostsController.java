package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {
    private static final User USER1 = new User(1L, "User 1", "user1@bob.com", "1111", null, User.Role.USER, null);
    private static final User USER2 = new User(2L, "User 2", "user2@bob.com", "2222", null, User.Role.ADMIN, null);
    private static final User USER3 = new User(3L, "User 3", "user3@bob.com", "3333", null, User.Role.USER, null);

    @GetMapping
    private List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", "Blah blah blah", USER1));
        posts.add(new Post(2L, "Post 2", "Blah blah blah blah blah", USER2));
        posts.add(new Post(3L, "Post 3", "Blah blah blah blah blah blah blah", USER1));
        return posts;
    }

    @GetMapping("{postId}")
    private Post getById(@PathVariable Long postId) {
        Post post = new Post(postId, "Post " + postId, "This is a test post", USER3);
        return post;
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