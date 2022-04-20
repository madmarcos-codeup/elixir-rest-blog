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
        List<Post> posts = new ArrayList<>();
        return posts;
    }

//    @GetMapping("{postId}")
//    private Post getById(@PathVariable Long postId) {
//        Post post = new Post(postId, "Post " + postId, "This is a test post", USER3, Arrays.asList(CAT1, CAT2));
//        return post;
//    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        Post post = new Post(1L, newPost.getTitle(), newPost.getContent());
        postsRepository.save(post);
        System.out.println("New post created");
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