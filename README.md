# 🏥 Clinic Management System

A console-based application built with **Java** to manage clinic operations, appointments, and user interactions. This project demonstrates the practical application of **Object-Oriented Programming (OOP)** principles.

## 📝 Project Overview
The **Clinic Management System** acts as a bridge between doctors and patients. It allows doctors to manage their schedules and patients to book appointments seamlessly. The system handles authentication, data management using collections, and simulates a real-world booking scenario within a console interface.

## ✨ Key Features

### 👤 User Authentication
- **Sign Up/Login:** Secure registration and login system for both Patients and Doctors.
- **Role-Based Access:** The system detects the user type (Doctor vs. Patient) and displays the appropriate menu.

### 👨‍⚕️ Doctor Module
- **Manage Schedule:** Doctors can add available appointment slots (Date & Time).
- **Profile Management:** View and update professional details (Speciality, Price, etc.).

### 🤒 Patient Module
- **Search Doctors:** Find doctors by name or ID.
- **Book Appointments:** View available slots and book an appointment instantly.
- **Reservation Details:** View reservation ID and doctor details upon confirmation.

### 🏨 Clinic Administration
- **Data Handling:** Centralized management of all doctors and appointments using `ArrayLists`.

## 🛠️ Technical Concepts Applied (OOP)
This project was built to demonstrate mastery of core Java and OOP concepts:
- **Inheritance:** `Doctor` and `Patient` classes inherit common attributes from the abstract `User` class.
- **Polymorphism:** The `AuthManager` handles different user types dynamically during login.
- **Encapsulation:** All data fields are private and accessed via getters and setters to ensure data integrity.
- **Abstraction:** Utilization of abstract classes and methods to define a blueprint for users.
- **Composition:** The `Doctor` class contains a list of `Appointment` objects.

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/YourUsername/Clinic-Management-System.git](https://github.com/YourUsername/Clinic-Management-System.git)
