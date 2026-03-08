# Onboarding Guide

Welcome to the **Grappling Coach** project.

This document helps new developers understand the project quickly and
become productive in about **15--30 minutes**.

The goal is simple:

Understand the architecture → run the project → implement your first
feature.

------------------------------------------------------------------------

# 1. What This Project Is

The Grappling Coach application is a web app that helps coaches:

-   manage kids
-   start training sessions
-   record attendance
-   review training history
-   analyze participation

The system is intentionally small but demonstrates **clean software
architecture**.

------------------------------------------------------------------------

# 2. Technology Stack

The application is built entirely in Java.

    Java 21
    Spring Boot
    Vaadin
    Spring Data JPA
    H2 Database
    Maven

Why this stack:

-   simple full‑stack Java development
-   minimal infrastructure
-   fast iteration

------------------------------------------------------------------------

# 3. Running the Project

Clone the repository and start the application.

    ./mvnw spring-boot:run

Open the application:

    http://localhost:8080

------------------------------------------------------------------------

# Database Console

The project uses an embedded H2 database.

Open:

    http://localhost:8080/h2-console

Typical connection settings:

    JDBC URL: jdbc:h2:mem:testdb
    User: sa
    Password: (empty)

------------------------------------------------------------------------

# 4. Understanding the System

The architecture is intentionally simple.

    UI (Vaadin Views)
            ↓
    Service Layer
            ↓
    Repository Layer
            ↓
    Database

Responsibilities:

    UI → user interaction
    Service → business logic
    Repository → persistence
    Domain → real-world concepts

------------------------------------------------------------------------

# 5. Domain Model

The core domain consists of three entities.

    Kid
    TrainingSession
    Attendance

Relationship:

    Kid ← Attendance → TrainingSession

Meaning:

-   kids attend training sessions
-   attendance records participation

This model enables:

-   training history
-   attendance statistics
-   participation analytics

------------------------------------------------------------------------

# 6. Important Project Rules

Follow these rules when developing new features.

### Rule 1

Views must **not call repositories directly**.

Correct:

    View → Service → Repository

------------------------------------------------------------------------

### Rule 2

Business logic belongs in services.

Example:

    AttendanceService.startTrainingSession()

------------------------------------------------------------------------

### Rule 3

Domain should reflect reality.

Example mistake:

    Kid
    - present

Correct model:

    Kid ← Attendance → TrainingSession

Presence belongs to the training session.

------------------------------------------------------------------------

# 7. Project Structure

    src/main/java/com/grappling/grapplingcoach

    domain/
    repository/
    service/
    ui/
    ui/views/

Explanation:

    domain → business concepts
    repository → database access
    service → application behavior
    ui → presentation layer

------------------------------------------------------------------------

# 8. Implementing Your First Feature

When adding a feature follow this order.

    1. Domain
    2. Repository
    3. Service
    4. UI

Example:

Feature: Training notes

    Domain
    TrainingNote
            ↓
    Repository
    TrainingNoteRepository
            ↓
    Service
    TrainingNoteService
            ↓
    UI
    TrainingDetailView

------------------------------------------------------------------------

# 9. Debugging Tips

Common checks when something does not work:

    Check service logic
    Check repository query
    Check database data in H2 console
    Check browser console

You can also log actions using:

    System.out.println()

during development.

------------------------------------------------------------------------

# 10. Useful First Tasks

Good starter tasks for new developers:

    Add kid profile page
    Add training notes
    Add attendance chart
    Improve dashboard metrics

These tasks help understand the system quickly.

------------------------------------------------------------------------

# 11. Where to Learn More

Additional project documentation:

    docs/architecture.md
    docs/domain.md
    docs/project-structure.md
    docs/development.md
    docs/decisions.md

These documents explain:

-   architectural reasoning
-   domain design
-   development workflow

------------------------------------------------------------------------

# Summary

To start working on the project:

    1. Run the application
    2. Explore the UI
    3. Read the architecture and domain docs
    4. Follow the Domain → Repository → Service → UI workflow

If you follow these principles, the codebase will remain:

-   understandable
-   maintainable
-   extensible
