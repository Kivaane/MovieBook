package com.cinema.service;

import com.cinema.model.User;
import com.cinema.repository.UserRepository;
import com.cinema.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private com.cinema.util.Validator validator;
    
    // CREATE - Register new user
    public User registerUser(User user) {
        if (!com.cinema.util.Validator.isValidEmail(user.getEmail())) {
            throw new RuntimeException("Invalid email format!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        user.setRole("customer");
        user.setRegistrationDate(DateFormatter.getCurrentDate());
        user.setPassword(com.cinema.util.PasswordHasher.hash(user.getPassword()));
        return userRepository.save(user);
    }
    
    // READ - Login user
    public Optional<User> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && com.cinema.util.PasswordHasher.check(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
    
    // READ - Get user by ID
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }
    
    // READ - Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // UPDATE - Update user profile
    public boolean updateUser(User user) {
        return userRepository.update(user);
    }
    
    // DELETE - Delete user account
    public boolean deleteUser(Integer userId) {
        return userRepository.deleteById(userId);
    }
}
