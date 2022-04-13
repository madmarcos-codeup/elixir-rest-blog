package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    private List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", "Blah blah blah"));
        posts.add(new Post(2L, "Post 2", "Blah blah blah blah blah"));
        posts.add(new Post(3L, "Post 3", "Blah blah blah blah blah blah blah"));
        return posts;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {
        Post post = new Post(id, "Post " + id, "This is a test post");
        return post;
    }
}
