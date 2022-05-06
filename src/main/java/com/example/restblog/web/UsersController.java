package com.example.restblog.web;

import com.example.restblog.data.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    private List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("{userId}")
    private Optional<User> getById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    // return a user object for the logged in user
    @GetMapping("me")
    private User getMyInfo(OAuth2Authentication auth) {
        String email = auth.getName(); // yes, the email is found under "getName()"
        return userRepository.findByEmail(email);
    }


    @GetMapping("username")
    private User getByUsername(@RequestParam String userName) {
        return userRepository.findByUsername(userName);
    }

    @GetMapping("email")
    private User getByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    // create user is now at endpoint /api/users/create
    @PostMapping("create")
    private void createUser(@RequestBody User newUser) {
        System.out.println("Backend wants to create: " + newUser);
        newUser.setRole(User.Role.USER);
        String plainTextPassword = newUser.getPassword();
        String encryptedPassword = passwordEncoder.encode(plainTextPassword);
        newUser.setPassword(encryptedPassword);
        userRepository.save(newUser);
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