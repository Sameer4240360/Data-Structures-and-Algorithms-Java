package bankqueuelinkedlist;

/**
 * Priority queue of bank customers stored in a singly linked list.
 * Priority order: VVIP, female VIP, female Normal, male VIP, male Normal.
 */
class Functions {
    Customer head;

    public Functions() {
        head = null;
    }

    int getPrio(Customer x) {
        if (x.t.equalsIgnoreCase("VVIP")) return 1;
        if ((x.g == 'F' || x.g == 'f') && x.t.equalsIgnoreCase("VIP")) return 2;
        if ((x.g == 'F' || x.g == 'f') && x.t.equalsIgnoreCase("Normal")) return 3;
        if ((x.g == 'M' || x.g == 'm') && x.t.equalsIgnoreCase("VIP")) return 4;
        return 5;
    }

    public void addCust(Customer newC) {
        int newP = getPrio(newC);

        if (head == null || newP < getPrio(head)) {
            newC.next = head;
            head = newC;
            System.out.println("Success: " + newC.n + " added to the queue.");
            return;
        }
        Customer temp = head;
        while (temp.next != null && getPrio(temp.next) <= newP) {
            temp = temp.next;
        }

        newC.next = temp.next;
        temp.next = newC;
        System.out.println("Success: " + newC.n + " added to the queue.");
    }

    public void serve() {
        if (head == null) {
            System.out.println("Queue is empty:");
            return;
        }
        System.out.println("Served and Removed: " + head.n);
        head = head.next;
    }

    public void dispAll() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("\n--- Current Queue ---");
        Customer temp = head;
        int i = 1;
        while (temp != null) {
            System.out.println(i + ". " + temp);
            temp = temp.next;
            i++;
        }
    }

    public void dispEldest() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }
        Customer max = head;
        Customer temp = head.next;
        while (temp != null) {
            if (temp.a > max.a) max = temp;
            temp = temp.next;
        }
        System.out.println("Eldest Customer: " + max);
    }

    public void dispYoungF() {
        Customer min = null;
        Customer temp = head;
        while (temp != null) {
            if (temp.g == 'F' || temp.g == 'f') {
                if (min == null || temp.a < min.a) min = temp;
            }
            temp = temp.next;
        }
        if (min != null) {
            System.out.println("Youngest Female: " + min);
        } else {
            System.out.println("No female customer found:");
        }
    }

    public void dispFVVIP() {
        System.out.println("\n--- Female VVIPs ---");
        Customer temp = head;
        while (temp != null) {
            if ((temp.g == 'F' || temp.g == 'f') && temp.t.equalsIgnoreCase("VVIP"))
                System.out.println(temp);
            temp = temp.next;
        }
    }

    public void dispFNorm() {
        System.out.println("\n--- Female Normals ---");
        Customer temp = head;
        while (temp != null) {
            if ((temp.g == 'F' || temp.g == 'f') && temp.t.equalsIgnoreCase("Normal"))
                System.out.println(temp);
            temp = temp.next;
        }
    }

    public void dispEldVIP() {
        Customer max = null;
        Customer temp = head;
        while (temp != null) {
            if (temp.t.equalsIgnoreCase("VIP")) {
                if (max == null || temp.a > max.a) max = temp;
            }
            temp = temp.next;
        }
        if (max != null) {
            System.out.println("Eldest VIP: " + max);
        } else {
            System.out.println("No VIP customer found:");
        }
    }

    public void search(String sn) {
        Customer temp = head;
        while (temp != null) {
            if (temp.n.equalsIgnoreCase(sn)) {
                System.out.println("Found: " + temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Customer named " + sn + " not found.");
    }

    public void avgVVIP() {
        int sum = 0, count = 0;
        Customer temp = head;
        while (temp != null) {
            if (temp.t.equalsIgnoreCase("VVIP")) {
                sum += temp.a;
                count++;
            }
            temp = temp.next;
        }
        if (count > 0) {
            System.out.println("Average VVIP Age: " + String.format("%.1f", (double) sum / count));
        } else {
            System.out.println("No VVIP customer data available to calculate average:");
        }
    }
}
