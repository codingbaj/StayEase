package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.ChangePasswordRequest;
import com.codder.stayease.entity.User;
import com.codder.stayease.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // =====================================================
    // ADD USER
    // =====================================================

    public User addUser(User user) {

        // Password is required when creating a user
        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Password is required"
            );
        }

        // Encode password before saving
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        return repo.save(user);
    }


    // =====================================================
    // GET ALL USERS
    // =====================================================

    public List<User> getAllUser() {

        return repo.findAll();
    }


    // =====================================================
    // GET USER BY ID
    // =====================================================

    public User getUserById(long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));
    }


    // =====================================================
    // ADMIN UPDATE USER
    //
    // Admin can update:
    // - Name
    // - Email
    // - Phone
    // - Role
    // - Enabled / Disabled
    //
    // IMPORTANT:
    // Password is NOT changed here.
    // =====================================================

    public User updateUser(
            long id,
            User user) {

        User existingUser =
                repo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"
                                ));


        // -------------------------------------------------
        // NAME
        // -------------------------------------------------

        if (user.getName() != null &&
                !user.getName().trim().isEmpty()) {

            existingUser.setName(
                    user.getName().trim()
            );
        }


        // -------------------------------------------------
        // EMAIL
        // -------------------------------------------------

        if (user.getEmail() != null &&
                !user.getEmail().trim().isEmpty()) {

            existingUser.setEmail(
                    user.getEmail().trim()
            );
        }


        // -------------------------------------------------
        // PHONE
        // -------------------------------------------------

        if (user.getPhone() != null &&
                !user.getPhone().trim().isEmpty()) {

            existingUser.setPhone(
                    user.getPhone().trim()
            );
        }


        // -------------------------------------------------
        // ROLE
        // -------------------------------------------------

        if (user.getRole() != null &&
                !user.getRole().trim().isEmpty()) {

            existingUser.setRole(
                    user.getRole().trim()
            );
        }


        // -------------------------------------------------
        // ENABLE / DISABLE
        // -------------------------------------------------

        existingUser.setEnabled(
                user.isEnabled()
        );


        // -------------------------------------------------
        // PASSWORD
        // -------------------------------------------------
        //
        // DO NOT TOUCH existingUser.password here.
        //
        // Admin does not need to enter a password
        // when editing user details.
        //
        // The existing password remains unchanged.
        // -------------------------------------------------


        return repo.save(existingUser);
    }


    // =====================================================
    // ADMIN UPDATE ROLE
    // =====================================================

    public User updateRole(
            long id,
            String role) {

        User user =
                repo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"
                                ));


        if (role == null ||
                role.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Role is required"
            );
        }


        user.setRole(
                role.trim()
        );


        return repo.save(user);
    }


    // =====================================================
    // ADMIN ENABLE / DISABLE USER
    // =====================================================

    public User updateEnabled(
            long id,
            boolean enabled) {

        User user =
                repo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"
                                ));


        user.setEnabled(
                enabled
        );


        return repo.save(user);
    }


    // =====================================================
    // USER CHANGE OWN PASSWORD
    //
    // The user ID comes from the logged-in user.
    //
    // We DO NOT accept a user ID from the frontend.
    // =====================================================

    public User changeOwnPassword(
            long userId,
            ChangePasswordRequest request) {


        // -------------------------------------------------
        // REQUEST VALIDATION
        // -------------------------------------------------

        if (request == null) {

            throw new IllegalArgumentException(
                    "Password change request is required"
            );
        }


        // -------------------------------------------------
        // FIND LOGGED-IN USER
        // -------------------------------------------------

        User user =
                repo.findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"
                                ));


        // -------------------------------------------------
        // CURRENT PASSWORD
        // -------------------------------------------------

        if (request.getCurrentPassword() == null ||
                request.getCurrentPassword()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Current password is required"
            );
        }


        // Check current password against
        // the encoded password stored in database.

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "Current password is incorrect"
            );
        }


        // -------------------------------------------------
        // NEW PASSWORD
        // -------------------------------------------------

        if (request.getNewPassword() == null ||
                request.getNewPassword()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "New password is required"
            );
        }


        // Minimum 6 characters

        if (request.getNewPassword().length() < 6) {

            throw new IllegalArgumentException(
                    "New password must be at least 6 characters"
            );
        }


        // -------------------------------------------------
        // CONFIRM PASSWORD
        // -------------------------------------------------

        if (request.getConfirmPassword() == null ||
                !request.getNewPassword()
                        .equals(
                                request.getConfirmPassword()
                        )) {

            throw new IllegalArgumentException(
                    "New password and confirm password do not match"
            );
        }


        // -------------------------------------------------
        // PREVENT SAME PASSWORD
        // -------------------------------------------------

        if (passwordEncoder.matches(
                request.getNewPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "New password must be different from current password"
            );
        }


        // -------------------------------------------------
        // ENCODE NEW PASSWORD
        // -------------------------------------------------

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );


        // -------------------------------------------------
        // SAVE USER
        // -------------------------------------------------

        return repo.save(user);
    }


    // =====================================================
    // DELETE USER
    // =====================================================

    public void deleteUser(long id) {

        User user =
                repo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"
                                ));


        repo.delete(user);
    }
}