package libraryarray;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Functions fObj = new Functions(100);

        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT (ARRAY) ===");
            System.out.println("1. Insert Book at Front");
            System.out.println("2. Insert Book at End");
            System.out.println("3. Delete Book from Front");
            System.out.println("4. Delete Book from End");
            System.out.println("5. Undo Action");
            System.out.println("6. Redo Action");
            System.out.println("7. Display All Books");
            System.out.println("8. Book with Max Copies");
            System.out.println("9. Search by Subject");
            System.out.println("0. Exit");
            System.out.print("Choice:");

            int ch = InputUtil.readInt(sc);

            if (ch == 0) {
                System.out.print("Program Ended:");
                break;
            }
            switch (ch) {
                case 1:
                case 2:
                    System.out.print("ID:"); int bId = InputUtil.readInt(sc);
                    System.out.print("Title:"); String t = sc.nextLine();
                    System.out.print("Edition:"); int e = InputUtil.readInt(sc);
                    System.out.print("Subject:"); String s = sc.nextLine();
                    System.out.print("Price:"); double p = InputUtil.readDouble(sc);

                    Book newB = new Book(bId, p, t, e, s, 1);
                    if (ch == 1) fObj.insF(newB);
                    else fObj.insE(newB);
                    break;
                case 3: fObj.delF(); break;
                case 4: fObj.delE(); break;
                case 5: fObj.undo(); break;
                case 6: fObj.redo(); break;
                case 7: fObj.dispAll(); break;
                case 8: fObj.dispMaxC(); break;
                case 9:
                    System.out.print("Enter Subject to Search:");
                    fObj.searchS(sc.nextLine());
                    break;
                default: System.out.println("Invalid Choice. Please try again.");
            }
        }
    }
}
