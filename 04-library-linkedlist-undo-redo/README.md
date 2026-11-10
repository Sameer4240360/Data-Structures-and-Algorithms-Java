# 04 - Library Manager with Undo/Redo (Linked List)

The same library problem as project 03, but the books are stored in a **singly linked list**.
Undo and redo use stacks that hold full copies (snapshots) of the list, and the history keeps the latest 50 actions.

**Menu:** insert at front or end, delete from front or end, undo, redo, display all,
book with maximum copies, search by subject.

## Run
```bash
javac -d out 04-library-linkedlist-undo-redo/src/librarylinkedlist/*.java
java -cp out librarylinkedlist.Main
```
