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
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "bob smith", "bob@smith.com", "1234", null, User.Role.USER));
        users.add(new User(2L, "tom jones", "tom@jones.com", "5678", null, User.Role.ADMIN));
        users.add(new User(3L, "jane williams", "jane@williams.com", "9000", null, User.Role.USER));
        return users;
    }

    @GetMapping("{userId}")
    private User getById(@PathVariable Long userId) {
        User user = new User(userId, "bob smith", "bob@smith.com", "1234", null, User.Role.USER);
        return user;
    }

    @GetMapping("username")
    private User getByUsername(@RequestParam String userName) {
        User user = new User(1L, userName, "user@bigfatuser.com", "1234", null, User.Role.USER);
        return user;
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam String email) {
        User user = new User(1L, "big fat user", email, "1234", null, User.Role.USER);
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

    @PutMapping("{userId}/password")
    private void updateUserPassword(@PathVariable Long userId, @RequestBody User updatedUser) {
        System.out.printf("Backend wants to update user password for id %d with %s\n", userId, updatedUser);
    }

    @DeleteMapping("{userId}")
    private void deleteUser(@PathVariable Long userId) {
        System.out.printf("Backend wants to delete user id %d\n", userId);
    }

}