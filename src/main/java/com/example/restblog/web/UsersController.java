package com.example.restblog.web;

import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    private List<User> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping("{userId}")
    private Optional<User> getById(@PathVariable Long userId) {
        return usersRepository.findById(userId);
    }

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
        User userToAdd = newUser;
        newUser.setCreatedAt(LocalDate.now());
        newUser.setRole(User.Role.USER);
        usersRepository.save(userToAdd);
        System.out.println("User created!");
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