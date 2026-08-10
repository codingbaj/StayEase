package com.codder.stayease.controller;

import com.codder.stayease.entity.User;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/add")
    public ApiResponse addUser(@RequestBody User user) {

        User savedUser = service.addUser(user);

        return new ApiResponse(
                true,
                savedUser.getName()+" added successfully",
                savedUser
        );
    }

    @GetMapping("/all")
    public ApiResponse getAllUser() {

        return new ApiResponse(
                true,
                "Users fetched successfully",
                service.getAllUser()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable long id) {

        return new ApiResponse(
                true,
                "User fetched successfully",
                service.getUserById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateUser(@PathVariable long id,
                                  @RequestBody User user) {

        return new ApiResponse(
                true,
                "User updated successfully",
                service.updateUser(id, user)
        );
    }

    @PutMapping("/update-role/{id}")
    public ApiResponse updateRole(@PathVariable long id,
                                  @RequestParam String role) {

        return new ApiResponse(
                true,
                "User role updated successfully",
                service.updateRole(id, role)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteUser(@PathVariable long id) {

        service.deleteUser(id);

        return new ApiResponse(
                true,
                "User deleted successfully",
                null
        );
    }
}