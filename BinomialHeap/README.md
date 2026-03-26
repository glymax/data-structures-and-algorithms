# Binomial Heap (Java)

Implementation of a **Binomial Heap** in Java.

A binomial heap is a priority queue data structure that supports efficient merging of heaps.
It is composed of a collection of binomial trees that satisfy the heap property.

---

## Features

The following operations are implemented:

* `insert(int key)` – inserts a new element into the heap
* `min()` – returns the minimum element
* `deleteMin()` – removes and returns the minimum element
* `merge()` – merges two binomial trees of the same rank

The heap internally stores trees grouped by their rank and merges trees similarly to binary addition.

---

## Implementation details

The implementation consists of two main classes:

### BinomialTreeNode

Represents a node of a binomial tree.

Responsibilities:

* stores the element value
* stores child nodes
* computes the rank of the tree
* merges two binomial trees
* provides DOT graph generation support

### BinomialHeap

Represents the heap itself.

Key ideas:

* trees are stored in a map indexed by their rank
* insertion merges trees similarly to carrying in binary numbers
* the minimum element is tracked via the root reference
* deleting the minimum reinserts the removed root’s children back into the heap

---

## Visualization

The heap can be exported to Graphviz DOT format using:

```
System.out.println(binomialHeap);
```

This allows visualizing the structure of the heap as a directed graph (http://webgraphviz.com/).

---

## Performance tests

The project includes several simple performance tests:

* `performanceTest1()` – inserts 5000 random elements
* `performanceTest2()` – inserts and removes elements
* `performanceTest3()` – mixed insert/delete workload

Execution time is measured using `System.nanoTime()`.
