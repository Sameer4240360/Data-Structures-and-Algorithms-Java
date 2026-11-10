package librarylinkedlist;

/** A library book (linked-list node). Books are identified by title and edition; extra copies are counted in nc. */
class Book {
    int bId;
    double p;   // price
    String t;   // title
    int e;      // edition
    String s;   // subject
    int nc;     // number of copies
    Book next;

    public Book(int bId, double p, String t, int e, String s, int nc) {
        this.bId = bId;
        this.p = p;
        this.t = t;
        this.e = e;
        this.s = s;
        this.nc = nc;
        this.next = null;
    }

    public String toString() {
        return "ID: " + bId + " | Title: " + t + " | Edition: " + e + " | Subject: " + s + " | Price: " + p + " | Copies: " + nc;
    }
}
