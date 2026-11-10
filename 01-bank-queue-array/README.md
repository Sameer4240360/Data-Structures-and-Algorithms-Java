# 01 - Bank Priority Queue (Array)

ABC Bank serves customers by priority. The queue is stored in an **array** and a new customer is inserted at the
position that matches their priority.

**Priority order:** 1. VVIP · 2. Female VIP · 3. Female Normal · 4. Male VIP · 5. Male Normal

**Automatic upgrade of "Normal" customers:**
- Male: age 60-70 becomes VIP, above 70 becomes VVIP
- Female: age 50-60 becomes VIP, above 60 becomes VVIP

**Menu:** display all, add customer, serve customer, youngest customer, eldest female, male VVIPs,
female Normals, eldest VIP, search by name, average age of VVIPs.

## Run
```bash
javac -d out 01-bank-queue-array/src/bankqueuearray/*.java
java -cp out bankqueuearray.Main
```
