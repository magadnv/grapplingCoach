# Contributing

Thank you for contributing to the Grappling Coach project.

This document describes how to contribute code while keeping the project
consistent and maintainable.

The goal is simple:

-   keep the architecture clean
-   keep the domain model correct
-   keep the codebase easy to understand

------------------------------------------------------------------------

# Development Setup

1.  Clone the repository

```{=html}
<!-- -->
```
    git clone <repository-url>
    cd grapplingcoach

2.  Start the application

```{=html}
<!-- -->
```
    ./mvnw spring-boot:run

3.  Open the app

```{=html}
<!-- -->
```
    http://localhost:8080

------------------------------------------------------------------------

# Branch Strategy

Always create a feature branch.

Example:

    git checkout -b feature/add-training-notes

Branch naming convention:

    feature/<feature-name>
    fix/<bug-name>
    docs/<documentation-update>

------------------------------------------------------------------------

# Development Workflow

When implementing a feature follow this order:

    1. Domain
    2. Repository
    3. Service
    4. UI

Example:

Feature: training notes

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

This workflow prevents business logic from leaking into the UI.

------------------------------------------------------------------------

# Architectural Rules

These rules must always be respected.

## Rule 1

Views must **not access repositories directly**.

Correct:

    View → Service → Repository

------------------------------------------------------------------------

## Rule 2

Business logic belongs in services.

Example:

    AttendanceService.startTrainingSession()

------------------------------------------------------------------------

## Rule 3

Domain must reflect real-world concepts.

Correct model:

    Kid ← Attendance → TrainingSession

Avoid storing attendance directly on the `Kid` entity.

------------------------------------------------------------------------

# Code Guidelines

Prefer:

    clear class names
    short methods
    small classes
    simple logic

Avoid:

    large service classes
    deeply nested conditionals
    duplicated logic

------------------------------------------------------------------------

# Pull Request Process

1.  Create a feature branch
2.  Implement the feature
3.  Ensure architecture rules are followed
4.  Submit a pull request

Before submitting verify:

    feature follows domain model
    business logic in services
    UI remains thin
    no duplicated logic

------------------------------------------------------------------------

# Documentation

If your contribution changes the architecture or domain model, update
the relevant documentation:

    docs/architecture.md
    docs/domain.md
    docs/development.md
    docs/decisions.md

------------------------------------------------------------------------

# Suggested First Contributions

New contributors may start with:

    Kid detail page
    Training notes
    Attendance analytics
    Dashboard improvements

These features help understand the system quickly.

------------------------------------------------------------------------

# Summary

When contributing:

    respect the architecture
    follow the development workflow
    keep the domain model clean

These principles keep the project:

-   understandable
-   maintainable
-   extensible
