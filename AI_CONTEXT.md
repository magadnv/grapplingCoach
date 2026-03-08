# AI Context Document

This document is intended for **AI systems and code assistants** to
quickly understand the Grappling Coach project.

The goal is to provide enough context so an AI can: - understand the
architecture - understand the domain model - generate correct code -
follow project conventions

This document should be read together with:

-   README.md
-   docs/architecture.md
-   docs/domain.md
-   docs/development.md

------------------------------------------------------------------------

# Project Purpose

The Grappling Coach application helps coaches manage training
participation.

Core capabilities:

-   manage kids
-   start a training session
-   record attendance
-   review training history
-   show participation statistics

The application is intentionally small and designed as a **clean
architecture learning project**.

------------------------------------------------------------------------

# Technology Stack

The system is written entirely in Java.

Stack:

Java 21\
Spring Boot\
Vaadin (UI)\
Spring Data JPA\
H2 Database\
Maven

The system is a **server-side rendered web application**.

------------------------------------------------------------------------

# Architecture Overview

The project uses a layered architecture.

UI → Service → Repository → Database

Layer responsibilities:

UI\
Handles user interaction and rendering.

Service\
Contains business logic and system behavior.

Repository\
Handles persistence and database queries.

Domain\
Represents the real-world training model.

------------------------------------------------------------------------

# Domain Model

Core entities:

Kid\
TrainingSession\
Attendance

Relationship:

Kid ← Attendance → TrainingSession

Meaning:

A kid attends many training sessions. Each training session has many
kids. Attendance records whether a kid was present.

------------------------------------------------------------------------

# Entities

Kid

Fields:

id\
name

Purpose:

Represents a child participating in training.

------------------------------------------------------------------------

TrainingSession

Fields:

id\
date

Rules:

Only one training session may exist per date.

------------------------------------------------------------------------

Attendance

Fields:

id\
kid\
session\
present

Purpose:

Records whether a kid attended a specific training session.

------------------------------------------------------------------------

# Key Business Rules

Rule 1

Only one TrainingSession per day.

This is enforced by:

service layer guard\
database uniqueness constraint

------------------------------------------------------------------------

Rule 2

Attendance belongs to a training session, not to the kid.

Correct model:

Kid ← Attendance → TrainingSession

------------------------------------------------------------------------

Rule 3

UI components must never access repositories directly.

Correct dependency flow:

View → Service → Repository

------------------------------------------------------------------------

# Code Structure

Main package:

src/main/java/com/grappling/grapplingcoach

Structure:

domain/ repository/ service/ ui/ ui/views/

------------------------------------------------------------------------

# Domain Layer

Contains entities representing the real world.

Examples:

Kid\
TrainingSession\
Attendance

Entities should remain small and simple.

------------------------------------------------------------------------

# Repository Layer

Spring Data JPA repositories.

Examples:

KidRepository\
TrainingSessionRepository\
AttendanceRepository

Repositories only contain persistence logic.

No business logic.

------------------------------------------------------------------------

# Service Layer

Contains business logic.

Example:

AttendanceService

Responsibilities:

start training sessions\
create attendance records\
calculate attendance statistics\
enforce domain rules

------------------------------------------------------------------------

# UI Layer

Built with Vaadin.

Views are located in:

ui/views/

Examples:

DashboardView\
TrainingView\
HistoryView\
TrainingDetailView\
StatisticsView

Responsibilities:

render UI\
handle user interaction\
call services\
display results

------------------------------------------------------------------------

# Typical Feature Workflow

When implementing features, follow this order:

Domain → Repository → Service → UI

Example:

Feature: training notes

Domain TrainingNote

Repository TrainingNoteRepository

Service TrainingNoteService

UI TrainingDetailView

------------------------------------------------------------------------

# Important Conventions

Do not place business logic in UI components.

Do not bypass the service layer.

Domain entities should represent real-world concepts.

Prefer small classes and simple methods.

------------------------------------------------------------------------

# Example Request Flow

User starts a training session.

Flow:

DashboardView\
→ AttendanceService.startTrainingSession()\
→ TrainingSessionRepository.save()\
→ AttendanceRepository.save()\
→ database

------------------------------------------------------------------------

# Future Extensions

Possible extensions for the system:

Kid profile page\
Training notes\
Attendance charts\
Analytics dashboard\
PostgreSQL database\
Coach authentication

------------------------------------------------------------------------

# Summary

The project is intentionally simple and designed to demonstrate:

clean architecture\
domain-driven design basics\
clear separation of concerns\
full-stack Java development

AI systems should follow the architectural rules described above when
generating code.
