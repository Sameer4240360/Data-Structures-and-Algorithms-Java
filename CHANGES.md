# Changes and fixes

These projects started as coursework submissions. While preparing them for GitHub I tested every menu option
and fixed the problems below. The data structure logic itself is unchanged.

## All projects
- Added `InputUtil`: typing letters where a number is expected no longer crashes the program.
- Gender and customer type are validated (bank projects and loan project), so wrong values are not stored silently.
- Removed unused imports and added comments to every class.

## Fixes by project

| Project | Problem | Fix |
|---|---|---|
| 03, 04 Library | The undo history held only 50 actions, so the **51st action crashed** with `ArrayIndexOutOfBoundsException` | History now keeps the latest 50 actions and drops the oldest |
| 03 Library (array) | Failed actions (for example "library is full") still wiped the redo history | A snapshot is saved only when the action really changes the library |
| 01, 02 Bank | Average VVIP age was rounded down (integer division) | Shows the exact average, for example `72.5` |
| 01 Bank | Search message printed `AliNot Found:` (missing space) | Fixed |
| 05 Loan | An invalid accept/reject answer **lost the application**, and a wrong gender silently went to the female list | Answers are re-asked until valid, applications are removed from the queue only after the destination list has room, gender is validated |
| 05 Loan | Choosing anything other than 1 for "Male" was treated as "Female" | Only 1 or 2 are accepted |
| 06 Graph (matrix) | After deleting a node, the old last row and column stayed in the matrix, so **a new node inherited old edges** | Stale row and column are cleared when a node is deleted |
| 06 Graph (matrix) | A node could be connected to itself; wrong spacing in one message | Self-connection is refused; message fixed |
| 07 Graph (list) | The same edge could be added twice, so deleting it left a copy behind | Duplicate edges are refused |
| 07 Graph (list) | "Edge Deleted" was printed even when no edge existed | Prints "No edge existed." |
| 07 Graph (list) | DFS and BFS used arrays of fixed size 100, which overflow in larger graphs | Arrays are sized to the number of nodes |
| 07 Graph (list) | Edges stored the typed name, so `bob` and `Bob` could appear differently | The real node name is stored |
| 08 Stationery | **The database password was hard-coded in the source code** | Connection details are read from environment variables |
| 08 Stationery | A new connection was opened for every operation and never closed | Connections are closed automatically (try-with-resources) |
| 08 Stationery | If the database insert failed (for example duplicate ID), the item was still added to the list | The database is updated first; the list changes only if that succeeds |
| 08 Stationery | Duplicate IDs, empty names and negative price/quantity were accepted | All are validated |
| 08 Stationery | `module-info.java` was not needed and can break the MySQL driver on the classpath | Removed |
