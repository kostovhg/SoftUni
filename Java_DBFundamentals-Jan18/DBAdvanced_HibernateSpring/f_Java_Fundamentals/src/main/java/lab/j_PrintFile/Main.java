package lab.j_PrintFile;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean isPrinted = false;
        Character ch = null;

        try {
            do {
                System.out.println("enter path to the file to print : ");
                String filePath = reader.readLine();
                try {
                    printFile(filePath);
                    isPrinted = true;
                } catch (FileNotFoundException fnfe) {
                    System.out.println("File with name \"" + filePath + "\" is not found !");
                    System.out.println("Do you want to try again?(y or n) ");
                    ch = reader.readLine().toLowerCase().trim().charAt(0);
                }
            } while (!isPrinted && ch == 'y');
        } finally {
            reader.close();
        }

        if (isPrinted) {
            System.out.println();
            System.out.println("File was printed successfully");
        }
    }

    public static void printFile(String fileName) throws FileNotFoundException {
        File sourceFile = new File(fileName);
        Scanner scr = new Scanner(sourceFile);
        String line = "";
        try {
            while (scr.hasNext()) {
                line = scr.nextLine();
                System.out.println(line);
            }
        } finally {
            scr.close();
        }
    }
}