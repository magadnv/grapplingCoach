# Grappling Coach App

A lightweight web application for grappling coaches to manage kids,
track attendance, and analyze training participation.

The system helps coaches run training sessions efficiently while
collecting useful participation insights over time.

------------------------------------------------------------------------

# Features

## Kid Management

-   Register kids participating in training

## Training Sessions

-   Start a training session for the current day
-   Automatically generate attendance entries for all kids

## Attendance Tracking

-   Mark kids as present or absent during training

## Training History

-   Browse past training sessions
-   Inspect attendance for each session

## Dashboard

-   Overview of:
    -   Registered kids
    -   Attendance statistics
    -   Kids present today
    -   Training session control

------------------------------------------------------------------------

# Tech Stack

-   **Java 21**
-   **Spring Boot**
-   **Vaadin**
-   **Spring Data JPA**
-   **H2 Database**

This stack allows full‑stack development using only Java.

------------------------------------------------------------------------

# Project Architecture

The system follows a layered architecture:

    UI (Vaadin Views)
            ↓
    Service Layer (Business Logic)
            ↓
    Repository Layer (Database Access)
            ↓
    Database (H2 / future PostgreSQL)

### Design Principles

-   Views **do not access repositories**
-   Services contain **business logic**
-   Entities represent **real-world concepts**
-   Repositories only **load/store data**

------------------------------------------------------------------------

# Domain Model

The domain reflects how real training works.

## Kid

Represents a child attending grappling training.

    Kid
    - id
    - name

## TrainingSession

Represents one training event on a specific date.

    TrainingSession
    - id
    - date

## Attendance

Connects a kid to a training session.

    Attendance
    - id
    - kid
    - session
    - present

Key idea:

    Attendance belongs to a training session
    —not to the kid.

A kid can attend multiple trainings.

------------------------------------------------------------------------

# Project Structure

    src/main/java/com/grappling/grapplingcoach

    domain/
      Kid
      TrainingSession
      Attendance

    repository/
      KidRepository
      TrainingSessionRepository
      AttendanceRepository

    service/
      AttendanceService

    ui/
      MainLayout

    ui/views/
      DashboardView
      TrainingView
      HistoryView
      TrainingDetailView
      StatisticsView

------------------------------------------------------------------------

# Running the Project

## Start the application

    ./mvnw spring-boot:run

Open the application:

    http://localhost:8080

------------------------------------------------------------------------

# Database Console

H2 database console:

    http://localhost:8080/h2-console

Typical settings:

    JDBC URL: jdbc:h2:mem:testdb
    User: sa
    Password: (empty)

------------------------------------------------------------------------

# Development Workflow

When implementing new features follow this order:

    1. Define domain model
    2. Implement service logic
    3. Add repository queries
    4. Build UI

This prevents UI logic from leaking into business logic.

------------------------------------------------------------------------

# Important Architectural Decisions

## Attendance is a separate entity

A kid can attend many trainings.

Therefore:

    Kid ← Attendance → TrainingSession

Instead of storing presence on the kid itself.

------------------------------------------------------------------------

## Only one training session per day

The system prevents multiple sessions for the same date to avoid
inconsistent attendance data.

------------------------------------------------------------------------

# Future Improvements

Planned next improvements:

-   Kid profile page
-   Attendance trends per kid
-   Training analytics
-   Mobile‑optimized check‑in UI
-   PostgreSQL persistence
-   Authentication for coaches

------------------------------------------------------------------------

# Purpose

The project is intentionally simple but demonstrates:

-   Domain modeling
-   Layered architecture
-   Clean separation of concerns
-   Practical full‑stack Java development
