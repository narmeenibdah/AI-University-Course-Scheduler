# AI University Course Scheduler

A JavaFX desktop application that generates and optimizes university course timetables using a Genetic Algorithm. Each gene represents the assignment of one course to a lecture time and room, while a chromosome represents a complete university timetable.

The system evaluates generated timetables according to hard and soft constraints and searches for a complete schedule with the fewest possible conflicts.

## Main Features

- Generate complete university course timetables.
- Support datasets containing 10, 20, 30, 40, or 50 courses.
- Assign every course to a suitable lecture time and room.
- Check instructor, room, and student-group conflicts.
- Verify room capacity and required room type.
- Evaluate hard and soft constraint violations.
- Display fitness, penalty, generations, and runtime.
- Display a Generation vs. Best Fitness convergence graph.
- Run experiments using different population, mutation, and crossover values.
- Compare the Genetic Algorithm with Hill Climbing.

## Genetic Algorithm Representation

A chromosome represents a complete candidate timetable.

Each gene represents one course assignment and contains:

- Course
- Instructor
- Student group
- Lecture time
- Room

The fitness function measures the quality of each chromosome by calculating hard and soft constraint violations.

## Hard Constraints

- An instructor cannot teach two courses at the same time.
- A room cannot contain two courses at the same time.
- A student group cannot attend two courses at the same time.
- The room capacity must accommodate the number of students.
- The room type must match the course type.
- The course cannot be scheduled during an instructor's unavailable time.

## Soft Constraints

- Reduce undesirable early or late lectures.
- Reduce gaps in student-group schedules.
- Produce a more convenient and organized timetable.

## Genetic Algorithm Operators

### Selection

- Tournament Selection
- Roulette Wheel Selection

### Crossover

- One-Point Crossover
- Two-Point Crossover

### Mutation

- Random Mutation
- Swap Mutation

The implementation also uses elitism to preserve the best chromosome between generations.

## Technologies Used

- Java
- JavaFX
- CSS
- Genetic Algorithm
- Hill Climbing
- Eclipse IDE

## Experiments

The application supports experiments using different:

- Population sizes
- Mutation rates
- Crossover rates
- Dataset sizes
- Selection methods
- Crossover methods
- Mutation methods

The experimental results include fitness, hard violations, soft violations, generations, and runtime.

## Project Results

The system successfully generated complete university timetables with zero hard violations for multiple dataset sizes.

It also demonstrated how dataset size and Genetic Algorithm parameters affect solution quality, convergence speed, and runtime. The Genetic Algorithm was additionally compared with Hill Climbing.

## Running the Project

1. Open the project using Eclipse IDE.
2. Configure the JavaFX SDK in the project build path.
3. Ensure that the project uses a compatible Java version.
4. Run `Main.java`.
5. Select the number of courses.
6. Enter the Genetic Algorithm parameters.
7. Choose the selection, crossover, and mutation methods.
8. Click **Generate Timetable**.

## Project Report

The complete project report includes the problem formulation, system design, Genetic Algorithm implementation, constraints, experiments, results, graphs, and application screenshots.

[View the Complete Project Report](AI_Report-1230833.pdf)
