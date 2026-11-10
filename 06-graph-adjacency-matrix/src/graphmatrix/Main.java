package graphmatrix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Functions fObj = new Functions(50);

        while (true) {
            System.out.println("\n=== TRANSPORTATION SYSTEM (GRAPH ARRAY) ===");
            System.out.println("1. Add Node (City)");
            System.out.println("2. Add Edge (Route)");
            System.out.println("3. Search Edges for a Node");
            System.out.println("4. Check Direct Edge Between Two Nodes");
            System.out.println("5. Delete Node");
            System.out.println("6. Delete Edge");
            System.out.println("7. Traverse using DFS");
            System.out.println("8. Traverse using BFS");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int ch = InputUtil.readInt(sc);

            if (ch == 0) {
                System.out.println("Exit:");
                break;
            }
            switch (ch) {
                case 1:
                    System.out.print("Enter Node/City Name:");
                    fObj.addN(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter First Node:"); String e1 = sc.nextLine();
                    System.out.print("Enter Second Node:"); String e2 = sc.nextLine();
                    fObj.addE(e1, e2);
                    break;
                case 3:
                    System.out.print("Enter Node Name:");
                    fObj.srchE(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter First Node:"); String c1 = sc.nextLine();
                    System.out.print("Enter Second Node:"); String c2 = sc.nextLine();
                    fObj.chkE(c1, c2);
                    break;
                case 5:
                    System.out.print("Enter Node Name to Delete:");
                    fObj.delN(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter First Node:"); String d1 = sc.nextLine();
                    System.out.print("Enter Second Node:"); String d2 = sc.nextLine();
                    fObj.delE(d1, d2);
                    break;
                case 7:
                    System.out.print("Enter Starting Node for DFS:");
                    fObj.dfs(sc.nextLine());
                    break;
                case 8:
                    System.out.print("Enter Starting Node for BFS:");
                    fObj.bfs(sc.nextLine());
                    break;
                default:
                    System.out.println("Invalid Choice:");
            }
        }
    }
}
