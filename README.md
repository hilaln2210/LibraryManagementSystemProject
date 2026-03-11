# Library Management System

A complete academic library management project built with Java and Maven, developed progressively across 4 parts (A through D). Each part builds on the previous, introducing new capabilities from core data structures up to a full JavaFX graphical interface.

## Project Overview

The system manages books, customers, and library operations through a multi-layered architecture. Key concerns — caching, file persistence, client-server communication, and GUI — are introduced incrementally across the four parts.

**Authors:** Hila Mendelson & Sahar Halili (academic project)

## Parts Overview

### Part A — Core Data Structures & Caching Algorithms

Located in `LibraryManagementSystemPartA/`

- Defines the `IAlgoCache<K, V>` interface with `get`, `put`, `remove`, and `size` operations
- `LRUCache<K, V>` — Least Recently Used eviction implemented with `LinkedHashMap` (access-order mode)
- `RandomCache<K, V>` — cache with random eviction policy
- JUnit tests: `AlgoCacheTest`, `AppTest`
- This part is the algorithmic foundation used by all later parts

### Part B — File Persistence & Book Management

Located in `LibraryManagementSystemPartB/`

- `Book` model — id, title, author, year
- `IBookDao` interface — standard DAO contract (add, get, getAll, update, delete)
- `BookDaoFileImpl` — file-based persistence; reads and writes book records to `books.txt`
- `BookService` — business logic layer wrapping the DAO
- `MainClass` — interactive entry point for book operations
- JUnit tests: `TestBookService`, `MainClassTest`, `AppTest`
- Depends on Part A JAR (included in `libs/`)

### Part C — Client-Server Architecture

Located in `LibraryManagementSystemPartC/`

- `Server` — TCP socket server accepting client connections on a configured port; spawns a handler thread per connection
- `ServerDriver` — bootstraps and starts the server
- `HandleRequest` — dispatches incoming socket requests to the appropriate controller
- `BookController` — handles book-related API requests
- `Request` / `Response` — structured message classes for client-server communication
- JUnit tests: `ServerTest`, `ServerDriverTest`, `HandleRequestTest`, `BookControllerTest`, `AppTest`
- Depends on Parts A and B JARs (included in `libs/`)

### Part D — JavaFX GUI & Full Integration

Located in `LibraryManagementSystemPartD/`

- Full graphical user interface built with JavaFX
- `MainUI` — application entry point and role-selection screen (Customer / Librarian)
- `LibrarianUI` — librarian view: browse, add, update, and remove books
- `CustomerUI` — customer view: browse available books
- `AddBookUI` / `UpdateBookUI` — modal dialogs for book management
- CSS styling (`src/main/resources/style.css`) and image assets
- `books.txt` and `customers.txt` for persistent data storage
- Depends on Parts A, B, and C JARs (included in `libs/`)

## Tech Stack

| Component | Technology |
|---|---|
| Language | Java 17+ |
| Build | Maven (one `pom.xml` per part) |
| Unit testing | JUnit |
| GUI (Part D) | JavaFX |
| Persistence | Text file I/O (`books.txt`, `customers.txt`) |
| Networking (Part C) | Java TCP Sockets (`ServerSocket`, `Socket`) |

## Build Instructions

Each part is an independent Maven project. Build in order (A → B → C → D) because later parts depend on JARs from earlier ones (pre-built JARs are already included in each part's `libs/` directory).

```bash
# Part A — cache algorithms
cd LibraryManagementSystemPartA && mvn clean install

# Part B — book CRUD + file persistence
cd ../LibraryManagementSystemPartB && mvn clean install

# Part C — TCP server
cd ../LibraryManagementSystemPartC && mvn clean install

# Part D — JavaFX GUI
cd ../LibraryManagementSystemPartD && mvn clean install
```

### Run tests for any part

```bash
cd LibraryManagementSystemPartX && mvn test
```

### Run Part D (JavaFX application)

```bash
cd LibraryManagementSystemPartD && mvn javafx:run
```

> Requires JavaFX to be on the module path. If using JDK 17+ without bundled JavaFX, add the JavaFX Maven plugin to `pom.xml` or provide `--module-path` flags manually.

## Project Files

| File | Description |
|---|---|
| `LibraryManagementSystemPresentation_Final.pdf` | Final presentation slides |
| `LibraryManagementSystem - detailes.txt` | Project details and authors |
| `סרטון הסבר.mp4` | Demo and explanation video |

## Academic Context

Submitted by **Hila Mendelson** and **Sahar Halili** as a collaborative academic project demonstrating progressive software engineering: data structures, DAO patterns, TCP networking, and GUI development.

---

## 🇮🇱 תיעוד בעברית

### מה הפרויקט עושה

מערכת ניהול ספרייה אקדמית מלאה, שפותחה בשלבים מ-4 חלקים עוקבים (A עד D). כל חלק מוסיף שכבת פונקציונליות על גבי הקודם — ממימוש אלגוריתמי ומבני נתונים ועד לממשק גרפי מלא.

המערכת מנהלת ספרים, לקוחות ופעולות ספרייה בארכיטקטורה רב-שכבתית.

**מפתחות:** הילה מנדלסון ושחר חלילי (פרויקט אקדמי)

### טכנולוגיות

| רכיב | טכנולוגיה |
|------|-----------|
| שפה | Java 17+ |
| בנייה | Maven (pom.xml נפרד לכל חלק) |
| בדיקות | JUnit |
| ממשק גרפי (חלק D) | JavaFX |
| אחסון נתונים | קבצי טקסט (`books.txt`, `customers.txt`) |
| תקשורת רשת (חלק C) | Java TCP Sockets |

### הוראות התקנה והפעלה

יש לבנות את החלקים לפי הסדר (A ← B ← C ← D), מכיוון שחלקים מאוחרים תלויים ב-JAR-ים מחלקים קודמים (הקבצים כלולים מראש בתיקיות `libs/`):

```bash
# חלק A — אלגוריתמי Cache
cd LibraryManagementSystemPartA && mvn clean install

# חלק B — ניהול ספרים + שמירה לקובץ
cd ../LibraryManagementSystemPartB && mvn clean install

# חלק C — שרת TCP
cd ../LibraryManagementSystemPartC && mvn clean install

# חלק D — ממשק JavaFX
cd ../LibraryManagementSystemPartD && mvn clean install
```

**הרצת ממשק הגרפי (חלק D):**

```bash
cd LibraryManagementSystemPartD && mvn javafx:run
```

**הרצת בדיקות:**

```bash
cd LibraryManagementSystemPartX && mvn test
```

### מבנה הפרויקט

```
LibraryManagementSystemProject/
├── LibraryManagementSystemPartA/   # אלגוריתמי Cache: LRU ו-Random Eviction
├── LibraryManagementSystemPartB/   # ניהול ספרים + DAO + שמירה לקובץ
├── LibraryManagementSystemPartC/   # שרת TCP + תקשורת Client-Server
└── LibraryManagementSystemPartD/   # ממשק JavaFX מלא (ספרן + לקוח)
```

**סיכום השלבים:**
- **חלק A** — הגדרת ממשק `IAlgoCache`, מימוש `LRUCache` ו-`RandomCache`
- **חלק B** — מודל `Book`, `BookDaoFileImpl` (שמירה לקובץ), `BookService` (לוגיקה עסקית)
- **חלק C** — שרת TCP עם thread לכל לקוח, ניתוב בקשות, `Request`/`Response`
- **חלק D** — ממשק JavaFX עם תפקידים נפרדים לספרן ולקוח, ניהול ספרים גרפי
