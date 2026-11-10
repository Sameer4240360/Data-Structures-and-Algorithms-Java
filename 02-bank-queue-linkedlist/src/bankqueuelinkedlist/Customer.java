package bankqueuelinkedlist;

/**
 * A bank customer (linked-list node). A "Normal" customer is automatically
 * upgraded to VIP or VVIP depending on gender and age.
 */
class Customer {
    int id;
    String n;   // name
    int a;      // age
    char g;     // gender (M/F)
    String t;   // type (Normal / VIP / VVIP)
    Customer next;

    public Customer(int id, String n, int a, char g, String t) {
        this.id = id;
        this.n = n;
        this.a = a;
        this.g = g;
        this.t = t;
        this.next = null;

        if (this.t.equalsIgnoreCase("Normal")) {
            if (this.g == 'M' || this.g == 'm') {
                if (this.a >= 60 && this.a <= 70) this.t = "VIP";
                else if (this.a > 70) this.t = "VVIP";
            }
            else if (this.g == 'F' || this.g == 'f') {
                if (this.a >= 50 && this.a <= 60) this.t = "VIP";
                else if (this.a > 60) this.t = "VVIP";
            }
        }
    }

    public String toString() {
        return "ID: " + id + " | Name: " + n + " | Age: " + a + " | Gender: " + g + " | Type: " + t;
    }
}
