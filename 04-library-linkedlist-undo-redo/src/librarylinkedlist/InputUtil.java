package librarylinkedlist;

import java.util.Scanner;

/**
 * Small helper for safe console input. Reads a whole line and keeps asking
 * until the user types a valid number, so the program does not crash on bad input.
 */
final class InputUtil {

    private InputUtil() {
    }

    static int readInt(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }

    static double readDouble(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }
}
