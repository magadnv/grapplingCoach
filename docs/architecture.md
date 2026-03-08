# Architecture

## Overview

The Grappling Coach application follows a **layered architecture**
designed to keep the system simple, maintainable, and easy to extend.

The system separates **UI, business logic, and data access**.

    UI (Vaadin Views)
            ↓
    Service Layer (Business Logic)
            ↓
    Repository Layer (Persistence)
            ↓
    Database

Each layer has a single responsibility.

------------------------------------------------------------------------

# Layers

## 1. UI Layer

Location:

    ui/
    ui/views/

Responsibilities:

-   Render the user interface
-   Handle user interactions
-   Call service methods
-   Display results

Important rule:

    Views must never access repositories directly.
    Views only communicate with Services.

Example Views:

-   DashboardView
-   TrainingView
-   HistoryView
-   TrainingDetailView
-   StatisticsView

------------------------------------------------------------------------

## 2. Service Layer

Location:

    service/

Responsibilities:

-   Implement business logic
-   Coordinate domain objects
-   Enforce application rules
-   Use repositories for persistence

Example:

    AttendanceService

Example responsibilities:

-   Start a training session
-   Create attendance records
-   Prevent duplicate training sessions
-   Calculate attendance rates

Services represent the **behavior of the system**.

------------------------------------------------------------------------

## 3. Repository Layer

Location:

    repository/

Repositories provide database access using Spring Data JPA.

Responsibilities:

-   Store entities
-   Load entities
-   Execute queries

Example repositories:

    KidRepository
    TrainingSessionRepository
    AttendanceRepository

Repositories should **not contain business logic**.

------------------------------------------------------------------------

## 4. Domain Layer

Location:

    domain/

The domain represents the **real-world concepts** of the system.

Entities:

    Kid
    TrainingSession
    Attendance

### Kid

Represents a child participating in training.

    Kid
    - id
    - name

### TrainingSession

Represents one training event on a specific date.

    TrainingSession
    - id
    - date

Rule:

    Only one training session per day

### Attendance

Represents whether a kid attended a training session.

    Attendance
    - id
    - kid
    - session
    - present

Key design insight:

    Attendance belongs to a TrainingSession,
    not to the Kid.

This allows a kid to attend many trainings.

------------------------------------------------------------------------

# Request Flow Example

Example: Starting a training session.

    User clicks "Start Training"
            ↓
    DashboardView
            ↓
    AttendanceService.startTrainingSession()
            ↓
    TrainingSessionRepository.save()
            ↓
    AttendanceRepository.save()
            ↓
    Database

------------------------------------------------------------------------

# Design Principles

## Separation of Concerns

Each layer has a clear responsibility.

    UI → presentation
    Service → behavior
    Repository → data access
    Domain → business concepts

------------------------------------------------------------------------

## Domain First Design

The domain model reflects the real world.

    Kid attends TrainingSession
    Attendance connects them

Instead of storing presence directly on the Kid.

------------------------------------------------------------------------

## Small Focused Modules

Classes should remain small and focused.

Example:

    TrainingSession → represents a training
    AttendanceService → manages attendance behavior

------------------------------------------------------------------------

# Package Structure

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

# Future Architectural Extensions

Possible future additions:

## Authentication

    Coach accounts
    Login system
    Role management

## Analytics

    Attendance trends
    Training participation graphs
    Kid progress tracking

## Database Migration

Move from:

    H2 (development)

to:

    PostgreSQL (production)

------------------------------------------------------------------------

# Summary

The system architecture focuses on:

-   clear separation of responsibilities
-   a simple and expressive domain model
-   services that encapsulate behavior
-   a UI layer that remains thin

This structure keeps the system understandable and easy to extend.
