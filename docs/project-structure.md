# Project Structure

This document explains how the Grappling Coach project is organized and
where to find each part of the system.

The goal of the structure is:

-   easy navigation
-   clear responsibilities
-   predictable locations for code

A developer should always know **where new code belongs**.

------------------------------------------------------------------------

# High-Level Structure

    grapplingcoach/
    │
    ├── README.md
    ├── docs/
    │   ├── architecture.md
    │   ├── domain.md
    │   └── project-structure.md
    │
    ├── src/
    │   └── main/
    │       └── java/com/grappling/grapplingcoach
    │
    ├── pom.xml

------------------------------------------------------------------------

# Source Code Structure

All application code lives in:

    src/main/java/com/grappling/grapplingcoach

The project is divided into **four main layers**.

    domain/
    repository/
    service/
    ui/

------------------------------------------------------------------------

# domain/

The **domain layer** represents the real-world model of the application.

    domain/
        Kid.java
        TrainingSession.java
        Attendance.java

These classes describe the core concepts of the system.

Responsibilities:

-   represent business entities
-   contain domain relationships
-   reflect the real-world training model

Example:

    Kid ← Attendance → TrainingSession

------------------------------------------------------------------------

# repository/

Repositories provide access to the database using Spring Data JPA.

    repository/
        KidRepository.java
        TrainingSessionRepository.java
        AttendanceRepository.java

Responsibilities:

-   load entities from the database
-   save entities
-   define query methods

Important rule:

    Repositories should NOT contain business logic.

------------------------------------------------------------------------

# service/

Services contain the **business logic of the system**.

    service/
        AttendanceService.java

Responsibilities:

-   start training sessions
-   create attendance records
-   enforce system rules
-   compute attendance statistics

Services coordinate the domain model and repositories.

------------------------------------------------------------------------

# ui/

The UI layer contains all Vaadin components.

    ui/
        MainLayout.java

    ui/views/
        DashboardView.java
        TrainingView.java
        HistoryView.java
        TrainingDetailView.java
        StatisticsView.java

Responsibilities:

-   render the user interface
-   react to user input
-   call services
-   display data

Important rule:

    Views should never access repositories directly.

------------------------------------------------------------------------

# Configuration Files

## pom.xml

The Maven configuration file.

Contains:

-   project dependencies
-   build configuration
-   plugins

------------------------------------------------------------------------

# Typical Feature Development Flow

When adding a new feature follow this order:

    1. Extend domain model
    2. Add repository query if needed
    3. Implement service logic
    4. Create or update UI view

Example:

    Add training notes

    Domain → TrainingNote
    Repository → TrainingNoteRepository
    Service → TrainingNoteService
    UI → TrainingDetailView

------------------------------------------------------------------------

# Naming Conventions

Classes should follow consistent naming patterns.

    Entity           → Kid
    Repository       → KidRepository
    Service          → KidService
    View             → KidView

This keeps the project easy to scan.

------------------------------------------------------------------------

# Summary

The structure keeps the codebase predictable.

    domain      → business concepts
    repository  → persistence
    service     → system behavior
    ui          → presentation

By keeping responsibilities separated the system remains:

-   easier to understand
-   easier to extend
-   easier to maintain
