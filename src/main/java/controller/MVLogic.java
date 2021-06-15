package controller;

import java.util.Scanner;

public class MVLogic {

    public static void logicMainView() {
        for (; ; ) {
            printMainMenuView();
            String cmd = commandFromScanner();
            CommandHandler.commandSwitcher(cmd);
            if (cmd.equals("0")) {
                break;
            }
        }
    }

    private static void printMainMenuView() {
        System.out.println("\n[ Please, enter command: ]" +
                "\n[1] - show all users;" +
                "\t[2] - show all projects;" +
                "\t[3] - show all tasks;" +
                "\t[4] - show all subtasks;" +
                "\n[5] - create, edit, delete or assign user;" +
                "\t[6] - create, edit, delete or assign project;" +
                "\n[7] - create, edit, delete or assign task;" +
                "\t[8] - create, edit, delete or assign subtask;" +
                "\n[9] - show all assignments;" +
                "\t[10] - special report of all tasks by userId & projectId;" +
                "\n[11] - calculate time (tasks & subtasks) by task ID;" +
                "\t[12] - close all tasks & subtasks by task ID;" +
                "\n[0] - exit program]");
    }

    public static String commandFromScanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}