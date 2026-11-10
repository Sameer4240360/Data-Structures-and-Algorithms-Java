package bankqueuelinkedlist;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Functions fObj = new Functions();

        while (true) {
            System.out.println("\n=== ABC BANK MENU (LINKED LIST) ===");
            System.out.println("1. Add Customer\t\t6. Female VVIPs");
            System.out.println("2. Serve Customer\t7. Female Normals");
            System.out.println("3. Display All\t\t8. Eldest VIP");
            System.out.println("4. Eldest Customer\t9. Search by Name");
            System.out.println("5. Youngest Female\t10. Avg Age of VVIPs");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int ch = InputUtil.readInt(sc);

            if (ch == 0) break;

            switch (ch) {
                case 1:
                    System.out.print("ID: "); int id = InputUtil.readInt(sc);
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Age: "); int a = InputUtil.readInt(sc);
                    System.out.print("Gender (M/F): "); String gs = sc.nextLine().trim();
                    if (gs.length() != 1 || "MmFf".indexOf(gs.charAt(0)) < 0) {
                        System.out.println("Invalid gender. Please enter M or F.");
                        break;
                    }
                    char g = gs.charAt(0);
                    System.out.print("Type (Normal/VIP/VVIP): "); String t = sc.nextLine().trim();
                    if (!(t.equalsIgnoreCase("Normal") || t.equalsIgnoreCase("VIP") || t.equalsIgnoreCase("VVIP"))) {
                        System.out.println("Invalid type. Please enter Normal, VIP or VVIP.");
                        break;
                    }

                    fObj.addCust(new Customer(id, n, a, g, t));
                    break;
                case 2: fObj.serve(); break;
                case 3: fObj.dispAll(); break;
                case 4: fObj.dispEldest(); break;
                case 5: fObj.dispYoungF(); break;
                case 6: fObj.dispFVVIP(); break;
                case 7: fObj.dispFNorm(); break;
                case 8: fObj.dispEldVIP(); break;
                case 9:
                    System.out.print("Enter Name to Search: ");
                    fObj.search(sc.nextLine());
                    break;
                case 10: fObj.avgVVIP(); break;
                default: System.out.println("Invalid Choice:");
            }
        }
    }
}
