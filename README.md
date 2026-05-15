# Employee Management System - Spring Boot + JWT

> Assignment solution for **Zest India IT Services Pvt. Ltd.** – Software Developer (Java) Role

---

## Tech Stack
| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Spring Data JPA / Hibernate | Included |
| Spring Security | Included |
| MySQL | 8.x |
| JWT (JJWT) | 0.11.5 |
| Maven | 3.x |
| Lombok | Latest |

---

## Project Structure

```
employee-management/
├── src/
│   ├── main/
│   │   ├── java/com/zestindia/employeemanagement/
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java          # Spring Security + JWT config
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java          # Register & Login endpoints
│   │   │   │   └── EmployeeController.java      # CRUD + Pagination endpoints
│   │   │   ├── dto/
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── AuthResponse.java
│   │   │   │   ├── EmployeeRequest.java
│   │   │   │   ├── EmployeeResponse.java
│   │   │   │   └── ApiResponse.java             # Generic wrapper
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
│   │   │   │   ├── JwtService.java              # Token generation & validation
│   │   │   │   ├── JwtAuthenticationFilter.java # Per-request JWT filter
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   └── service/
│   │   │       ├── AuthService.java
│   │   │       ├── EmployeeService.java
│   │   │       └── impl/
│   │   │           ├── AuthServiceImpl.java
│   │   │           └── EmployeeServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/zestindia/employeemanagement/
│           ├── service/
│           │   ├── EmployeeServiceTest.java
│           │   └── AuthServiceTest.java
│           └── repository/
│               └── EmployeeRepositoryTest.java
└── pom.xml
```

---

## Prerequisites
- Java 17+
- Maven 3.x
- MySQL 8.x running locally

---

## Setup & Run

### 1. Create MySQL Database
```sql
CREATE DATABASE employee_db;
```

### 2. Configure `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 3. Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

The app starts at: `http://localhost:8080`

### 4. Run Tests
```bash
mvn test
```

---

## API Endpoints

### Authentication (Public – No Token Required)

| Method | URL | Description |
|---|---|---|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login & get JWT token |

### Employee Management (Protected – JWT Required)

| Method | URL | Description |
|---|---|---|
| POST | `/api/employees` | Create new employee |
| GET | `/api/employees` | Get all employees (paginated) |
| GET | `/api/employees/{id}` | Get employee by ID |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |
| GET | `/api/employees/department/{dept}` | Filter by department |
| GET | `/api/employees/search?keyword=X` | Search employees |

### Pagination & Sorting Parameters
```
GET /api/employees?page=0&size=10&sortBy=name&sortDir=asc
```
| Param | Default | Description |
|---|---|---|
| page | 0 | Page number (0-indexed) |
| size | 10 | Records per page |
| sortBy | id | Field to sort by |
| sortDir | asc | Sort direction: `asc` or `desc` |

---

## Postman Testing Guide

### Step 1: Register a User
```
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "johnadmin",
  "email": "john@zestindia.com",
  "password": "password123"
}
```

### Step 2: Login
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "johnadmin",
  "password": "password123"
}
```
**Copy the `token` from the response.**

### Step 3: Use Token in All Employee Requests
```
Authorization: Bearer <paste_token_here>
```

### Step 4: Create Employee
```
POST http://localhost:8080/api/employees
Authorization: Bearer <token>
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

### Step 5: Get All Employees (Paginated + Sorted)
```
GET http://localhost:8080/api/employees?page=0&size=5&sortBy=salary&sortDir=desc
Authorization: Bearer <token>
```

### Step 6: Search Employees
```
GET http://localhost:8080/api/employees/search?keyword=priya
Authorization: Bearer <token>
```

### Step 7: Update Employee
```
PUT http://localhost:8080/api/employees/1
Authorization: Bearer <token>
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

### Step 8: Delete Employee
```
DELETE http://localhost:8080/api/employees/1
Authorization: Bearer <token>
```

---

## Sample API Responses

### Successful Login Response
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
  },
  "timestamp": "2024-05-15T10:30:00"
}
```

### Paginated Employees Response
```json
{
  "success": true,
  "message": "Employees retrieved successfully",
  "data": {
    "content": [
      {
        "id": 1,
        "name": "Priya Sharma",
        "email": "priya.sharma@zestindia.com",
        "department": "Engineering",
        "position": "Senior Java Developer",
        "salary": 850000.00,
        "dateOfJoining": "2024-01-15",
        "createdAt": "2024-05-15T10:30:00",
        "updatedAt": "2024-05-15T10:30:00"
      }
    ],
    "totalElements": 1,
    "totalPages": 1,
    "size": 5,
    "number": 0
  },
  "timestamp": "2024-05-15T10:31:00"
}
```

---

## Security Flow
```
Client → HTTP Request
         ↓
JwtAuthenticationFilter (extracts & validates Bearer token)
         ↓
SecurityContextHolder (sets authenticated user)
         ↓
Controller → Service → Repository → MySQL
```

---

## Author
Built for Zest India IT Services Pvt. Ltd. Assignment – May 2026
