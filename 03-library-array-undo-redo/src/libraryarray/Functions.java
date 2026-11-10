package libraryarray;

/**
 * Library stored in an array, with undo / redo implemented using snapshot stacks.
 * Each snapshot is a full copy of the library, and the history keeps the latest MAX_HISTORY actions.
 */
class Functions {
    private static final int MAX_HISTORY = 50;

    Book[] arr;
    int c;          // current number of books
    int limit;      // array capacity
    Book[][] uArr;  // undo stack (snapshots)
    int[] uC;       // number of books in each undo snapshot
    int uT;         // top of undo stack
    Book[][] rArr;  // redo stack (snapshots)
    int[] rC;
    int rT;         // top of redo stack

    public Functions(int size) {
        limit = size;
        arr = new Book[limit];
        c = 0;

        uArr = new Book[MAX_HISTORY][limit];
        uC = new int[MAX_HISTORY];
        uT = -1;

        rArr = new Book[MAX_HISTORY][limit];
        rC = new int[MAX_HISTORY];
        rT = -1;
    }

    /** Saves the current state to the undo stack. Must be called before every change. */
    void saveS() {
        if (uT == MAX_HISTORY - 1) {
            // Stack is full: drop the oldest snapshot instead of overflowing
            Book[] oldest = uArr[0];
            for (int i = 0; i < uT; i++) {
                uArr[i] = uArr[i + 1];
                uC[i] = uC[i + 1];
            }
            uArr[uT] = oldest;
            uT--;
        }
        uT++;
        uC[uT] = c;
        for (int i = 0; i < c; i++) {
            uArr[uT][i] = new Book(arr[i].bId, arr[i].p, arr[i].t, arr[i].e, arr[i].s, arr[i].nc);
        }
        rT = -1;   // a new action clears the redo history
    }

    public void undo() {
        if (uT == -1) {
            System.out.println("Nothing to undo.");
            return;
        }

        rT++;
        rC[rT] = c;
        for (int i = 0; i < c; i++) {
            rArr[rT][i] = new Book(arr[i].bId, arr[i].p, arr[i].t, arr[i].e, arr[i].s, arr[i].nc);
        }

        c = uC[uT];
        for (int i = 0; i < c; i++) {
            arr[i] = new Book(uArr[uT][i].bId, uArr[uT][i].p, uArr[uT][i].t, uArr[uT][i].e, uArr[uT][i].s, uArr[uT][i].nc);
        }
        for (int i = c; i < limit; i++) arr[i] = null;
        uT--;
        System.out.println("Undo successful.");
    }

    public void redo() {
        if (rT == -1) {
            System.out.println("Nothing to redo.");
            return;
        }

        uT++;
        uC[uT] = c;
        for (int i = 0; i < c; i++) {
            uArr[uT][i] = new Book(arr[i].bId, arr[i].p, arr[i].t, arr[i].e, arr[i].s, arr[i].nc);
        }

        c = rC[rT];
        for (int i = 0; i < c; i++) {
            arr[i] = new Book(rArr[rT][i].bId, rArr[rT][i].p, rArr[rT][i].t, rArr[rT][i].e, rArr[rT][i].s, rArr[rT][i].nc);
        }
        for (int i = c; i < limit; i++) arr[i] = null;
        rT--;
        System.out.println("Redo successful:");
    }

    /** Returns the index of the book with this title and edition, or -1. */
    int find(String t, int e) {
        for (int i = 0; i < c; i++) {
            if (arr[i].t.equalsIgnoreCase(t) && arr[i].e == e) {
                return i;
            }
        }
        return -1;
    }

    public void insF(Book b) {
        int idx = find(b.t, b.e);

        if (idx != -1) {
            saveS();
            arr[idx].nc++;
            System.out.println("Book exists.Copies incremented.");
            return;
        }

        if (c == limit) {
            System.out.println("Library is full:");
            return;
        }

        saveS();
        for (int i = c; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = b;
        c++;
        System.out.println("Book inserted at the front.");
    }

    public void insE(Book b) {
        int idx = find(b.t, b.e);

        if (idx != -1) {
            saveS();
            arr[idx].nc++;
            System.out.println("Book exists. Copies incremented:");
            return;
        }

        if (c == limit) {
            System.out.println("Library is full:");
            return;
        }

        saveS();
        arr[c] = b;
        c++;
        System.out.println("Book inserted at the end.");
    }

    public void delF() {
        if (c == 0) {
            System.out.println("Library is empty:");
            return;
        }
        saveS();

        if (arr[0].nc > 1) {
            arr[0].nc--;
            System.out.println("Front book copy decremented.");
        } else {
            System.out.println("Deleted from front: " + arr[0].t);
            for (int i = 0; i < c - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[c - 1] = null;
            c--;
        }
    }

    public void delE() {
        if (c == 0) {
            System.out.println("Library is empty.");
            return;
        }
        saveS();

        if (arr[c - 1].nc > 1) {
            arr[c - 1].nc--;
            System.out.println("End book copy decremented.");
        } else {
            System.out.println("Deleted from end:" + arr[c - 1].t);
            arr[c - 1] = null;
            c--;
        }
    }

    public void dispAll() {
        if (c == 0) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("\n--- Library Books ---");
        for (int i = 0; i < c; i++) {
            System.out.println((i + 1) + ". " + arr[i]);
        }
    }

    public void dispMaxC() {
        if (c == 0) {
            System.out.println("Library is empty.");
            return;
        }
        int max = arr[0].nc;
        for (int i = 1; i < c; i++) {
            if (arr[i].nc > max) {
                max = arr[i].nc;
            }
        }
        System.out.println("\n--- Books with Maximum Copies (" + max + ") ---");
        for (int i = 0; i < c; i++) {
            if (arr[i].nc == max) {
                System.out.println(arr[i]);
            }
        }
    }

    public void searchS(String sub) {
        boolean f = false;
        System.out.println("\n--- Search Results for Subject: " + sub + " ---");
        for (int i = 0; i < c; i++) {
            if (arr[i].s.equalsIgnoreCase(sub)) {
                System.out.println(arr[i]);
                f = true;
            }
        }
        if (!f) {
            System.out.println("No books found for this subject:");
        }
    }
}
