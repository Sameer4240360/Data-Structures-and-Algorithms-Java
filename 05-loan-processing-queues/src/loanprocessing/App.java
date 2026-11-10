package loanprocessing;

/** A loan application. */
class App {
    String n;    // applicant name
    String ad;   // address
    String c;    // contact
    char g;      // gender (M/F)
    int amt;     // loan amount

    public App(String n, String ad, String c, char g, int amt) {
        this.n = n;
        this.ad = ad;
        this.c = c;
        this.g = g;
        this.amt = amt;
    }

    public String toString() {
        return "Name: " + n + " | Gen: " + g + " | Amount: " + amt;
    }
}
