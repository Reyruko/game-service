# Game Service

## Overview

Game Service is a Spring Boot REST microservice responsible for managing video game-related data for the Game Collection Manager application.

It provides a REST API for managing games, genres, and platforms. The Main MVC Application communicates with this service through REST clients, keeping game-related functionality separated from user and collection management.

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

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Jakarta Bean Validation
* JUnit 5

---

## Architecture

The Game Service follows a layered architecture:

### Controller Layer

Exposes REST API endpoints and handles HTTP requests.

### Service Layer

Contains business logic and validation.

### Repository Layer

Provides database access through Spring Data JPA.

### DTO Layer

Handles data transfer between the REST API and clients.

### Entity Layer

Contains the persistent database entities.

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

Appropriate HTTP status codes are returned for different types of errors.

---

## Database

The Game Service uses MySQL for persistent storage.

Database configuration can be provided through the application's configuration files or environment variables.

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
* Authentication
* UserGame collections
* Web pages and Thymeleaf views

This separation allows the two applications to operate independently while communicating through a REST API.
