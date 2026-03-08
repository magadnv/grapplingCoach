# Architectural Decisions

This document records the key architectural and design decisions made in
the Grappling Coach project.

The goal is to explain **why certain choices were made** so that future
contributors understand the reasoning behind the architecture.

This prevents developers from accidentally breaking important design
principles.

------------------------------------------------------------------------

# Decision 1 --- Layered Architecture

## Decision

The project follows a layered architecture:

    UI → Service → Repository → Database

## Rationale

This structure separates responsibilities clearly:

-   UI handles presentation
-   Services contain business logic
-   Repositories manage persistence
-   Domain objects represent real-world concepts

This makes the system easier to understand and modify.

## Consequences

Advantages:

-   clean separation of concerns
-   easier testing
-   simpler debugging
-   maintainable structure

Trade‑off:

-   requires discipline when adding new features

------------------------------------------------------------------------

# Decision 2 --- Domain Model Based on Real Training

## Decision

The domain is modeled around three core entities:

    Kid
    TrainingSession
    Attendance

## Rationale

A child can attend many trainings.

Attendance is not a property of the child, but a relationship between:

    Kid ← Attendance → TrainingSession

This allows the system to correctly represent:

-   training history
-   attendance statistics
-   participation trends

## Consequences

Advantages:

-   accurate real‑world model
-   flexible future analytics

Trade‑off:

-   slightly more complex than storing presence on the Kid entity

------------------------------------------------------------------------

# Decision 3 --- One Training Session per Day

## Decision

The system enforces that only one `TrainingSession` can exist per date.

## Rationale

Training sessions represent daily practice.

Allowing multiple sessions on the same date would create inconsistent
attendance data.

## Implementation

Enforced through:

-   service layer guard
-   database uniqueness constraint

## Consequences

Advantages:

-   consistent attendance records
-   simplified dashboard logic

Trade‑off:

-   system assumes only one training per day

------------------------------------------------------------------------

# Decision 4 --- Vaadin for UI

## Decision

The project uses Vaadin for the user interface.

## Rationale

Vaadin allows full‑stack development using only Java.

Benefits:

-   no JavaScript infrastructure required
-   tight integration with Spring Boot
-   rapid UI development

## Consequences

Advantages:

-   simpler development environment
-   strong type safety
-   faster prototyping

Trade‑off:

-   less control over client-side rendering compared to JS frameworks

------------------------------------------------------------------------

# Decision 5 --- Service Layer for Business Logic

## Decision

Business logic is placed in services rather than in UI components.

## Rationale

Keeping logic in services ensures:

-   UI remains thin
-   logic can be reused
-   business rules remain centralized

Example:

    AttendanceService.startTrainingSession()

## Consequences

Advantages:

-   easier maintenance
-   cleaner architecture
-   easier testing

Trade‑off:

-   requires developers to respect architectural boundaries

------------------------------------------------------------------------

# Decision 6 --- Dashboard‑Driven Workflow

## Decision

The application starts with a dashboard that provides quick access to
key actions and metrics.

## Rationale

Coaches need fast access to:

-   today's attendance
-   training session controls
-   participation overview

A dashboard provides this information immediately.

## Consequences

Advantages:

-   improved usability
-   faster workflow during training
-   clearer overview of activity

Trade‑off:

-   dashboard requires additional service queries

------------------------------------------------------------------------

# Decision 7 --- Documentation for Developer Onboarding

## Decision

The project includes dedicated documentation in the `docs/` directory.

## Rationale

Good documentation helps new developers quickly understand:

-   system architecture
-   domain concepts
-   development workflow

## Documents Included

    architecture.md
    domain.md
    project-structure.md
    development.md
    decisions.md

## Consequences

Advantages:

-   easier onboarding
-   clearer design intent
-   reduced architectural drift

Trade‑off:

-   documentation must be maintained

------------------------------------------------------------------------

# Summary

Key principles guiding the project:

    simple domain model
    clear architecture
    small focused modules
    business logic in services
    thin UI layer

These decisions aim to keep the project:

-   understandable
-   maintainable
-   extensible
