# Library Management System

A complete academic library management project built with Java and Maven, developed progressively across 4 parts (A–D).

## Description

This system manages books, customers, and library operations through a multi-layered architecture. Each part builds on the previous, introducing new capabilities from core data structures up to a full JavaFX graphical interface.

## Parts Overview

### Part A — Core Data Structures & Caching Algorithms
- Implements fundamental data structures used throughout the project
- Introduces `IAlgoCache` interface with two implementations: `LRUCache` (Least Recently Used) and `RandomCache`
- Foundation for all subsequent parts

### Part B — File Persistence & Algorithms
- Adds `Book` model and `IBookDao` interface for data access
- `BookDaoFileImpl` — file-based persistence, reads/writes book records to `books.txt`
- `BookService` — business logic layer for book management (add, remove, search, sort)
- Sorting and search algorithms over book collections
- JUnit tests: `TestBookService`, `MainClassTest`

### Part C — Client-Server Architecture
- Introduces a TCP socket server (`Server`, `ServerDriver`)
- `HandleRequest` — dispatches incoming requests to the appropriate controller
- `BookController` — handles book-related requests
- `Request` / `Response` model classes for structured communication
- JUnit tests covering server, controller, and request handling

### Part D — JavaFX GUI & Advanced Features
- Full graphical user interface using JavaFX
- `MainUI` — entry point and navigation
- `LibrarianUI` — librarian management view (add, update, remove books)
- `CustomerUI` — customer browsing and borrowing view
- `AddBookUI` / `UpdateBookUI` — dedicated book management dialogs
- CSS styling (`style.css`) and image assets
- `customers.txt` — persistent customer data storage

## Tech Stack

- **Java** 17+
- **Maven** — build and dependency management per part
- **JUnit** — unit tests across all parts
- **JavaFX** — graphical user interface (Part D)
- **File I/O** — text-file persistence for books and customers
- **TCP Sockets** — client-server communication (Part C)

## Build Instructions

Each part is an independent Maven project. Build them in order (A → B → C → D) since later parts depend on JARs from earlier ones (included in `libs/`).

```bash
# Part A
cd LibraryManagementSystemPartA && mvn clean install

# Part B
cd LibraryManagementSystemPartB && mvn clean install

# Part C
cd LibraryManagementSystemPartC && mvn clean install

# Part D
cd LibraryManagementSystemPartD && mvn clean install
```

To run tests for any part:
```bash
cd LibraryManagementSystemPartX && mvn test
```

## Project Documents

- `LibraryManagementSystemPresentation_Final.pdf` — final presentation slides
- `LibraryManagementSystem - detailes.txt` — project details and authors
- `סרטון הסבר.mp4` — explanation and demo video

## Academic Context

Submitted by **Hila Mendelson** and **Sahar Halili** as a collaborative academic project.
