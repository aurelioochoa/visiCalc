import java.util.Scanner;

public class SpreadsheetApp {
    private static final int NUM_ROWS = 15;
    private static final int NUM_COLUMNS = 10;

    public static void main(String[] args) {
        String[][] spreadsheet = new String[NUM_ROWS][NUM_COLUMNS];
        final int[] limits = {0, spreadsheet.length - 1, 0, spreadsheet[0].length - 1};
        Scanner input = new Scanner(System.in);
        int[] cursorPosition = {0, 0};
        boolean endAppLoop = false;

        do {
            printSpreadsheet(spreadsheet, cursorPosition);
            endAppLoop = askUserInput(spreadsheet, cursorPosition, input, limits);
        } while (!endAppLoop);

        input.close();
    }

    static void printAsset(int assetID) {
        String asset = "[____]";
        System.out.print(asset);
    }

    static void printHeader() {
        System.out.println("+" + "-".repeat(82) + "+");
        System.out.print("|  |");
        for (char letter = 'A'; letter < 'A' + NUM_COLUMNS; letter++) {
            System.out.print("   " + letter + "   |");
        }
        System.out.println("\n+" + "-".repeat(82) + "+");
    }

    static void printRowNumber(int row) {
        int sheetRow = row + 1;
        String wall = (String.valueOf(sheetRow).length() < 2) ? "| " : "|";
        if (row == 9) {
            System.out.print("|" + sheetRow + "|");
        } else {
            System.out.print(wall + sheetRow + "|");
        }
    }

    static String formatCell(String cellData) {
        if (cellData == null) {
            return "      ";
        } else if (cellData.length() < 6) {
            return " ".repeat(6 - cellData.length()) + cellData;
        } else {
            return cellData.substring(0, 6);
        }
    }

    static void printSpreadsheet(String[][] spreadsheet, int[] cursorPosition) {
        printHeader();

        for (int row = 0; row < spreadsheet.length; row++) {
            printRowNumber(row);

            for (int column = 0; column < spreadsheet[row].length; column++) {
                String data = spreadsheet[row][column];

                if (row == cursorPosition[0] && column == cursorPosition[1]) {
                    System.out.print("[" + formatCell(data) + "]");
                } else {
                    System.out.print(" " + formatCell(data) + " ");
                }
            }

            System.out.print("|");
            System.out.println();
        }

        System.out.println("+" + "-".repeat(82) + "+");
    }

    static boolean isValidCommand(char command) {
        return 
        command == 'i' ||
        command == 's' ||
        command == 'w' ||
        command == 'a' ||
        command == 'd' ||
        command == 'f';
    }

    static boolean askUserInput(String[][] spreadsheet, int[] cursorPosition, Scanner input, int[] limits) {
        boolean endAppLoop = false;

        String userInput = input.nextLine();
        
        char command;
        
        do {
            command = userInput.length() > 0 ? userInput.charAt(0) : ' ';
        } while (!isValidCommand(command));

        switch (command) {
            case 'i':
                System.out.print("Introduce el dato: ");
                String data = input.nextLine();
                spreadsheet[cursorPosition[0]][cursorPosition[1]] = data;
                break;
            case 's':
                cursorPosition[0] = Math.min(cursorPosition[0] + 1, limits[1]);
                break;
            case 'w':
                cursorPosition[0] = Math.max(cursorPosition[0] - 1, limits[0]);
                break;
            case 'a':
                cursorPosition[1] = Math.max(cursorPosition[1] - 1, limits[2]);
                break;
            case 'd':
                cursorPosition[1] = Math.min(cursorPosition[1] + 1, limits[3]);
                break;
            case 'f':
                endAppLoop = true;
                break;
            default:
                System.out.println("Comando incorrecto!");
                break;
        }
        return endAppLoop;
    }
}
