package com.demo.users.controller;

@RestController
@RequestMapping("/api/users")
public class orderRepositoryController {

    @GetMapping
    public List<User> getorders() {
        System.out.println("Fetching all orders from database...");
        return "orders";
    }
}
