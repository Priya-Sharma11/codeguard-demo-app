package com.demo.users.controller;

import com.demo.users.model.User;
import com.demo.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        System.out.println("Fetching all users from database...");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("Creating user: " + user.getName());
        if (user.getEmail() == null) {
            throw new RuntimeException("email required");
        }
        return userRepository.save(user);
    }
}
