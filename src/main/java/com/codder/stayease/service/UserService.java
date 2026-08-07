package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.entity.User;
import com.codder.stayease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User addUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUser() {
        return repo.findAll();
    }

    public User getUserById(long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    public User updateUser(long id, User user) {

        User u = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setPhone(user.getPhone());

        return repo.save(u);
    }

    public User updateRole(long id, String role) {

        User u = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        u.setRole(role);

        return repo.save(u);
    }

    public void deleteUser(long id) {

        User u = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        repo.delete(u);
    }
}