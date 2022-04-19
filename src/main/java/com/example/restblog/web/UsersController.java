package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {
    private static final Category CAT1 = new Category(1L, "CAT 1", null);
    private static final Category CAT2 = new Category(2L, "CAT 2", null);
    private static final Category CAT3 = new Category(3L, "CAT 3", null);

    private static final Post POST1 = new Post(1L, "Post 1", "Blah", null, Arrays.asList(CAT1, CAT2));
    private static final Post POST2 = new Post(2L, "Post 2", "Blah", null, Arrays.asList(CAT2, CAT3));
    private static final Post POST3 = new Post(3L, "Post 3", "Blah", null, Arrays.asList(CAT1, CAT3));
    private static final Post POST4 = new Post(4L, "Post 4", "Blah", null, Arrays.asList(CAT1, CAT2));
    private static final Post POST5 = new Post(5L, "Post 5", "Blah", null, Arrays.asList(CAT2, CAT3));
    private static final Post POST6 = new Post(6L, "Post 6", "Blah", null, Arrays.asList(CAT1, CAT3));

    @GetMapping
    private List<User> getAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "bob smith", "bob@smith.com", "1234", null, User.Role.USER, Arrays.asList(POST1, POST2)));
        users.add(new User(2L, "tom jones", "tom@jones.com", "5678", null, User.Role.ADMIN, Arrays.asList(POST3, POST4)));
        users.add(new User(3L, "jane williams", "jane@williams.com", "9000", null, User.Role.USER, Arrays.asList(POST5, POST6)));
        return users;
    }

    @GetMapping("{userId}")
    private User getById(@PathVariable Long userId) {
        User user = new User(userId, "bob smith", "bob@smith.com", "1234", null, User.Role.USER, Arrays.asList(POST1, POST2));
        return user;
    }

    @GetMapping("username")
    private User getByUsername(@RequestParam String userName) {
        User user = new User(1L, userName, "user@bigfatuser.com", "1234", null, User.Role.USER, Arrays.asList(POST1, POST2));
        return user;
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam String email) {
        User user = new User(1L, "big fat user", email, "1234", null, User.Role.USER, Arrays.asList(POST1, POST2));
        return user;
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