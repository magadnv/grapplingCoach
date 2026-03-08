# Domain Model

This document explains the **core domain concepts** of the Grappling
Coach application.

The goal of the system is to help grappling coaches track training
participation and gain insights about attendance.

The domain model mirrors how training works in reality.

------------------------------------------------------------------------

# Core Concepts

The system revolves around three main entities:

    Kid
    TrainingSession
    Attendance

These entities represent the real-world relationships between children
and training events.

------------------------------------------------------------------------

# Kid

Represents a child who participates in grappling training.

Example:

    Max
    Tom
    Anna

### Attributes

    Kid
    - id
    - name

### Responsibilities

A `Kid` represents a participant in the training program.

A kid can attend **many training sessions over time**.

Relationship:

    Kid → many Attendances

------------------------------------------------------------------------

# TrainingSession

Represents a single training event on a specific date.

Example:

    Training on 2026-03-06
    Training on 2026-03-08
    Training on 2026-03-10

### Attributes

    TrainingSession
    - id
    - date

### Rules

The system enforces the rule:

    Only one TrainingSession per day

This prevents duplicate attendance records.

Relationship:

    TrainingSession → many Attendances

------------------------------------------------------------------------

# Attendance

Attendance connects a `Kid` to a `TrainingSession`.

It answers the question:

    Was this kid present at this training?

### Attributes

    Attendance
    - id
    - kid
    - session
    - present

### Example

    TrainingSession (2026-03-06)

    Kid      Present
    Max      true
    Tom      false
    Anna     true

### Relationship Model

    Kid ← Attendance → TrainingSession

This design allows:

-   A kid to attend many trainings
-   Each training to have many kids

------------------------------------------------------------------------

# Why Attendance is a Separate Entity

A common beginner mistake is storing presence directly on the kid:

    Kid
    - name
    - present

This design breaks when multiple trainings exist.

Example problem:

    Monday → present
    Wednesday → absent
    Friday → present

Where should that information be stored?

By introducing `Attendance`, each training has its own attendance
records.

Correct model:

    Kid ← Attendance → TrainingSession

------------------------------------------------------------------------

# Example Data

Example training session:

    TrainingSession
    Date: 2026-03-06

Kids:

    Max
    Tom
    Anna

Attendance table:

    Kid   Session   Present
    Max   06.03     true
    Tom   06.03     false
    Anna  06.03     true

------------------------------------------------------------------------

# Derived Metrics

The system can compute insights from attendance data.

Examples:

### Attendance Rate

    present trainings / total trainings

Example:

    Max attended 8 of 10 trainings
    Attendance rate = 80%

### Today Present

    kids marked present for today's training

Example:

    8 / 12 kids present

------------------------------------------------------------------------

# Future Domain Extensions

Possible future additions:

## Belt Level

    Kid
    - beltLevel

## Skill Progress

Track techniques learned.

    KidSkill
    - kid
    - technique
    - level

## Training Notes

Allow coaches to attach notes to training sessions.

    TrainingNote
    - session
    - text

------------------------------------------------------------------------

# Summary

The domain model is intentionally small and focused.

Core idea:

    Kids attend training sessions.
    Attendance records who was present.

This structure enables:

-   training history
-   attendance statistics
-   participation insights
-   future coaching analytics
