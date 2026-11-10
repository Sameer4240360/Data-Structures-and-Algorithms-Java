package bankqueuearray;

/**
 * Priority queue of bank customers stored in an array.
 * Priority order: VVIP, female VIP, female Normal, male VIP, male Normal.
 */
class Functions {
    Customer[] arr;
    int c;       // current number of customers
    int limit;   // array capacity

    public Functions(int size) {
        limit = size;
        arr = new Customer[limit];
        c = 0;
    }

    int getPrio(Customer x) {
        if (x.t.equalsIgnoreCase("VVIP")) return 1;
        if ((x.g == 'F' || x.g == 'f') && x.t.equalsIgnoreCase("VIP")) return 2;
        if ((x.g == 'F' || x.g == 'f') && x.t.equalsIgnoreCase("Normal")) return 3;
        if ((x.g == 'M' || x.g == 'm') && x.t.equalsIgnoreCase("VIP")) return 4;
        return 5;
    }

    public void addCust(Customer newC) {
        if (c == limit) {
            System.out.println("Array Full:");
            return;
        }

        int pos = c;
        int newPrio = getPrio(newC);
        for (int i = 0; i < c; i++) {
            if (newPrio < getPrio(arr[i])) {
                pos = i;
                break;
            }
        }
        for (int i = c; i > pos; i--) {
            arr[i] = arr[i - 1];
        }

        arr[pos] = newC;
        c++;
        System.out.println(newC.n + " added successfully!");
    }

    public void dispAll() {
        if (c == 0) { System.out.println("Empty:"); return; }
        System.out.println("\n--- Current Queue ---");
        for (int i = 0; i < c; i++) System.out.println((i + 1) + ". " + arr[i]);
    }

    public void serve() {
        if (c == 0) { System.out.println("Line Empty:"); return; }
        System.out.println("Removed:" + arr[0].n);
        for (int i = 0; i < c - 1; i++) arr[i] = arr[i + 1];
        arr[c - 1] = null;
        c--;
    }

    public void dispYoung() {
        if (c == 0) { System.out.println("Empty:"); return; }
        Customer min = arr[0];
        for (int i = 1; i < c; i++) {
            if (arr[i].a < min.a) min = arr[i];
        }
        System.out.println("Youngest:" + min);
    }

    public void dispEldF() {
        Customer max = null;
        for (int i = 0; i < c; i++) {
            if (arr[i].g == 'F' || arr[i].g == 'f') {
                if (max == null || arr[i].a > max.a) max = arr[i];
            }
        }
        if (max != null) System.out.println("Eldest Female:" + max);
        else System.out.println("No Female:");
    }

    public void dispMVVIP() {
        System.out.println("\n--- Male VVIPs ---");
        for (int i = 0; i < c; i++) {
            if ((arr[i].g == 'M' || arr[i].g == 'm') && arr[i].t.equalsIgnoreCase("VVIP"))
                System.out.println(arr[i]);
        }
    }

    public void dispFNorm() {
        System.out.println("\n--- Female Normals ---");
        for (int i = 0; i < c; i++) {
            if ((arr[i].g == 'F' || arr[i].g == 'f') && arr[i].t.equalsIgnoreCase("Normal"))
                System.out.println(arr[i]);
        }
    }

    public void dispEldVIP() {
        Customer max = null;
        for (int i = 0; i < c; i++) {
            if (arr[i].t.equalsIgnoreCase("VIP")) {
                if (max == null || arr[i].a > max.a) max = arr[i];
            }
        }
        if (max != null) System.out.println("Eldest VIP: " + max);
        else System.out.println("No VIP:");
    }

    public void search(String sn) {
        for (int i = 0; i < c; i++) {
            if (arr[i].n.equalsIgnoreCase(sn)) {
                System.out.println("Found:" + arr[i]);
                return;
            }
        }
        System.out.println(sn + " Not Found:");
    }

    public void avgVVIP() {
        int sum = 0, count = 0;
        for (int i = 0; i < c; i++) {
            if (arr[i].t.equalsIgnoreCase("VVIP")) {
                sum += arr[i].a;
                count++;
            }
        }
        if (count > 0) System.out.println("Average VVIP Age:" + String.format("%.1f", (double) sum / count));
        else System.out.println("No VVIP:");
    }
}
