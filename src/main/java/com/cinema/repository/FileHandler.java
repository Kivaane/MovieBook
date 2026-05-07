package com.cinema.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;

/**
 * Utility class for handling all file-based I/O operations.
 * Implements the Repository pattern for data persistence using TXT files.
 * Provides thread-safe operations with synchronized methods.
 */
@Component
public class FileHandler {
    
    @Value("${app.data.directory}")
    private String dataDir;
    
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(dataDir));
        } catch (IOException e) {
            System.err.println("Could not create data directory: " + e.getMessage());
        }
    }
    
    // Write to file (append mode)
    public synchronized boolean writeToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(dataDir + filename, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Read all lines from file
    public List<String> readFromFile(String filename) {
        try {
            Path path = Paths.get(dataDir + filename);
            if (!Files.exists(path)) {
                Files.createFile(path);
                return new ArrayList<>();
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    // Update specific line
    public synchronized boolean updateLine(String filename, int lineNumber, String newData) {
        try {
            List<String> lines = readFromFile(filename);
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.set(lineNumber, newData);
                return writeAllLines(filename, lines);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete specific line
    public synchronized boolean deleteLine(String filename, int lineNumber) {
        try {
            List<String> lines = readFromFile(filename);
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.remove(lineNumber);
                return writeAllLines(filename, lines);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Write all lines (overwrite)
    public synchronized boolean writeAllLines(String filename, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(dataDir + filename, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get next available ID
    public int getNextId(String filename) {
        List<String> lines = readFromFile(filename);
        if (lines.isEmpty()) {
            return 1;
        }
        String lastLine = lines.get(lines.size() - 1);
        try {
            int lastId = Integer.parseInt(lastLine.split(",")[0]);
            return lastId + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    
    // Search by field index
    public List<String> searchByField(String filename, String searchTerm, int fieldIndex) {
        return readFromFile(filename).stream()
                .filter(line -> {
                    String[] parts = line.split(",");
                    return parts.length > fieldIndex && 
                           parts[fieldIndex].toLowerCase().contains(searchTerm.toLowerCase());
                })
                .collect(Collectors.toList());
    }
    
    // Find line by ID (assuming ID is first field)
    public String findById(String filename, int id) {
        return readFromFile(filename).stream()
                .filter(line -> {
                    String[] parts = line.split(",");
                    return parts.length > 0 && Integer.parseInt(parts[0]) == id;
                })
                .findFirst()
                .orElse(null);
    }
    
    // Get line number by ID
    public int getLineNumberById(String filename, int id) {
        List<String> lines = readFromFile(filename);
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            try {
                if (parts.length > 0 && Integer.parseInt(parts[0]) == id) {
                    return i;
                }
            } catch (NumberFormatException ignored) {}
        }
        return -1;
    }
}
