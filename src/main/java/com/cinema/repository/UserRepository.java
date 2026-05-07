package com.cinema.repository;

import com.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "users.txt";
    
    public User save(User user) {
        if (user.getUserId() == null) {
            user.setUserId(fileHandler.getNextId(FILENAME));
        }
        fileHandler.writeToFile(FILENAME, user.toFileString());
        return user;
    }
    
    public Optional<User> findById(Integer id) {
        String line = fileHandler.findById(FILENAME, id);
        return line != null ? Optional.ofNullable(User.fromFileString(line)) : Optional.empty();
    }
    
    public Optional<User> findByEmail(String email) {
        List<String> lines = fileHandler.searchByField(FILENAME, email, 2);
        for (String line : lines) {
            User u = User.fromFileString(line);
            if (u != null && u.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
    
    public List<User> findAll() {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(User::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    public boolean update(User user) {
        int lineNumber = fileHandler.getLineNumberById(FILENAME, user.getUserId());
        if (lineNumber >= 0) {
            return fileHandler.updateLine(FILENAME, lineNumber, user.toFileString());
        }
        return false;
    }
    
    public boolean deleteById(Integer id) {
        int lineNumber = fileHandler.getLineNumberById(FILENAME, id);
        return lineNumber >= 0 && fileHandler.deleteLine(FILENAME, lineNumber);
    }
    
    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
