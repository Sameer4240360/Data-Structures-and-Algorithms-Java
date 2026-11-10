# 08 - Stationery Inventory (Final Project)

A stationery store inventory kept in a **singly linked list** and saved in a **MySQL** database with JDBC.
Items are loaded from the database when the program starts, and every change is saved to the database first.

**Menu:** add item at front, add item at end, insert after a given ID, delete from front, delete from end,
update price and quantity, view all items, calculate the grand total.

## Setup

1. **Create the database and table** (run once):
   ```bash
   mysql -u root -p < database/schema.sql
   ```
2. **Download the MySQL Connector/J** jar (platform independent) from the
   [MySQL downloads page](https://dev.mysql.com/downloads/connector/j/) and put it in a `lib/` folder.
3. **Set your database login** as environment variables (the password is never stored in the code):

   | Variable | Default |
   |---|---|
   | `DB_URL` | `jdbc:mysql://localhost:3306/stationery_db` |
   | `DB_USER` | `root` |
   | `DB_PASSWORD` | *(empty)* |

   Windows (Command Prompt): `set DB_PASSWORD=your_password`
   Windows (PowerShell): `$env:DB_PASSWORD="your_password"`
   Linux / macOS: `export DB_PASSWORD=your_password`

   In Eclipse: Run > Run Configurations > Environment tab > New.

## Run

```bash
javac -d out 08-stationery-inventory-mysql/src/stationeryinventory/*.java
```
Linux / macOS:
```bash
java -cp "out:lib/*" stationeryinventory.Main
```
Windows:
```bash
java -cp "out;lib/*" stationeryinventory.Main
```

## Notes
- Table columns: `id` (primary key), `name`, `price`, `quantity`.
- After a restart, items are loaded in the order the database returns them (by ID), not in the order they were inserted.
