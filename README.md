
# Employee Management REST API

A RESTful backend application built using Java, Spring Boot, Spring Data JPA, and MySQL to manage employee records through CRUD operations. The project includes input validation, centralized exception handling, and automated tests using JUnit, Mockito, and MockMvc.

## Features

- Create employee records
- Retrieve all employees
- Retrieve an employee by ID
- Update employee details
- Delete employee records
- Validate employee input using Jakarta Bean Validation
- Handle exceptions centrally with `@RestControllerAdvice`
- Test service logic using JUnit and Mockito
- Test REST endpoints using MockMvc

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **REST API:** Spring WebMVC
- **Persistence:** Spring Data JPA, Hibernate
- **Database:** MySQL
- **Validation:** Jakarta Bean Validation
- **Testing:** JUnit 5, Mockito, MockMvc
- **Build Tool:** Maven
- **API Testing:** Postman

## Architecture

The application follows a layered architecture:

```text
Postman / HTTP Client
        |
        v
EmployeeController
        |
        v
EmployeeService
        |
        v
EmployeeRepository
        |
        v
MySQL Database
```

- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains employee management business logic.
- **Repository:** Uses Spring Data JPA to interact with the database.
- **Entity:** Represents employee data stored in MySQL.
- **Global Exception Handler:** Returns structured error responses.

## Employee Data Model

Each employee contains:

| Field | Description |
|---|---|
| `id` | Auto-generated primary key |
| `name` | Employee name; required, maximum 100 characters |
| `email` | Employee email; required and validated |
| `department` | Employee department; required |

## REST API Endpoints

Base URL: `http://localhost:8081`

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| POST | `/api/employees` | Create an employee | 201 Created |
| GET | `/api/employees` | Retrieve all employees | 200 OK |
| GET | `/api/employees/{id}` | Retrieve an employee by ID | 200 OK |
| PUT | `/api/employees/{id}` | Update an employee | 200 OK |
| DELETE | `/api/employees/{id}` | Delete an employee | 204 No Content |

## Example Request

### Create an Employee

**POST** `/api/employees`

```json
{
  "name": "Yaswanth",
  "email": "yaswanth@example.com",
  "department": "Engineering"
}
```

Example successful response:

```json
{
  "id": 1,
  "name": "Yaswanth",
  "email": "yaswanth@example.com",
  "department": "Engineering"
}
```

The generated ID may differ.

## Validation and Error Handling

The API validates incoming employee data.

Example validation errors:

```json
{
  "name": "Name is required",
  "email": "Please provide a valid email",
  "department": "Department is required"
}
```

Common HTTP status codes:

- `400 Bad Request` — Invalid employee input
- `404 Not Found` — Employee does not exist
- `201 Created` — Employee created successfully
- `204 No Content` — Employee deleted successfully

## Prerequisites

Install the following:

- Java 21 or a compatible JDK
- MySQL 8.0 or compatible
- Git
- Postman (optional, for manual API testing)

The project uses the Maven Wrapper, so a separate Maven installation is not required.

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/Yaswanth000gsyr/employee-management-api.git
cd employee-management-api
```

If the project is nested in another directory, navigate to the folder containing `pom.xml` and `mvnw.cmd`.

### 2. Create the database

Open MySQL and run:

```sql
CREATE DATABASE employee_management;
```

### 3. Configure database credentials

Configure your local `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8081
```

Set `DB_USERNAME` and `DB_PASSWORD` as environment variables before starting the application. Never commit real database credentials to GitHub.

### 4. Run the application

On Windows PowerShell:

```powershell
$env:DB_USERNAME="your_mysql_username"
$env:DB_PASSWORD="your_mysql_password"
.\mvnw.cmd spring-boot:run
```

Replace the example values with your own local MySQL credentials.

### 5. Run automated tests

```powershell
.\mvnw.cmd test
```

The project includes tests for employee data, service logic, missing employees, and REST controller behavior.

### 6. Test with Postman

Send requests to:

```text
http://localhost:8081/api/employees
```

Use the endpoints listed above to test create, read, update, and delete operations.

## Future Improvements

- Pagination and sorting
- Search employees by department or email
- Improved API documentation with OpenAPI/Swagger
- Integration tests using a dedicated test database
- Docker-based application and database setup
- Authentication and role-based authorization

## Author

**Yaswanth Reddy**

- GitHub: [Yaswanth000gsyr](https://github.com/Yaswanth000gsyr)
- LinkedIn: [Connect on LinkedIn](https://www.linkedin.com/in/-gadiyam/)
