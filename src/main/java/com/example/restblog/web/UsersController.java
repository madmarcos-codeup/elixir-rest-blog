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

    @GetMapping
    private List<User> getAll() {
        List<User> users = new ArrayList<>();
        return users;
    }

//    @GetMapping("{userId}")
//    private User getById(@PathVariable Long userId) {
//        User user = new User(userId, "bob smith", "bob@smith.com", "1234", null, User.Role.USER, Arrays.asList(POST1, POST2));
//        return user;
//    }

//    @GetMapping("username")
//    private User getByUsername(@RequestParam String userName) {
//        User user = new User(1L, userName, "user@bigfatuser.com", "1234", null, User.Role.USER, Arrays.asList(POST1, POST2));
//        return user;
//    }
//
//    @GetMapping("email")
//    private User getByEmail(@RequestParam String email) {
//        User user = new User(1L, "big fat user", email, "1234", null, User.Role.USER, Arrays.asList(POST1, POST2));
//        return user;
//    }

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