# CineBook - Luxury Movie Reservation Platform

## Project Overview
CineBook is a premium, enterprise-grade Online Movie Ticket Reservation Platform built with **Spring Boot 3.2.0**, **Thymeleaf**, and **File-based Persistence**. It features a stunning "Luxury Cinema" aesthetic with dark themes, gold accents, and glassmorphism.

## Features
- **User Management**: Registration, Login (Session-based), Profile Management.
- **Movie Catalog**: Advanced search, filtering, and detailed movie profiles.
- **Interactive Booking**: 10x10 seat selection grid with real-time availability and double-booking prevention.
- **Theater Management**: Hall configurations (IMAX, 4DX, Standard).
- **Payment & Transactions**: Secure payment simulation with transaction history.
- **Reviews & Feedback**: Movie rating system with "Verified Booking" badges and customer feedback.
- **Admin Dashboard**: Comprehensive management of movies, halls, bookings, and feedback.

## Tech Stack
- **Backend**: Java 17, Spring Boot, Maven, Lombok.
- **Frontend**: Thymeleaf, Bootstrap 5.3, Font Awesome 6.
- **Persistence**: File I/O (TXT files) located in `src/main/resources/data/`.
- **Aesthetics**: Custom CSS with Glassmorphism and Luxury styling.

## OOP Concepts Demonstrated
- **Abstraction**: `Person`, `Media`, `Venue`, `Reservation`, `Transaction`, `UserContent` abstract classes.
- **Inheritance**: 6 distinct inheritance hierarchies (e.g., `Person` -> `User` -> `Admin`).
- **Encapsulation**: Private fields with Lombok getters/setters and service-layer validation.
- **Polymorphism**: Method overriding across all model hierarchies and method overloading in services.

## Setup Instructions
1. Open the project in IntelliJ IDEA or Eclipse.
2. Ensure you have Java 17 installed.
3. Maven will automatically download dependencies from `pom.xml`.
4. Run `MovieReservationApplication.java`.
5. Access the application at `http://localhost:8080`.

## Sample Admin Credentials
- **Email**: `admin@cinema.com`
- **Password**: `admin123`

---
*Developed for Academic Purposes - Demonstrating Advanced Java Web Development.*
