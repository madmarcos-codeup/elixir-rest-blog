package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    @GetMapping
    private List<User> getAll() {
        List<User> posts = new ArrayList<>();
        posts.add(new User(1L, "User 1", "email 1", "1111", null, User.Role.ADMIN));
        posts.add(new User(2L, "User 2", "email 2", "2222", null, User.Role.USER));
        posts.add(new User(3L, "User 3", "email 3", "3333", null, User.Role.USER));
        return posts;
    }

    @GetMapping("{userId}")
    private User getById(@PathVariable Long userId) {
        User user = new User(userId, "User 1", "email 1", "1111", null, User.Role.ADMIN);
        return user;
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        System.out.println("Backend wants to create: " + newUser);
    }

    @PutMapping("{userId}")
    private void updateUser(@PathVariable Long userId, @RequestBody User newUser) {
        System.out.printf("Backend wants to update user id %d with %s\n", userId, newUser);
    }

    @DeleteMapping("{userId}")
    private void deletePost(@PathVariable Long userId) {
        System.out.printf("Backend wants to delete user id %d\n", userId);
    }

}