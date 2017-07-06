package gb.level3.lesson3;

import java.io.*;
import java.util.*;

public class J3Lesson3_HW_3 {

    public static void main(String[] args) {
        String charset[] = new String[] {"WINDOWS-1251", "IBM866", "KOI8-R", "UTF-8", "UTF-16"};
        final int SYMBOLS_PER_PAGE = 1800;
        int pageNumber = 0, switchCase1 = 0, index1 = 0;
        byte inputByteArr[] = new byte[SYMBOLS_PER_PAGE];
        boolean whileExitCond;
        String outputStr = "", userAnswer = "";
        File inFile = new File("book1.txt");
        Scanner scanner = new Scanner(System.in);

        if (!inFile.canRead()) {
            System.out.println("ERROR: Can't read this file...");
            return;
        }

        System.out.println("The number of pages in the file \"" + inFile.getName() + "\" is "
                + (inFile.length() / SYMBOLS_PER_PAGE));
        System.out.println("Enter the page number you want to open (pages start from zero) >> ");
        do {
            try {
                pageNumber = scanner.nextInt();
                whileExitCond = true;
            } catch (InputMismatchException e) {
                whileExitCond = false;
                scanner.nextLine();
                switch (switchCase1) {
                    case 0:
                        System.out.println("Enter the page number using numbers >> ");
                        switchCase1++;
                        break;
                    case 1:
                        System.out.println("Numbers please... >> ");
                        switchCase1++;
                        break;
                    case 2:
                        System.out.println(" O_o it seems like a long time... >> ");
                        switchCase1++;
                        break;
                    case 3:
                        System.out.println("The digits are '0' or '1' or '2' and so on up to '9'. Use them >> ");
                        switchCase1 = 0;
                        break;
                }
                //e.printStackTrace();
            }
        } while (!whileExitCond);

        if (pageNumber < 0 || (pageNumber * SYMBOLS_PER_PAGE) > inFile.length()) {
            System.out.println("There is no such page in this file...");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(inFile, "r")) {
            raf.seek(pageNumber * SYMBOLS_PER_PAGE);
            raf.read(inputByteArr);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: An error occurred while opening the input file!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERROR: The specified file is locked or missing or an HDD error!");
            e.printStackTrace();
        }

        System.out.println("=================== " + inFile.getName() + " ===================");
        System.out.println("PAGE: " + pageNumber);
        System.out.println("CODEPAGE: " + charset[index1] + "\n");

        try {
            outputStr = new String(inputByteArr, charset[index1]);
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR: There are some problems with the output string now ...");
            e.printStackTrace();
        }
        System.out.println(outputStr);

        do {
            System.out.println("\n Is the text displayed correctly? (y/n/q)");
            userAnswer = scanner.next().toLowerCase();
//            scanner.next();
            switch (userAnswer) {
                case "y":
                    whileExitCond = true;
                    break;
                case "n":
                    whileExitCond = false;
                    index1++;
                    if (index1 > 4) index1 = 0;
                    System.out.println("=================== " + inFile.getName() + " ===================");
                    System.out.println("PAGE: " + pageNumber);
                    System.out.println("CODEPAGE: " + charset[index1] + "\n");
                    try {
                        outputStr = new String(inputByteArr, charset[index1]);
                    } catch (UnsupportedEncodingException e) {
                        System.out.println("ERROR: There are some problems with the output string now ...");
                        e.printStackTrace();
                    }
                    System.out.println(outputStr);
                    break;
                case "q":
                    whileExitCond = true;
                    break;
                default:
                    System.out.println("\nOnly y/n/q please...");
                    whileExitCond = false;
                    scanner.nextLine();
            }
        } while (!whileExitCond);
        System.out.println("\nBYE!");
    } //main
} //class
