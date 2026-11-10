package stationeryinventory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Check the database connection once at start-up
        try (Connection con = DBConnection.getConnection()) {
            System.out.println("Database Connected!");
        } catch (SQLException e) {
            System.out.println("Connection Failed:" + e.getMessage());
            System.out.println("Check that MySQL is running and that DB_URL, DB_USER and DB_PASSWORD are set (see README).");
            return;
        }

        Scanner sc = new Scanner(System.in);
        Functions fObj = new Functions();

        fObj.loadFromDB();
        while (true) {
            System.out.println("\n===  STATIONERY DS PROJECT ===");
            System.out.println("1. Add Item at Front");
            System.out.println("2. Add Item at End");
            System.out.println("3. Insert Item after specific ID");
            System.out.println("4. Delete Item from Front");
            System.out.println("5. Delete Item from End");
            System.out.println("6. Update Item Price/Qty");
            System.out.println("7. View All Items");
            System.out.println("8. Calculate Grand Total");
            System.out.println("0. Exit");
            System.out.print("Choice:");

            int ch = InputUtil.readInt(sc);
            if (ch == 0) {
                System.out.println("Project Exited Successfully:");
                break;
            }
            switch (ch) {
                case 1:
                    System.out.print("Enter Item ID:"); int i1 = InputUtil.readInt(sc);
                    System.out.print("Item Name:"); String n1 = sc.nextLine();
                    System.out.print("Price:"); double p1 = InputUtil.readDouble(sc);
                    System.out.print("Qty:"); int q1 = InputUtil.readInt(sc);
                    fObj.addF(i1, n1, p1, q1);
                    break;
                case 2:
                    System.out.print("Enter Item ID:"); int i2 = InputUtil.readInt(sc);
                    System.out.print("Item Name:"); String n2 = sc.nextLine();
                    System.out.print("Price:"); double p2 = InputUtil.readDouble(sc);
                    System.out.print("Qty:"); int q2 = InputUtil.readInt(sc);
                    fObj.addE(i2, n2, p2, q2);
                    break;
                case 3:
                    System.out.print("Target ID (Insert After):"); int tId = InputUtil.readInt(sc);
                    System.out.print("New Item ID:"); int i3 = InputUtil.readInt(sc);
                    System.out.print("Item Name:"); String n3 = sc.nextLine();
                    System.out.print("Price:"); double p3 = InputUtil.readDouble(sc);
                    System.out.print("Qty:"); int q3 = InputUtil.readInt(sc);
                    fObj.ins(tId, i3, n3, p3, q3);
                    break;
                case 4:
                    fObj.delF();
                    break;
                case 5:
                    fObj.delE();
                    break;
                case 6:
                    System.out.print("Enter ID to Update:"); int uId = InputUtil.readInt(sc);
                    System.out.print("New Price:"); double uP = InputUtil.readDouble(sc);
                    System.out.print("New Qty:"); int uQ = InputUtil.readInt(sc);
                    fObj.upd(uId, uP, uQ);
                    break;
                case 7:
                    fObj.disp();
                    break;
                case 8:
                    fObj.calcTot();
                    break;
                default:
                    System.out.println("Invalid Choice:");
            }
        }
    }
}
