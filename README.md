# Mini Hospital Emergency Management System

CIT300 - Data Structures and Algorithms | Individual Mid Assignment

## Project Overview

A console-based Java system that simulates hospital emergency management:
patient registration, emergency treatment queueing, treatment history, and
per-patient visit history. Each requirement is backed by a specific data
structure implemented from scratch (no built-in `java.util.Stack` /
`java.util.Queue` / `java.util.LinkedList` / `java.util.TreeMap`).

## Data Structures Used

| Requirement                  | Data Structure          | File(s)                                 |
|-------------------------------|--------------------------|------------------------------------------|
| Patient Records                | Binary Search Tree (BST) | `PatientBST.java`, `Patient.java`        |
| Emergency Patient Queue         | Queue (FIFO)              | `EmergencyQueue.java`                    |
| Treatment History               | Stack (LIFO)              | `TreatmentStack.java`, `TreatmentRecord.java` |
| Patient Visit History           | Singly Linked List        | `VisitLinkedList.java`, `Visit.java`     |

- **`PatientBST`** stores patients keyed by Patient ID, supporting insert,
  search, delete (all 3 cases: leaf, one child, two children via in-order
  successor), and in-order traversal.
- **`EmergencyQueue`** is a custom linked-list-based FIFO queue: `enqueue`
  adds to the rear, `dequeue` removes from the front.
- **`TreatmentStack`** is a custom linked-list-based LIFO stack: `push` adds
  a completed treatment on top, `pop` removes the most recent one.
- **`VisitLinkedList`** is a singly linked list attached to each `Patient`
  object, holding that patient's visit history (add, remove, search,
  display).

## Project Structure

```
hospital/
├── src/
│   ├── Patient.java
│   ├── Visit.java
│   ├── VisitLinkedList.java
│   ├── PatientBST.java
│   ├── EmergencyQueue.java
│   ├── TreatmentRecord.java
│   ├── TreatmentStack.java
│   ├── Main.java      <- interactive menu-driven system
│   └── Demo.java       <- non-interactive walkthrough of every operation
├── README.md
└── commit.sh            <- helper script for progressive git commits
```

## How to Compile & Run

From the `src/` folder:

```bash
javac *.java

# Interactive menu system
java Main

# Automatic walkthrough of every required operation (for screen recording)
java Demo
```

## Menu Options (Main.java)

1. Register new patient (BST insert)
2. Search patient by ID (BST search)
3. Delete patient (BST delete)
4. Display all patients - in-order (BST traversal)
5. Add patient to emergency queue (enqueue)
6. Display emergency queue
7. Dequeue next patient & complete treatment (moves patient from Queue,
   creates a record pushed onto the Stack)
8. Display treatment history (Stack)
9. Pop most recent treatment record (Stack)
10. Add a visit to a patient's history (Linked List)
11. Remove a visit from a patient's history (Linked List)
12. Search a visit in a patient's history (Linked List)
13. Display a patient's visit history (Linked List)
0. Exit

## Notes on Design Decisions

- Each patient owns its own `VisitLinkedList` instance, so visit history is
  naturally scoped per patient rather than kept as one global list.
- Treatment completion is wired to the queue: dequeuing a patient for
  treatment immediately prompts for treatment details and pushes the
  resulting record onto the treatment stack, mirroring the real workflow
  (waiting -> being treated -> treatment recorded).
- All four data structures are implemented manually with their own node
  classes to demonstrate the underlying mechanics required by the
  assignment, rather than relying on Java's built-in collection classes.
