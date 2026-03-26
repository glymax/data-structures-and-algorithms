# Dynamic Array

This project contains a manual implementation of a dynamically resizing array in Java.  
The implementation is used as the basis for several data structures such as stacks and queues.

---

## Overview

The core component is the `DynamicArray`, which automatically resizes depending on the number of stored elements.  
The resizing behavior is controlled by two parameters:

- **growthFactor** – determines how much the array grows when resizing
- **maxOverhead** – limits unused memory

The method `reportUsage()` ensures that the array grows or shrinks when necessary.

---

## Implemented Data Structures

### DynamicArray
A dynamic array similar to Java's `ArrayList`.

Features:
- automatic resizing
- configurable growth strategy
- direct indexed access

### DynamicStack
A stack implementation built on top of `DynamicArray`.

Operations:
- `pushBack` – adds an element to the top of the stack
- `popBack` – removes and returns the top element
- `size` – returns the number of elements currently in the stack

### RingQueue
A queue implemented using a circular buffer on top of `DynamicArray`.

Operations:
- `pushBack` – inserts an element at the end of the queue
- `popFront` – removes and returns the element at the front of the queue
- `size` – returns the number of elements currently stored in the queue


### StackyQueue
A queue implemented using **two stacks**.

Idea:
- elements are pushed into the first stack
- when removing elements, they are transferred to the second stack

This results in amortized **O(1)** queue operations.

---

## Additional Components

### Interval
Represents an interval of indices inside the array.

Types:
- `NonEmptyInterval`
- `EmptyInterval`

Used to describe which part of the array currently contains valid data during resizing.

### Interfaces
- `Collection` – base interface providing `size()` and `isEmpty()`
- `Stack` – stack abstraction
- `Queue` – queue abstraction

---

## Purpose

This implementation demonstrates the internal mechanics behind dynamic arrays and how they can be used to build higher level data structures.