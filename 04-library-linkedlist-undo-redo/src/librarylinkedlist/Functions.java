package librarylinkedlist;

/**
 * Library stored in a singly linked list, with undo / redo implemented using snapshot stacks.
 * Each snapshot is a full copy of the list, and the history keeps the latest MAX_HISTORY actions.
 */
class Functions {
    private static final int MAX_HISTORY = 50;

    Book head;

    Book[] uArr;   // undo stack (list snapshots)
    int uT;
    Book[] rArr;   // redo stack (list snapshots)
    int rT;

    public Functions() {
        head = null;
        uArr = new Book[MAX_HISTORY];
        uT = -1;
        rArr = new Book[MAX_HISTORY];
        rT = -1;
    }

    /** Returns a full copy of the list starting at h. */
    Book copyL(Book h) {
        if (h == null) return null;
        Book nH = new Book(h.bId, h.p, h.t, h.e, h.s, h.nc);
        Book cN = nH;
        Book cO = h.next;

        while (cO != null) {
            cN.next = new Book(cO.bId, cO.p, cO.t, cO.e, cO.s, cO.nc);
            cN = cN.next;
            cO = cO.next;
        }
        return nH;
    }

    /** Saves the current state to the undo stack. Must be called before every change. */
    void saveS() {
        if (uT == MAX_HISTORY - 1) {
            // Stack is full: drop the oldest snapshot instead of overflowing
            for (int i = 0; i < uT; i++) {
                uArr[i] = uArr[i + 1];
            }
            uT--;
        }
        uT++;
        uArr[uT] = copyL(head);
        rT = -1;   // a new action clears the redo history
    }

    public void undo() {
        if (uT == -1) {
            System.out.println("Nothing to undo.");
            return;
        }
        rT++;
        rArr[rT] = copyL(head);
        head = copyL(uArr[uT]);
        uT--;
        System.out.println("Undo successful.");
    }

    public void redo() {
        if (rT == -1) {
            System.out.println("Nothing to redo.");
            return;
        }
        uT++;
        uArr[uT] = copyL(head);
        head = copyL(rArr[rT]);
        rT--;
        System.out.println("Redo successful.");
    }

    /** Returns the book with this title and edition, or null. */
    Book find(String t, int e) {
        Book temp = head;
        while (temp != null) {
            if (temp.t.equalsIgnoreCase(t) && temp.e == e) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void insF(Book b) {
        Book ex = find(b.t, b.e);
        saveS();

        if (ex != null) {
            ex.nc++;
            System.out.println("Book exists. Copies incremented.");
            return;
        }
        b.next = head;
        head = b;
        System.out.println("Book inserted at the front.");
    }

    public void insE(Book b) {
        Book ex = find(b.t, b.e);
        saveS();

        if (ex != null) {
            ex.nc++;
            System.out.println("Book exists. Copies incremented.");
            return;
        }
        if (head == null) {
            head = b;
        } else {
            Book temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = b;
        }
        System.out.println("Book inserted at the end.");
    }

    public void delF() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        saveS();
        if (head.nc > 1) {
            head.nc--;
            System.out.println("Front book copy decremented.");
        } else {
            System.out.println("Deleted from front: " + head.t);
            head = head.next;
        }
    }

    public void delE() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        saveS();
        if (head.next == null) {
            if (head.nc > 1) {
                head.nc--;
                System.out.println("End book copy decremented.");
            } else {
                System.out.println("Deleted from end: " + head.t);
                head = null;
            }
            return;
        }
        Book temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        if (temp.next.nc > 1) {
            temp.next.nc--;
            System.out.println("End book copy decremented.");
        } else {
            System.out.println("Deleted from end: " + temp.next.t);
            temp.next = null;
        }
    }

    public void dispAll() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("\n--- Library Books ---");
        Book temp = head;
        int i = 1;
        while (temp != null) {
            System.out.println(i + ". " + temp);
            temp = temp.next;
            i++;
        }
    }

    public void dispMaxC() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        int max = head.nc;
        Book temp = head.next;
        while (temp != null) {
            if (temp.nc > max) {
                max = temp.nc;
            }
            temp = temp.next;
        }
        System.out.println("\n--- Books with Maximum Copies (" + max + ") ---");
        temp = head;
        while (temp != null) {
            if (temp.nc == max) {
                System.out.println(temp);
            }
            temp = temp.next;
        }
    }

    public void searchS(String sub) {
        boolean f = false;
        System.out.println("\n--- Search Results for Subject: " + sub + " ---");
        Book temp = head;
        while (temp != null) {
            if (temp.s.equalsIgnoreCase(sub)) {
                System.out.println(temp);
                f = true;
            }
            temp = temp.next;
        }
        if (!f) {
            System.out.println("No books found for this subject.");
        }
    }
}
