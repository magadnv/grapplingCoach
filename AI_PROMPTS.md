# AI Development Prompts

This document contains **ready‑to‑use prompts** for working with AI
tools (ChatGPT, Claude, Copilot, Cursor, etc.) while developing the
Grappling Coach project.

The goal is to help AI tools:

-   understand the architecture
-   follow project conventions
-   generate correct code
-   avoid breaking the domain model

These prompts assume the AI has access to the repository and the
documentation.

------------------------------------------------------------------------

# General Context Prompt

Use this prompt at the start of an AI conversation.

    You are helping develop a Java Spring Boot + Vaadin application called "Grappling Coach".

    Architecture:
    UI → Service → Repository → Database

    Important rules:
    - UI must never call repositories directly
    - business logic belongs in services
    - domain model must reflect real training

    Domain model:
    Kid ← Attendance → TrainingSession

    Entities:
    Kid (id, name)
    TrainingSession (id, date)
    Attendance (id, kid, session, present)

    Only one training session per day is allowed.

    When generating code:
    - follow the Domain → Repository → Service → UI workflow
    - keep classes small and readable
    - respect existing architecture

------------------------------------------------------------------------

# Prompt: Implement a New Feature

Use when building a new feature.

    Help me implement a new feature in this Java Spring Boot + Vaadin project.

    Follow this workflow:
    1. Domain
    2. Repository
    3. Service
    4. UI

    Explain each step before writing code.

    Ensure the architecture rule is respected:
    View → Service → Repository.

    Feature:
    <describe feature here>

Example:

    Feature: training notes for each training session.

------------------------------------------------------------------------

# Prompt: Generate a Domain Model

    Design a domain model for the following feature in the Grappling Coach project.

    Follow the existing architecture.

    Existing entities:
    Kid
    TrainingSession
    Attendance

    Feature:
    <feature description>

    Return:
    1. domain entities
    2. relationships
    3. fields
    4. reasoning

------------------------------------------------------------------------

# Prompt: Implement Service Logic

    Implement a service class for this feature.

    Requirements:

    - place business logic in the service
    - use Spring Boot conventions
    - use existing repositories
    - keep methods small and readable

    Feature:
    <describe feature>

------------------------------------------------------------------------

# Prompt: Generate Repository Queries

    Create Spring Data JPA repository methods for the following requirement.

    Project context:
    Java + Spring Boot + JPA.

    Requirement:
    <describe query requirement>

    Return:
    - repository interface methods
    - explanation of the query

------------------------------------------------------------------------

# Prompt: Build a Vaadin View

    Create a Vaadin view for this feature.

    Requirements:

    - use MainLayout
    - UI should remain thin
    - call service methods instead of repositories

    Feature:
    <describe feature>

------------------------------------------------------------------------

# Prompt: Refactor Code

    Refactor the following code to follow the Grappling Coach architecture.

    Rules:
    - business logic in services
    - UI components remain thin
    - repositories only handle persistence

    Return:
    1. improved code
    2. explanation of changes

------------------------------------------------------------------------

# Prompt: Debug a Problem

    Help debug a problem in the Grappling Coach project.

    Architecture:
    UI → Service → Repository

    Steps:
    1. analyze the issue
    2. identify where the bug likely exists
    3. suggest a fix
    4. provide corrected code

------------------------------------------------------------------------

# Prompt: Improve the Domain Model

    Review the following domain model and suggest improvements.

    Existing model:

    Kid
    TrainingSession
    Attendance

    Check:
    - correctness
    - extensibility
    - simplicity

    Return:
    - issues
    - improvements
    - updated design

------------------------------------------------------------------------

# Prompt: Generate Tests

    Generate tests for the following service class.

    Requirements:
    - JUnit tests
    - focus on business logic
    - mock repositories if needed

    Code:
    <paste service class>

------------------------------------------------------------------------

# Prompt: Plan a Feature

    Help plan a feature for the Grappling Coach system.

    Return:

    1. domain changes
    2. service logic
    3. repository queries
    4. UI changes
    5. implementation steps

------------------------------------------------------------------------

# Recommended AI Workflow

When working with AI follow this sequence:

    1. Describe the feature
    2. Design domain model
    3. Implement service logic
    4. Implement repository queries
    5. Build UI
    6. Add tests

This keeps the system architecture clean.

------------------------------------------------------------------------

# Summary

These prompts help AI tools work correctly within the project.

Key rules:

-   respect architecture
-   keep UI thin
-   keep domain correct
-   implement logic in services

Following these principles keeps the project:

-   maintainable
-   understandable
-   extensible
