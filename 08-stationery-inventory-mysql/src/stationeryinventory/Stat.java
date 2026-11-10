package stationeryinventory;

/** One stationery item (linked-list node). */
class Stat {
    int id;
    String n;    // name
    double p;    // price
    int q;       // quantity
    Stat nxt;

    public Stat(int id, String n, double p, int q) {
        this.id = id;
        this.n = n;
        this.p = p;
        this.q = q;
        this.nxt = null;
    }

    public String toString() {
        return "[ID: " + id + "] " + n + " | Price: Rs" + p + " | Qty: " + q + " | Subtotal: Rs" + (p * q);
    }
}
