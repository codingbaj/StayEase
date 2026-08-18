package com.codder.stayease.controller;

import com.codder.stayease.dto.ChangePasswordRequest;
import com.codder.stayease.entity.User;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    // =====================================================
    // ADD USER
    // =====================================================

    @PostMapping("/add")
    public ApiResponse addUser(
            @RequestBody User user) {

        User savedUser =
                service.addUser(user);

        return new ApiResponse(
                true,
                savedUser.getName()
                        + " added successfully",
                savedUser
        );
    }


    // =====================================================
    // GET ALL USERS
    // =====================================================

    @GetMapping("/all")
    public ApiResponse getAllUser() {

        return new ApiResponse(
                true,
                "Users fetched successfully",
                service.getAllUser()
        );
    }


    // =====================================================
    // GET USER BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ApiResponse getUserById(
            @PathVariable long id) {

        return new ApiResponse(
                true,
                "User fetched successfully",
                service.getUserById(id)
        );
    }


    // =====================================================
    // ADMIN UPDATE USER
    //
    // Password is NOT changed here.
    //
    // Admin can update:
    // - Name
    // - Email
    // - Phone
    // - Role
    // - Enabled / Disabled
    // =====================================================

    @PutMapping("/update/{id}")
    public ApiResponse updateUser(
            @PathVariable long id,
            @RequestBody User user) {

        User updatedUser =
                service.updateUser(id, user);

        return new ApiResponse(
                true,
                "User details updated successfully",
                updatedUser
        );
    }


    // =====================================================
    // ADMIN UPDATE ROLE
    // =====================================================

    @PutMapping("/update-role/{id}")
    public ApiResponse updateRole(
            @PathVariable long id,
            @RequestParam String role) {

        User user =
                service.updateRole(id, role);

        return new ApiResponse(
                true,
                "User role updated successfully",
                user
        );
    }


    // =====================================================
    // ADMIN ENABLE / DISABLE USER
    // =====================================================

    @PutMapping("/update-status/{id}")
    public ApiResponse updateStatus(
            @PathVariable long id,
            @RequestParam boolean enabled) {

        User user =
                service.updateEnabled(
                        id,
                        enabled
                );

        return new ApiResponse(
                true,
                enabled
                        ? "User enabled successfully"
                        : "User disabled successfully",
                user
        );
    }


    // =====================================================
    // LOGGED-IN USER CHANGE OWN PASSWORD
    //
    // User does NOT send user ID.
    //
    // Spring Security identifies the logged-in user.
    // =====================================================

    @PutMapping("/change-password")
    public ApiResponse changeOwnPassword(
            @RequestBody ChangePasswordRequest request,
            Authentication authentication) {

        User loggedInUser =
                (User) authentication.getPrincipal();

        User updatedUser =
                service.changeOwnPassword(
                        loggedInUser.getId(),
                        request
                );

        return new ApiResponse(
                true,
                "Password changed successfully",
                updatedUser
        );
    }


    // =====================================================
    // DELETE USER
    // =====================================================

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteUser(
            @PathVariable long id) {

        service.deleteUser(id);

        return new ApiResponse(
                true,
                "User deleted successfully",
                null
        );
    }
}