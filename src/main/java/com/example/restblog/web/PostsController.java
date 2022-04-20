package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private PostsRepository postsRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }


    @GetMapping
    private List<Post> getAll() {
        return postsRepository.findAll();
    }

    @GetMapping("{postId}")
    private Post getById(@PathVariable Long postId) {
        return postsRepository.getById(postId);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        Post post = new Post(1L, newPost.getTitle(), newPost.getContent());
        postsRepository.save(post);
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

}