# 05 - Bank Loan Processing

XYZ Bank processes loan applications in stages. Every stage is an **array queue** and applications move from one
queue to the next:

`Applications -> Accepted (male / female) or Rejected -> Approved or Disapproved -> Loan dispatched`

**Menu:** add application, show applications, accept or reject the first application, show accepted list,
show rejected list, approve or disapprove, dispatch loan amount, show successful receivers.

## Run
```bash
javac -d out 05-loan-processing-queues/src/loanprocessing/*.java
java -cp out loanprocessing.Main
```
