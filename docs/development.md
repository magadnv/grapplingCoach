# Development Guide

This document explains how to develop new features in the Grappling
Coach project.

The goal is to keep development **predictable, simple, and consistent**.

A developer should always know:

-   where code belongs
-   how new features are implemented
-   which architectural rules must be followed

------------------------------------------------------------------------

# Core Development Principle

Always implement features in the following order:

    1. Domain
    2. Repository
    3. Service
    4. UI

This ensures business logic stays out of the UI.

------------------------------------------------------------------------

# Step 1 --- Domain

First ask:

    Does the domain model need to change?

If the feature introduces a new concept, create a domain entity.

Example:

Feature: training notes

    TrainingNote
    - id
    - session
    - text

Location:

    domain/

Domain classes should represent **real-world concepts**.

------------------------------------------------------------------------

# Step 2 --- Repository

If the feature requires database access, add a repository.

Example:

    TrainingNoteRepository

Location:

    repository/

Repositories should only contain:

-   persistence logic
-   query definitions

Example:

    List<TrainingNote> findBySession(TrainingSession session);

Repositories must **not contain business logic**.

------------------------------------------------------------------------

# Step 3 --- Service

Services implement the **behavior of the system**.

Location:

    service/

Example responsibilities:

    start training session
    create attendance records
    calculate attendance statistics

Example:

    TrainingNoteService

Services orchestrate:

    domain objects
    repositories
    application rules

------------------------------------------------------------------------

# Step 4 --- UI

The UI layer presents the feature to the user.

Location:

    ui/views/

Example:

    TrainingDetailView

Responsibilities:

    render UI
    react to user actions
    call service methods
    display results

Important rule:

    Views must never access repositories directly.

------------------------------------------------------------------------

# Example Feature Implementation

Feature:

    Add training notes

Implementation flow:

    Domain
    TrainingNote entity
            ↓
    Repository
    TrainingNoteRepository
            ↓
    Service
    TrainingNoteService
            ↓
    UI
    TrainingDetailView

This keeps responsibilities separated.

------------------------------------------------------------------------

# Local Development

Start the application:

    ./mvnw spring-boot:run

Open the app:

    http://localhost:8080

------------------------------------------------------------------------

# Database

The project uses an embedded H2 database.

Access the console:

    http://localhost:8080/h2-console

Typical settings:

    JDBC URL: jdbc:h2:mem:testdb
    User: sa
    Password: (empty)

------------------------------------------------------------------------

# Code Style Guidelines

Keep classes:

    small
    focused
    readable

Prefer:

    clear names
    short methods
    simple logic

Avoid:

    business logic in UI
    large service classes
    complex conditionals

------------------------------------------------------------------------

# Adding New Views

Views should be placed in:

    ui/views/

Example:

    KidDetailView
    TrainingAnalyticsView

All views should use the main layout:

    @Route(value = "path", layout = MainLayout.class)

------------------------------------------------------------------------

# Typical Pitfalls

### UI calling repositories

Incorrect:

    View → Repository

Correct:

    View → Service → Repository

------------------------------------------------------------------------

### Domain modeling mistakes

Example mistake:

    Kid
    - present

Correct model:

    Kid ← Attendance → TrainingSession

Presence belongs to a training session.

------------------------------------------------------------------------

# Pull Request Checklist

Before submitting changes:

    Feature follows architecture
    Domain model is correct
    Services contain logic
    UI remains thin
    No duplicate business rules

------------------------------------------------------------------------

# Summary

Feature development always follows:

    Domain
    Repository
    Service
    UI

This workflow keeps the codebase:

-   understandable
-   maintainable
-   extensible
