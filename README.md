# Gym Management System

Hệ thống quản lý phòng tập gym được xây dựng bằng Java Spring Boot (Backend) và ReactJS (Frontend), hỗ trợ quản lý hội viên, gói tập, huấn luyện viên, lịch tập, thanh toán và phân quyền người dùng.

Công nghệ sử dụng:
- Backend: Java, Spring Boot, Spring Security, JWT, Spring Data JPA, Hibernate
- Frontend: ReactJS, Axios, React Router
- Database: MySQL

Chức năng chính:
- Quản lý hội viên, gói tập, huấn luyện viên
- Đăng ký và theo dõi lịch tập
- Quản lý thanh toán và hóa đơn
- Đăng nhập và phân quyền (Admin / Staff / Member)

Cấu trúc project:
- backend/: Spring Boot application
- frontend/: ReactJS application

Hướng dẫn chạy project:

Clone repository:
git clone https://github.com/HipHoang/GymAndHealthSystem.git
cd GymAndHealthSystem

Backend:
Cấu hình database trong application.properties  
spring.datasource.url=jdbc:mysql://localhost:8080/GymHealth/login  
spring.datasource.username=root  
spring.datasource.password=root

Chạy backend:
cd backend  
./mvnw spring-boot:run  
Backend chạy tại http://localhost:8080/GymHealth/login

Frontend:
cd frontend  
npm install  
npm start  
Frontend chạy tại http://localhost:3000

License: MIT License

Author: Minh Hiep 
GitHub: https://github.com/HipHoang
