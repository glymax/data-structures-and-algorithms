# AVL Tree (Java)

Implementation of an **AVL Tree** in Java, a self-balancing binary search tree.
The tree maintains a balance factor for each node and automatically performs rotations during insertion to guarantee logarithmic height.

---

## Features

* `insert(int key)` — inserts a new key into the tree
* `find(int key)` — searches for a key
* `height()` — returns the tree height
* `validAVL()` — checks whether the tree satisfies AVL properties

---

## Implementation details

The implementation consists of two main classes:

### AVLTreeNode

* stores the node key
* keeps references to left and right children
* maintains the balance factor
* provides helper methods (height calculation and Graphviz visualization)

### AVLTree

* manages the tree structure
* provides insertion, search, and validation
* performs rotations to maintain AVL balance

---

## AVL Property

For every node:

balance = height(right) − height(left)

The balance factor must satisfy:

-1 ≤ balance ≤ 1

If the balance becomes ±2 after insertion, the tree is rebalanced using rotations.

---

## Visualization

The tree can be exported in **Graphviz DOT format**:

```
System.out.println(avlTree);
```

This output can be rendered using Graphviz.

