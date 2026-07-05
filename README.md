# Height Balanced Tree (HBTree) Implementation in Java

This repository contains a native Java implementation of a custom **Height Balanced Tree (HBTree)** data structure. The HBTree is a specialized variation of a Balanced Binary Search Tree (BST) that stores unique keys and dynamically adjusts its structure using a unique, size-dependent height threshold factor.

---

##  Balance Condition Mechanics

A node \(K\) inside an HBTree is considered balanced if and only if the absolute height difference between its left and right subtrees satisfies the following inequality:

\[\lvert \text{height}(\text{left}) - \text{height}(\text{right}) \rvert \le \max(1, \lfloor \log_2(N) \rfloor)\]

Where:
* **\(N\)**: The **weight** (total node count) of the entire subtree rooted at node \(K\).
* **\(\lfloor \log_2(N) \rfloor\)**: The floor of the base-2 logarithm of the subtree's total size.

---

##  Node Structure Specifications

Every individual node component inside the `HBTree` must maintain and calculate the following local attributes:
1. **Key Value:** A unique comparable identifier (`int`, `String`, etc.).
2. **Height Tracker:** Calculated as the maximum height of its two immediate child subtrees plus one:
   \[\text{height}(K) = \max(\text{height}(\text{left}), \text{height}(\text{right})) + 1\]
3. **Weight Tracker:** The absolute total count of all nodes existing within the subtree rooted at the current node (inclusive of the current node itself):
   \[\text{weight}(K) = \text{weight}(\text{left}) + \text{weight}(\text{right}) + 1\]

---

##  Balancing Operations & Rotations

When a node breaks the balance condition, structural equilibrium is restored using standard binary tree rotations:
* **Single Right Rotation** (`rotateRight`)
* **Single Left Rotation** (`rotateLeft`)
* **Double Right Rotation** (Left-Right `rotateLeftRight`)
* **Double Left Rotation** (Right-Left `rotateRightLeft`)

*Note: Any rotation execution must immediately recalculate and update the respective `height` and `weight` metrics of all affected internal nodes.*

---

##  Insertion & Deletion Algorithms

### 1. Insertion Protocol (`insert`)
* Perform standard BST leaf insertion for a unique key.
* Traverse **upward** from the newly added leaf node back toward the root.
* Update the `height` and `weight` of every node along this path.
* If a node violates the HBTree balance condition, execute the appropriate rotation type.
* **Terminal Rule:** While the structural balancing operation resolves the local violation, you **must continue** traversing and updating `height` and `weight` attributes all the way up to the root node.

### 2. Deletion Protocol (`delete`)
* Remove the target node using standard BST deletion rules.
* Traverse **upward** starting directly from the parent of the physically deleted node toward the root.
* Update the `height` and `weight` parameters for each node along the path.
* If any node violates the balance condition, apply the corresponding rotation.
* **Cascading Check Rule:** The rotation operation here is **non-terminal**. After a rotation is performed, immediately re-verify the balance condition of the nodes involved in that rotation. If any involved node remains unbalanced post-rotation, trigger consecutive balancing sequences at that node.
* Continue checking for balance violations and updating attributes up to the root.

---

##  Project Package Structure

```text
├── src/
│   ├── Main.java         # Test execution driver and verification suites
│   ├── HBTree.java       # Core tree logic (insert, delete, balance loops)
│   └── HBNode.java       # Concrete node model containing key, height, and weight
└── README.md             # Project documentation manual
```

---

##  Execution & Testing

### Compilation
Compile all core source files through your local terminal using the Java Development Kit (JDK):
```bash
javac src/*.java -d bin/
```

### Execution
Run the compiled byte-code files:
```bash
java -cp bin Main
```
