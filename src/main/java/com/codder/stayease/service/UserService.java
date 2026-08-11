package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
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


    // ADD USER
    public User addUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repo.save(user);
    }


    // GET ALL USERS
    public List<User> getAllUser() {

        return repo.findAll();
    }


    // GET USER BY ID
    public User getUserById(long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }


    // UPDATE USER
    public User updateUser(long id, User user) {

        User u = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());

        // Encode the new password
        u.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repo.save(u);
    }


    // UPDATE ROLE
    public User updateRole(long id, String role) {

        User u = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        u.setRole(role);

        return repo.save(u);
    }


    // DELETE USER
    public void deleteUser(long id) {

        User u = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        repo.delete(u);
    }

    public User updatePassword(long id, String password) {

        User user = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setPassword(
                passwordEncoder.encode(password)
        );

        user.setEnabled(true);

        return repo.save(user);
    }
}