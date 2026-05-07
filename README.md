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



## 📸 Screenshots

### Homepage
<img width="1919" height="1005" alt="image" src="https://github.com/user-attachments/assets/ba54b3a6-1f4b-4c6b-b8cd-bb4bda674978" />


### Seat Selection
<img width="1919" height="1002" alt="image" src="https://github.com/user-attachments/assets/1923e692-0abb-4244-86e4-87d45aca77f4" />


### Movie Catalog
<img width="1917" height="960" alt="image" src="https://github.com/user-attachments/assets/2e000b68-9294-4834-9f57-371f6bb179fd" />

### Booking Details
<img width="1919" height="1010" alt="image" src="https://github.com/user-attachments/assets/5c303c06-11e4-475f-98e2-1831a697d3cd" />

###Feedback
<img width="1901" height="980" alt="image" src="https://github.com/user-attachments/assets/ba5eda19-a0ce-4e53-8f28-f5d0e4487a1f" />

###Hall Details
<img width="1919" height="996" alt="image" src="https://github.com/user-attachments/assets/f7402b67-b5f7-46fb-b207-c4e15bd6e544" />
<img width="1896" height="999" alt="image" src="https://github.com/user-attachments/assets/663fadb2-deb3-4438-b1d9-cd3fc598fc7d" />
<img width="1886" height="988" alt="image" src="https://github.com/user-attachments/assets/725ee84c-8d57-4ec0-9cf7-5171da1a6940" />


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

