# Maze Solver (Right-Hand Rule)

This project implements a simple **maze solving algorithm** using the **right-hand rule**.
The algorithm navigates through a maze by always keeping the right hand on the wall until it either finds the exit or returns to the starting position.

The project was implemented in **Java** and also includes a small **GUI visualization** for displaying the maze and the path taken by the solver.

---

## Algorithm

The solver follows the **right-hand rule**, a classical maze solving strategy:

1. Keep your right hand touching the wall.
2. If there is no wall on the right → turn right.
3. Otherwise, if the path in front is free → go forward.
4. Otherwise, if the left side is free → turn left.
5. Otherwise → turn around.

This method guarantees finding the exit in **simply connected mazes** (mazes without isolated wall sections).

---

## Project Structure

```
MazeSolver
│
├── Walker.java
│   Implements the maze traversal algorithm.
│
├── Maze.java
│   Generates random mazes and visualizes them using Swing.
│
├── StudentResult.java
│   Stores the path taken by the solver.
│
└── Result.java
    Interface used to record visited coordinates.
```

---

## Maze Representation

The maze is represented as a **2D boolean array**:

```
true  -> wall
false -> free cell
```

The entrance is located at:

```
(1, 0)
```

The exit is located at:

```
(width - 1, height - 2)
```

---

## Visualization

The project contains a small **Swing-based GUI** that visualizes:

* **White** – free cells
* **Dark gray** – walls
* **Yellow** – path taken by the solver
* **Red** – final position of the walker

---

## Notes

The right-hand rule works correctly only if the maze is **simply connected**, meaning all walls are connected to the outer boundary.

In mazes with isolated wall structures, this method may fail to find the exit.

