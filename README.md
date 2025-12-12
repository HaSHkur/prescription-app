# Prescription App

This is a Spring Boot application for managing medical prescriptions. It provides a RESTful API for creating, retrieving, updating, and deleting prescriptions, as well as user authentication and authorization.

## Features

*   **User Authentication**: Secure registration and login using JWT (JSON Web Tokens).
*   **Prescription Management**: Full CRUD (Create, Read, Update, Delete) functionality for prescriptions.
*   **Advanced Search**: Filter prescriptions by patient name, age, and a date range.
*   **Pagination**: Paginated results for prescription lists to handle large datasets efficiently.
*   **Prescription Counting**: Endpoints to count prescriptions by a specific date or get a list of counts grouped by date.
*   **API Documentation**: Integrated Swagger UI for easy API exploration and testing.
*   **CQRS Pattern**: Follows the Command Query Responsibility Segregation (CQRS) pattern for a clean and scalable architecture.

## Technologies Used

*   **Framework**: Spring Boot 3.5.7
*   **Language**: Java 21
*   **Database**: MySQL
*   **Data Access**: Spring Data JPA
*   **Security**: Spring Security with JWT
*   **API Documentation**: SpringDoc OpenAPI
*   **Build Tool**: Gradle
*   **Dependencies**:
    *   Lombok
    *   Thymeleaf
    *   JJWT (for JSON Web Tokens)

## Getting Started

### Prerequisites

*   Java 21
*   Gradle
*   MySQL

### Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/HaSHkur/prescription-app
    ```
2.  **Configure the database:**
    *   Open `src/main/resources/application.properties`.
    *   Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your MySQL configuration.
3.  **Build the project:**
    ```sh
    ./gradlew build
    ```
4.  **Run the application:**
    ```sh
    ./gradlew bootRun
    ```
The application will start on port 8080 (or as configured in `application.properties`).

## API Endpoints

The base URL for the API is `/api`.

### Authentication

*   **`POST /api/auth/register`**: Register a new user.
    *   **Request Body**:
        ```json
        {
          "username": "your_username",
          "password": "your_password",
          "role": "USER"
        }
        ```
*   **`POST /api/auth/login`**: Log in to get a JWT token.
    *   **Request Body**:
        ```json
        {
          "username": "your_username",
          "password": "your_password"
        }
        ```

### Prescriptions

*   **`POST /api/prescriptions`**: Create a new prescription.
*   **`GET /api/prescriptions`**: Get a paginated list of prescriptions with optional filtering.
    *   **Query Parameters**:
        *   `patientName` (String)
        *   `patientAge` (Integer)
        *   `fromDate` (Date, `yyyy-MM-dd`)
        *   `toDate` (Date, `yyyy-MM-dd`)
        *   `page` (int, default: 0)
        *   `size` (int, default: 10)
*   **`GET /api/prescriptions/{id}`**: Get a single prescription by its ID.
*   **`PUT /api/prescriptions`**: Update an existing prescription.
*   **`DELETE /api/prescriptions/{id}`**: Delete a prescription by its ID.
*   **`GET /api/prescriptions/count`**: Get prescription counts.
    *   **Query Parameters**:
        *   `date` (Date, `yyyy-MM-dd`, optional): If provided, returns the count for that specific date. If omitted, returns a list of all dates with their respective prescription counts.

### API Documentation

Once the application is running, you can access the Swagger UI for interactive API documentation at:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
