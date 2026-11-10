# 03 - Library Manager with Undo/Redo (Array)

A library stored in an **array**. A book is identified by its title and edition; adding it again increases the
number of copies, and deleting a book with several copies removes one copy first.

**Undo / redo** is implemented with two stacks of snapshots. The history keeps the latest 50 actions.

**Menu:** insert at front or end, delete from front or end, undo, redo, display all,
book with maximum copies, search by subject.

## Run
```bash
javac -d out 03-library-array-undo-redo/src/libraryarray/*.java
java -cp out libraryarray.Main
```
