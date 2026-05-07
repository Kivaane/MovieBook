# 🎬 MovieBook - Movie Reservation Platform

A luxury cinema ticket booking system built with Spring Boot, featuring interactive seat selection and premium UI/UX design.

## 📋 Overview

MovieBook is an online movie ticket reservation platform that allows users to browse movies, select seats interactively, and complete bookings. Built as an academic project demonstrating Object-Oriented Programming concepts and file-based data persistence.

## ✨ Features

- 🎫 User registration and login
- 🎥 Browse and search movies
- 🪑 Interactive 10×10 seat selection grid
- 💳 Payment processing
- 📋 Booking management
- ⭐ Movie reviews and ratings
- 🏛️ Theater hall information
- 👨‍💼 Admin dashboard

## 🛠️ Technologies

- **Backend:** Java 17, Spring Boot 3.2, Maven
- **Frontend:** Thymeleaf, Bootstrap 5, JavaScript
- **Storage:** File I/O (TXT files)
- **Server:** Embedded Tomcat (included in Spring Boot)

## 🚀 How to Run

1. **Prerequisites:**
   - Java 17 or higher
   - Maven 3.6+
   - IntelliJ IDEA (recommended)

2. **Clone & Open:**
```bash
   git clone https://github.com/YOUR_USERNAME/moviebook-platform.git
   cd moviebook-platform
```

3. **Run:**
Open in IntelliJ IDEA
Run MovieReservationApplication.java

4. **Access:**
http://localhost:8080

## 🔑 Default Credentials

**User:**
- Email: `imasha@email.com`
- Password: `pass123`

**Admin:**
- Email: `admin@cinema.com`
- Password: `admin123`

## 📂 Project Structure
MovieBook/
├── src/main/java/com/cinema/
│   ├── controller/        # MVC Controllers
│   ├── model/            # Entity classes
│   ├── service/          # Business logic
│   ├── repository/       # Data access layer
│   └── util/             # Helper classes
├── src/main/resources/
│   ├── templates/        # HTML pages
│   ├── static/           # CSS, JS, images
│   └── data/             # TXT data files
└── pom.xml

## 🧠 OOP Concepts

- **Encapsulation:** Private fields with getters/setters
- **Inheritance:** Person → User → Admin
- **Polymorphism:** Method overloading and overriding

## 🔄 CRUD Operations

| Component | Create | Read | Update | Delete |
|-----------|--------|------|--------|--------|
| Users | ✅ | ✅ | ✅ | ✅ |
| Movies | ✅ | ✅ | ✅ | ✅ |
| Bookings | ✅ | ✅ | - | ✅ |
| Halls | ✅ | ✅ | ✅ | ✅ |
| Payments | ✅ | ✅ | ✅ | - |
| Reviews | ✅ | ✅ | ✅ | ✅ |

**Total: 28 CRUD operations**

## 👥 Team Members

- **Imasha Silva** - User Management
- **Nixula Perera** - Movie Catalog
- **Avindu Fernando** - Booking & Seats
- **Thamalu Wickrama** - Hall Management
- **Sanrujan Raj** - Reviews & Feedback

## 📸 Screenshots

### Homepage
![Homepage](screenshots/homepage.png)

### Seat Selection
![Seat Selection](screenshots/booking.png)

### Movie Catalog
![Movies](screenshots/movies.png)

## 🎯 Academic Requirements

✅ Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)  
✅ Minimum 3 CRUD operations per component  
✅ File read/write for data storage  
✅ User-friendly interface  
✅ IntelliJ IDEA development  
✅ GitHub version control  

## 📄 File Storage

Data stored in TXT files:
- `users.txt` - User accounts
- `movies.txt` - Movie catalog
- `bookings.txt` - Reservations
- `seats.txt` - Seat availability
- `halls.txt` - Theater halls
- `payments.txt` - Transactions
- `reviews.txt` - User reviews
- `feedback.txt` - User feedback

## 🔮 Future Enhancements

- [ ] Email notifications
- [ ] QR code tickets
- [ ] Real payment gateway integration
- [ ] Mobile app
- [ ] Database migration

## 📞 Contact

**GitHub:** [https://github.com/YOUR_USERNAME/moviebook-platform](https://github.com/YOUR_USERNAME/moviebook-platform)  
**Email:** your.email@example.com

## 📝 License

Academic project for SE1020 - Object Oriented Programming course.

