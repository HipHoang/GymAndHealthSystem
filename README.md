# 🏋️ Gym Management System

A full-stack gym management application designed to streamline membership management, training schedules, payments, and administrative operations.

The project is built using **Spring Boot**, **ReactJS**, and **MySQL**, following a RESTful API architecture.

---

## 🚀 Features

* Member management
* Membership package management
* Trainer management
* Workout schedule tracking
* Payment and invoice management
* User authentication and authorization
* Role-based access control (Admin, Staff, Member)

---

## 🛠 Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate

### Frontend

* ReactJS
* Axios
* React Router

### Database

* MySQL

---

## 📂 Project Structure

```text
GymAndHealthSystem/
│
├── backend/          # Spring Boot application
│
├── frontend/         # ReactJS application
│
└── README.md
```

---

## ⚙️ Installation Guide

### 1. Clone the Repository

```bash
git clone https://github.com/HipHoang/GymAndHealthSystem.git
cd GymAndHealthSystem
```

---

### 2. Configure Database

Update the database configuration in:

```text
backend/src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gym_management
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### 3. Run the Backend

```bash
cd backend
./mvnw spring-boot:run
```

Or:

```bash
mvn spring-boot:run
```

Backend server:

```text
http://localhost:8080
```

---

### 4. Run the Frontend

```bash
cd frontend
npm install
npm start
```

Frontend server:

```text
http://localhost:3000
```

---

## 🔒 Security

The application uses Spring Security and JWT Authentication to provide:

* Secure login and registration
* Protected API endpoints
* Role-based authorization
* Session-free authentication

---

## 📊 Core Modules

* User Management
* Membership Management
* Trainer Management
* Schedule Management
* Payment Management
* Administrative Dashboard

---

## 👨‍💻 Author

**Hoang Minh Hiep**

GitHub: https://github.com/HipHoang

---

## 📄 License

This project is licensed under the MIT License.
