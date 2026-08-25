# Game Service

## Overview

Game Service is a Spring Boot REST microservice responsible for managing video game-related data for the Game Collection Manager application.

It provides a REST API for managing games, genres, and platforms. The Main MVC Application communicates with this service through REST clients, keeping game-related functionality separated from user and collection management.

The service is designed as an independent microservice with its own database, business logic, REST API, exception handling, and automated tests.

---

## Features

* Create, read, update, and delete games
* Create and manage genres
* Create and manage platforms
* Game data validation
* REST API endpoints
* Custom exception handling
* Global REST error handling
* Database persistence using JPA/Hibernate
* Unit testing
* API / controller testing
* Integration testing

---

## API

The Game Service exposes REST endpoints for:

### Games

```text
GET    /api/games
GET    /api/games/{id}
POST   /api/games
PUT    /api/games/{id}
DELETE /api/games/{id}
```

### Genres

```text
GET    /api/games/genres
POST   /api/games/genres
```

### Platforms

```text
GET    /api/games/platforms
POST   /api/games/platforms
```

The exact available endpoints may depend on the configured controller mappings.

---

## Technology Stack

### Backend

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Jakarta Bean Validation

### Database & Build Tools

* MySQL
* Maven

### Testing

* JUnit 5
* Mockito
* Spring Boot Test
* MockMvc
* Unit testing
* API / Controller testing
* Integration testing

---

## Architecture

The Game Service follows a layered architecture:

### Controller Layer

Exposes REST API endpoints and handles HTTP requests and responses.

### Service Layer

Contains business logic, validation, and application rules.

### Repository Layer

Provides database access through Spring Data JPA.

### DTO Layer

Handles data transfer between the REST API and clients, separating API requests and responses from the persistence entities.

### Entity Layer

Contains the persistent database entities used by the service.

---

## Error Handling

The service uses custom exceptions and a global exception handler to provide appropriate REST responses.

Examples include:

* Game not found
* Game already exists
* Genre not found
* Platform not found
* Invalid request data
* General server errors

Appropriate HTTP status codes are returned for different types of errors, allowing the Main MVC Application and other API clients to handle errors consistently.

---

## Testing

The Game Service includes automated tests covering different layers of the application.

### Unit Tests

Unit tests verify individual components and business logic in isolation.

Service-layer functionality is tested using mocked dependencies where appropriate.

### API / Controller Tests

Controller tests use `MockMvc` to verify REST API behavior, including:

* HTTP status codes
* Request and response handling
* Request validation
* JSON serialization and deserialization
* Successful API operations
* Error responses
* Exception handling

### Integration Tests

Integration tests verify that multiple application layers work correctly together, including interaction with the persistence layer and database.

The test suite helps ensure that changes to the Game Service do not break existing functionality.

---

## Database

The Game Service uses MySQL for persistent storage.

Database configuration can be provided through the application's configuration files or environment variables.

The Game Service maintains its own game-related data independently from the Main MVC Application.

---

## Running the Service

### Prerequisites

* Java 21
* MySQL
* Maven

### Start the Application

From the Game Service project directory:

```bash
mvn spring-boot:run
```

The service runs on its configured port and can then be accessed by the Main MVC Application.

By default, the Game Service is configured to run on:

```text
http://localhost:8081
```

---

## Project Role

The Game Service is part of the **Game Collection Manager microservice architecture**.

It is responsible specifically for game-related functionality, while the Main MVC Application handles:

* Users
* Authentication and authorization
* UserGame collections
* Achievements
* Web pages and Thymeleaf views

The Main MVC Application communicates with the Game Service through its REST API.

This separation allows the two applications to operate independently while keeping game management isolated in its own service and database.
