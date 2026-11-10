package loanprocessing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Functions fObj = new Functions(100);
        while (true) {
            System.out.println("\nXYZ BANK LOAN MENU:");
            System.out.println("1. Add new loan application");
            System.out.println("2. Show list of Applications");
            System.out.println("3. Accept or reject Application");
            System.out.println("4. Show Accepted List");
            System.out.println("5. Show Rejected List");
            System.out.println("6. Approve or Disapprove");
            System.out.println("7. Dispatch loan amount");
            System.out.println("8. Show Successful Receivers");
            System.out.println("0. Exit");
            System.out.print("Choice:");
            int ch = InputUtil.readInt(sc);
            if (ch == 0) {
                System.out.println("Program Ended:");
                break;
            }
            switch (ch) {
                case 1:
                    System.out.print("Name:"); String n = sc.nextLine();
                    System.out.print("Address:"); String ad = sc.nextLine();
                    System.out.print("Contact:"); String c = sc.nextLine();
                    System.out.print("Gender (M/F):"); String gs = sc.nextLine().trim();
                    if (gs.length() != 1 || "MmFf".indexOf(gs.charAt(0)) < 0) {
                        System.out.println("Invalid gender. Please enter M or F.");
                        break;
                    }
                    char g = gs.charAt(0);
                    System.out.print("Amount:"); int amt = InputUtil.readInt(sc);
                    fObj.addApp(new App(n, ad, c, g, amt));
                    break;
                case 2: fObj.dispApp(); break;
                case 3: fObj.accRej(sc); break;
                case 4: fObj.dispAcc(); break;
                case 5: fObj.dispRej(); break;
                case 6: fObj.appDis(sc); break;
                case 7: fObj.dispLoan(); break;
                case 8: fObj.dispSucc(); break;
                default: System.out.println("Invalid Choice:");
            }
        }
    }
}
