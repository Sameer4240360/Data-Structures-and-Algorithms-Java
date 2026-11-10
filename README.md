# Data Structures in Java

Console projects built in Java for my **Data Structures** course (BS Artificial Intelligence, Arid University, Rawalpindi).
Each project implements a data structure from scratch (arrays, linked lists, queues, undo/redo history, graphs)
and uses it to solve a real-world style problem.

**Author:** Sameer Ahmed · [LinkedIn](https://www.linkedin.com/in/sameer-ahmed-75111a359/)

## Projects

| # | Project | Data structure | Highlights |
|---|---|---|---|
| 01 | [Bank Priority Queue (Array)](01-bank-queue-array) | Array | VVIP / VIP / Normal priority, automatic VIP upgrade by age |
| 02 | [Bank Priority Queue (Linked List)](02-bank-queue-linkedlist) | Singly linked list | Same bank problem solved with a sorted linked list |
| 03 | [Library Manager with Undo/Redo (Array)](03-library-array-undo-redo) | Array + snapshot stacks | Copy counts, undo and redo of every change |
| 04 | [Library Manager with Undo/Redo (Linked List)](04-library-linkedlist-undo-redo) | Linked list + snapshot stacks | Same library problem with linked lists |
| 05 | [Bank Loan Processing](05-loan-processing-queues) | Multiple array queues | Application, accept/reject, approve/disapprove, dispatch pipeline |
| 06 | [Transportation Network (Graph)](06-graph-adjacency-matrix) | Graph, adjacency matrix | Add/delete cities and routes, DFS, BFS |
| 07 | [Social Network (Graph)](07-graph-adjacency-list) | Graph, adjacency list | Add/delete users and friendships, DFS, BFS |
| 08 | [Stationery Inventory (Final Project)](08-stationery-inventory-mysql) | Linked list + MySQL (JDBC) | Items saved to a database and reloaded on start-up |

## Requirements

- Java 11 or newer (JDK)
- Project 08 also needs MySQL and the MySQL Connector/J driver (see its README)

## How to run

Every project is independent. From the repository root, for example project 01:

```bash
javac -d out 01-bank-queue-array/src/bankqueuearray/*.java
java -cp out bankqueuearray.Main
```

The exact commands for every project are in its own README.
In Eclipse or IntelliJ you can also create a project and copy the `src` folder into it.

## Notes

- The program names inside each class (`n`, `a`, `g`, `t`, `hd`, `nxt` ...) are short on purpose, as in the original coursework.
  Every class has a comment that explains what it does.
- See [CHANGES.md](CHANGES.md) for the bugs I fixed and the improvements I made.
