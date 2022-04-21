package com.example.restblog.web;

import com.example.restblog.data.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {
    private final UsersRepository userRepository;

    private static final Category CAT1 = new Category(1L, "CAT 1", null);
    private static final Category CAT2 = new Category(2L, "CAT 2", null);
    private static final Category CAT3 = new Category(3L, "CAT 3", null);

    private static final Post POST1 = new Post(1L, "Post 1", "Blah", null, Arrays.asList(CAT1, CAT2));
    private static final Post POST2 = new Post(2L, "Post 2", "Blah", null, Arrays.asList(CAT2, CAT3));
    private static final Post POST3 = new Post(3L, "Post 3", "Blah", null, Arrays.asList(CAT1, CAT3));
    private static final Post POST4 = new Post(4L, "Post 4", "Blah", null, Arrays.asList(CAT1, CAT2));
    private static final Post POST5 = new Post(5L, "Post 5", "Blah", null, Arrays.asList(CAT2, CAT3));
    private static final Post POST6 = new Post(6L, "Post 6", "Blah", null, Arrays.asList(CAT1, CAT3));

    public UsersController(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    private List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("{userId}")
    private Optional<User> getById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @GetMapping("username")
    private User getByUsername(@RequestParam String userName) {
        return userRepository.findByUsername(userName);
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        System.out.println("Backend wants to create: " + newUser);
    }

    @PutMapping("{userId}")
    private void updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        System.out.printf("Backend wants to update user id %d with %s\n", userId, updatedUser);
    }

    @PutMapping("{userId}/updatePassword")
    private void updateUserPassword(@PathVariable Long userId, @RequestParam(required = false) String oldPassword, @RequestParam String newPassword) {
        System.out.printf("Backend wants to update user password for id %d with old pw %s new pw %s\n", userId, oldPassword, newPassword);
    }

    @DeleteMapping("{userId}")
    private void deleteUser(@PathVariable Long userId) {
        System.out.printf("Backend wants to delete user id %d\n", userId);
    }

}