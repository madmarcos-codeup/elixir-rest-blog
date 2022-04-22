package com.example.restblog.web;

import com.example.restblog.data.*;
import com.example.restblog.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {
    private final PostsRepository postRepository;
    private final UsersRepository userRepository;
    private final CategoriesRepository categoriesRepository;
    private final EmailService emailService;

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
        // associate user 1 with the post
        // also plug in all categories (1, 2, 3)
        newPost.setAuthor(userRepository.getById(1L));
        newPost.setCategories(Arrays.asList(categoriesRepository.getById(1L),
                categoriesRepository.getById(2L),
                categoriesRepository.getById(3L)));
        postRepository.save(newPost);

        emailService.prepareAndSend(newPost, "Hey a new Post!", "See subject");
    }

    @PutMapping("{postId}")
    private void updatePost(@PathVariable Long postId, @RequestBody Post newPost) {
        System.out.printf("Backend wants to update post id %d with %s\n", postId, newPost);
        Post originalPost = postRepository.getById(postId);
        BeanUtils.copyProperties(newPost, originalPost, getNullPropertyNames(newPost));
        postRepository.save(originalPost);
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId) {
        System.out.printf("Backend wants to delete post id %d\n", postId);
        postRepository.deleteById(postId);
    }

    // CONVENIENCE METHOD TO AVOID NULL CHECKS FOR EVERY PROPERTY ON UPDATE
    // thank you Ry!!
    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


}