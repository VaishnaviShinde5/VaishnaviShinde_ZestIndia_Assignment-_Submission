# 🚀 Employee Management System
### Built for **Zest India IT Services Pvt. Ltd.** — Software Developer (Java) Assignment

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=for-the-badge&logo=springboot)
![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?style=for-the-badge&logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Secured-red?style=for-the-badge&logo=jsonwebtokens)
![Maven](https://img.shields.io/badge/Maven-3.x-purple?style=for-the-badge&logo=apachemaven)

---

## 🖥️ Live Preview

### Login Page
![Login Page](output_screenshot/login.png)

### Register Page
![Register Page](output_screenshot/register.png)

### Dashboard — Overview
![Dashboard Overview](output_screenshot/Dashboard_3.png)

### Employee Management
![Employee Table](output_screenshot\employees-table.png)

---

## ✨ Features

- 🔐 **JWT Authentication** — Secure register & login with Bearer token
- 👥 **Employee CRUD** — Create, Read, Update, Delete employees
- 🔍 **Search** — Search by name, email, department, position
- 📄 **Pagination & Sorting** — Paginated results with flexible sorting
- 🏢 **Department Filter** — Filter employees by department
- 🛡️ **Spring Security** — Protected endpoints, stateless sessions
- 🎨 **Modern UI** — Dark-themed dashboard built into the app
- ✅ **21 Unit Tests** — Repository + Service + Auth layers fully tested

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Core language |
| Spring Boot | 3.2.5 | Application framework |
| Spring Security | Included | Authentication & authorization |
| Spring Data JPA | Included | Database ORM |
| MySQL | 8.x | Relational database |
| JWT (JJWT) | 0.11.5 | Token-based auth |
| Lombok | 1.18.32 | Boilerplate reduction |
| Maven | 3.x | Build tool |
| JUnit 5 + Mockito | Included | Unit testing |

---

## 📁 Project Structure

```
employee-management/
├── src/
│   ├── main/
│   │   ├── java/com/zestindia/employeemanagement/
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java          # Spring Security + CORS + JWT config
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java          # Register & Login endpoints
│   │   │   │   └── EmployeeController.java      # CRUD + Search + Pagination
│   │   │   ├── dto/
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── AuthResponse.java
│   │   │   │   ├── EmployeeRequest.java
│   │   │   │   ├── EmployeeResponse.java
│   │   │   │   └── ApiResponse.java             # Generic response wrapper
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   └── Employee.java
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── EmployeeRepository.java
│   │   │   ├── security/
│   │   │   │   ├── JwtService.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   └── service/
│   │   │       ├── AuthService.java
│   │   │       ├── EmployeeService.java
│   │   │       └── impl/
│   │   │           ├── AuthServiceImpl.java
│   │   │           └── EmployeeServiceImpl.java
│   │   └── resources/
│   │       ├── static/
│   │       │   └── index.html                   # Frontend UI (served by Spring Boot)
│   │       └── application.properties
│   └── test/
│       └── java/com/zestindia/employeemanagement/
│           ├── service/
│           │   ├── EmployeeServiceTest.java      # 10 tests
│           │   └── AuthServiceTest.java          # 4 tests
│           └── repository/
│               └── EmployeeRepositoryTest.java   # 7 tests
└── pom.xml
```

---

## ⚙️ Setup & Run

### Prerequisites
- ✅ Java 17 installed and set as default
- ✅ Maven 3.x installed
- ✅ MySQL 8.x running locally
- ✅ MySQL Workbench (optional, for visual DB access)

---

### Step 1 — Create MySQL Database

Open MySQL Workbench or any MySQL client and run:

```sql
CREATE DATABASE IF NOT EXISTS employee_db;
```

---

### Step 2 — Configure Database Password

Open `src/main/resources/application.properties` and update your MySQL password:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE
```

> ⚠️ Replace `YOUR_MYSQL_PASSWORD_HERE` with your actual MySQL root password.

---

### Step 3 — Build the Project

```bash
cd employee-management
mvn clean install
```

Expected output:
```
[INFO] Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

### Step 4 — Run the Application

```bash
mvn spring-boot:run
```

When you see this, the app is ready:
```
Tomcat started on port 8080 (http)
Started EmployeeManagementApplication in X seconds
```

---

### Step 5 — Open the UI

Open your browser and go to:

```
http://localhost:8080
```

You will see the **ZestIndia Employee Portal** login page.

---

## 🎯 Quick Start Guide (Try It Now!)

### 1️⃣ Register an Account

Click **"Create one"** on the login page and fill in:

| Field | Value |
|---|---|
| Username | `admin` |
| Email | `admin@zestindia.com` |
| Password | `password123` |

Click **Create Account** → you'll be redirected to login.

---

### 2️⃣ Login

Use the credentials you just created:

| Field | Value |
|---|---|
| Username | `admin` |
| Password | `password123` |

Click **Sign In** → the dashboard loads automatically.

---

### 3️⃣ Add Your First Employee

Click **"Add Employee"** button and fill in:

| Field | Example Value |
|---|---|
| Full Name | `Priya Sharma` |
| Email | `priya.sharma@zestindia.com` |
| Department | `Engineering` |
| Position | `Senior Java Developer` |
| Salary | `850000` |
| Date of Joining | `2024-01-15` |

Click **Save Employee** → it appears in the table instantly!

---

### 4️⃣ Explore Features

| Feature | How to use |
|---|---|
| 🔍 Search | Type in the search bar — results filter live |
| ✏️ Edit | Click the pencil icon on any employee row |
| 🗑️ Delete | Click the trash icon → confirm deletion |
| 📊 Dashboard | Click "Overview" in sidebar for stats |
| 📄 Pagination | Use arrows at bottom of table |

---

## 🔌 REST API Reference

### Authentication (Public — No Token Required)

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/register` | Register new user |
| `POST` | `/api/auth/login` | Login & receive JWT token |

### Employee Management (🔒 JWT Token Required)

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/employees` | Create new employee |
| `GET` | `/api/employees` | Get all employees (paginated) |
| `GET` | `/api/employees/{id}` | Get employee by ID |
| `PUT` | `/api/employees/{id}` | Update employee |
| `DELETE` | `/api/employees/{id}` | Delete employee |
| `GET` | `/api/employees/department/{dept}` | Filter by department |
| `GET` | `/api/employees/search?keyword=X` | Search employees |

### Pagination Parameters

```
GET /api/employees?page=0&size=10&sortBy=name&sortDir=asc
```

| Parameter | Default | Options |
|---|---|---|
| `page` | `0` | Any number (0-indexed) |
| `size` | `10` | Any number |
| `sortBy` | `id` | `name`, `email`, `salary`, `department`, `dateOfJoining` |
| `sortDir` | `asc` | `asc`, `desc` |

---

## 🧪 Testing with Postman

### Step 1 — Register
```
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "johnadmin",
  "email": "john@zestindia.com",
  "password": "password123"
}
```

### Step 2 — Login & Copy Token
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "johnadmin",
  "password": "password123"
}
```

Response:
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "tokenType": "Bearer",
    "username": "johnadmin",
    "email": "john@zestindia.com",
    "role": "ROLE_USER"
  }
}
```

> 📋 **Copy the token value** — you'll need it for all employee requests.

### Step 3 — Add Authorization Header

In every employee request, add this header:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### Step 4 — Create Employee
```
POST http://localhost:8080/api/employees
Authorization: Bearer <your_token>
Content-Type: application/json

{
  "name": "Priya Sharma",
  "email": "priya.sharma@zestindia.com",
  "department": "Engineering",
  "position": "Senior Java Developer",
  "salary": 850000.00,
  "dateOfJoining": "2024-01-15"
}
```

### Step 5 — Search Employees
```
GET http://localhost:8080/api/employees/search?keyword=priya
Authorization: Bearer <your_token>
```

### Step 6 — Update Employee
```
PUT http://localhost:8080/api/employees/1
Authorization: Bearer <your_token>
Content-Type: application/json

{
  "name": "Priya Sharma",
  "email": "priya.sharma@zestindia.com",
  "department": "Engineering",
  "position": "Lead Java Developer",
  "salary": 1000000.00,
  "dateOfJoining": "2024-01-15"
}
```

### Step 7 — Delete Employee
```
DELETE http://localhost:8080/api/employees/1
Authorization: Bearer <your_token>
```

---

## 🔒 Security Architecture

```
Client Request
      │
      ▼
JwtAuthenticationFilter
  ├── Extracts Bearer token from Authorization header
  ├── Validates token signature & expiry
  └── Sets SecurityContext with authenticated user
      │
      ▼
Spring Security Filter Chain
  ├── /api/auth/**  → PUBLIC (no token needed)
  ├── /index.html   → PUBLIC (frontend)
  └── /api/employees/** → PROTECTED (JWT required)
      │
      ▼
Controller → Service → Repository → MySQL
```

---

## ✅ Test Results

```
Tests run: 21, Failures: 0, Errors: 0, Skipped: 0

├── EmployeeRepositoryTest  →  7 tests  ✅
├── AuthServiceTest         →  4 tests  ✅
└── EmployeeServiceTest     → 10 tests  ✅
```

Run tests yourself:
```bash
mvn test
```

---
---

## 👩‍💻 Author

**Vaishnavi Shinde**
Built for Zest India IT Services Pvt. Ltd. — Software Developer (Java) Assignment
📅 May 2026