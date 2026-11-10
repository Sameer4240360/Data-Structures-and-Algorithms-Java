package loanprocessing;

import java.util.Scanner;

/**
 * Loan processing pipeline built from array queues:
 * applications -> accepted (male / female) or rejected -> approved or disapproved -> dispatched.
 */
class Functions {
    App[] aLst; int cA;     // all applications (waiting)
    App[] rLst; int cR;     // rejected
    App[] mAcc; int cM;     // accepted (male)
    App[] fAcc; int cF;     // accepted (female)
    App[] apLst; int cAp;   // approved
    App[] dLst; int cD;     // disapproved
    App[] sLst; int cS;     // successful receivers (loan dispatched)

    int limit;

    public Functions(int size) {
        limit = size;
        aLst = new App[limit]; cA = 0;
        rLst = new App[limit]; cR = 0;
        mAcc = new App[limit]; cM = 0;
        fAcc = new App[limit]; cF = 0;
        apLst = new App[limit]; cAp = 0;
        dLst = new App[limit]; cD = 0;
        sLst = new App[limit]; cS = 0;
    }

    /** Asks until the user types 1 or 0. */
    private int readDecision(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            int d = InputUtil.readInt(sc);
            if (d == 0 || d == 1) return d;
            System.out.println("Please enter 1 or 0.");
        }
    }

    public void addApp(App newApp) {
        if (cA == limit) {
            System.out.println("List Full:");
            return;
        }
        aLst[cA] = newApp;
        cA++;
        System.out.println("Added:" + newApp.n);
    }

    public void dispApp() {
        if (cA == 0) { System.out.println("Empty:"); return; }
        System.out.println(" App List:");
        for (int i = 0; i < cA; i++) {
            System.out.println((i + 1) + ". " + aLst[i]);
        }
    }

    public void accRej(Scanner sc) {
        if (cA == 0) { System.out.println("Line Empty:"); return; }
        App t = aLst[0];
        System.out.println("Processing:" + t.n);
        int d = readDecision(sc, "1 for Accept, 0 for Reject: ");
        boolean male = (t.g == 'M' || t.g == 'm');

        // Check there is room in the target list before removing the application from the queue
        if (d == 1 && ((male && cM == limit) || (!male && cF == limit))) {
            System.out.println("Accepted list is full:");
            return;
        }
        if (d == 0 && cR == limit) {
            System.out.println("Rejected list is full:");
            return;
        }

        for (int i = 0; i < cA - 1; i++)
            aLst[i] = aLst[i + 1];
        aLst[cA - 1] = null;
        cA--;

        if (d == 1) {
            if (male) {
                mAcc[cM] = t; cM++;
            } else {
                fAcc[cF] = t; cF++;
            }
            System.out.println("Accepted:");
        } else {
            rLst[cR] = t; cR++;
            System.out.println("Rejected:");
        }
    }

    public void dispAcc() {
        System.out.println("Male Acc:");
        if (cM == 0) System.out.println("Empty:");
        for (int i = 0; i < cM; i++) System.out.println(mAcc[i]);

        System.out.println("Female Acc:");
        if (cF == 0) System.out.println("Empty:");
        for (int i = 0; i < cF; i++) System.out.println(fAcc[i]);
    }

    public void dispRej() {
        System.out.println("Rejected:");
        if (cR == 0) System.out.println("Empty:");
        for (int i = 0; i < cR; i++) System.out.println(rLst[i]);
    }

    public void appDis(Scanner sc) {
        System.out.print("Process Male (1) or Female (2):");
        int ch = InputUtil.readInt(sc);
        if (ch != 1 && ch != 2) {
            System.out.println("Invalid choice. Please enter 1 or 2.");
            return;
        }

        App t;
        if (ch == 1) {
            if (cM == 0) { System.out.println("Empty:"); return; }
            t = mAcc[0];
        } else {
            if (cF == 0) { System.out.println("Empty:"); return; }
            t = fAcc[0];
        }

        System.out.println("Processing:" + t.n);
        int d = readDecision(sc, "1 for Approve, 0 for Disapprove:");

        if (d == 1 && cAp == limit) {
            System.out.println("Approved list is full:");
            return;
        }
        if (d == 0 && cD == limit) {
            System.out.println("Disapproved list is full:");
            return;
        }

        if (ch == 1) {
            for (int i = 0; i < cM - 1; i++) mAcc[i] = mAcc[i + 1];
            mAcc[cM - 1] = null;
            cM--;
        } else {
            for (int i = 0; i < cF - 1; i++) fAcc[i] = fAcc[i + 1];
            fAcc[cF - 1] = null;
            cF--;
        }

        if (d == 1) {
            apLst[cAp] = t; cAp++;
            System.out.println("Approved:");
        } else {
            dLst[cD] = t; cD++;
            System.out.println("Disapproved:");
        }
    }

    public void dispLoan() {
        if (cAp == 0) { System.out.println("Empty:"); return; }
        if (cS == limit) { System.out.println("Successful receivers list is full:"); return; }

        App t = apLst[0];
        for (int i = 0; i < cAp - 1; i++) apLst[i] = apLst[i + 1];
        apLst[cAp - 1] = null;
        cAp--;
        sLst[cS] = t; cS++;
        System.out.println("Dispatched to:" + t.n);
    }

    public void dispSucc() {
        System.out.println("Successful Receivers:");
        if (cS == 0) System.out.println("Empty:");
        for (int i = 0; i < cS; i++) System.out.println(sLst[i]);
    }
}
