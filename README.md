# Book Library Project

## Overview
This project is dedicated to learning and experimenting with Spring Boot, Java Persistence API (JPA), MySQL integration, annotations, testing practices, and other important concepts related to full-stack development in Java. The project aims to build a simple book library management system where users can add, delete, and search books.

This project is an excellent resource for anyone looking to understand how to use Spring Boot in combination with MySQL for building backend applications.

## Features
- **Book Management**: Add, delete, and view books in the system.
- **Author Search**: Search books by author name.
- **Database Integration**: Utilizes MySQL database for persistent storage.
- **Testing**: Unit tests for controller and repository layers using Mockito and MockMvc for web-layer testing.

## Learning Goals
This project is designed to help me understand the following key concepts:
- **Spring Boot**: Building RESTful APIs and microservices with Spring Boot.
- **Annotations**: Using various Spring annotations like `@RestController`, `@Autowired`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, etc., to simplify the development process.
- **MySQL Integration**: Connecting Spring Boot with MySQL and performing CRUD operations using JPA.
- **Testing**: Writing unit tests with JUnit, Mockito, and MockMvc for API endpoints.
- **Repository Layer**: Using Spring Data JPA for interacting with the database and handling entities like `Book`.

## Project Setup

### Prerequisites
- Java 17 or higher
- Maven for dependency management
- MySQL 5.7 or higher
- IDE: IntelliJ IDEA, Eclipse, or any other Java IDE of your choice

### Installation
1. Clone this repository:
    ```bash
    git clone https://github.com/yourusername/book-library.git
    ```

2. Create a MySQL database named `book_db`:
    ```sql
    CREATE DATABASE book_db;
    ```

3. Update `application.properties` or `application-test.properties` with your database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/book_db?useSSL=false&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=Password1
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # JPA / Hibernate
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    ```

4. Run the project using Maven:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints
The API exposes the following endpoints:

1. **POST /api/books**: Add a new book.
    - Request Body: JSON object with `title` and `author` fields.
    - Response: `Book added successfully` or `Book already exists`.

2. **GET /api/books**: Get all books.
    - Response: List of all books in the database.

3. **GET /api/books/author**: Get books by a specific author.
    - Query Parameter: `author`
    - Response: List of books by the specified author.

4. **DELETE /api/books/{id}**: Delete a book by its ID.
    - Response: `Book deleted successfully` or `Book not found`.

## Testing
Unit and integration tests are provided for the controller and repository layers.

### Test Dependencies
- **JUnit 5**: For running unit tests.
- **Mockito**: For mocking services and repositories in unit tests.
- **MockMvc**: For testing web layer controllers without starting a full HTTP server.

### Run Tests
To run the tests, use Maven:
```bash
mvn test