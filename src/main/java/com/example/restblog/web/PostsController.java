package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    private List<Post> getAll() {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 11111", "Blah blah blah"));
        posts.add(new Post(2L, "Post 22222", "Blah blah blah blah blah"));
        posts.add(new Post(3L, "Post 33333", "Blah blah blah blah blah blah blah"));
        return posts;
    }

    // GET /api/posts/5  <-- fetch the blog post with id 5
    @GetMapping("{postId}")
    private Post getById(@PathVariable Long postId){
        Post post = new Post(postId, "Post " + postId, "Blah blah blah");
        return post;
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        System.out.println("Ready to add post: " + newPost);
    }
}
