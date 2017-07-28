package gb.level3.lesson3.task3;

import java.io.*;
import java.util.*;

public class J3Lesson3_HW_3 {
    static private final String CHARSET[] = new String[] {"WINDOWS-1251", "IBM866", "KOI8-R", "UTF-8", "UTF-16"};
    static private final int SYMBOLS_PER_PAGE = 1800;

    private static void printPageFromFile(File f, byte[] byteArr, int pageNumber, String charSet) {
        String outputStr;

        if (f == null) {
            System.out.println("The file is NULL");
            return;
        }
        //TODO Надо ли проверять все входящие параметры на NULL? byteArr?
        System.out.println("=================== " + f.getName() + " ===================");
        System.out.println("PAGE: " + pageNumber);
        System.out.println("CODEPAGE: " + charSet + "\n");
        try {
            outputStr = new String(byteArr, charSet);
            System.out.println(outputStr);
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR: There are some problems with the output string now ...");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int pageNumber, swCase = 0, charSetIndex = 0;
        byte inputByteArr[] = new byte[SYMBOLS_PER_PAGE];
        boolean whileExitCond = false;
        String userAnswer;
        File inFile = new File("book1.txt");
        Scanner scanner = new Scanner(System.in);

        if (!inFile.canRead()) {
            System.out.println("ERROR: Can't read this file...");
            return;
        }

        System.out.println("The number of pages in the file \"" + inFile.getName() + "\" is "
                + (inFile.length() / SYMBOLS_PER_PAGE));
        System.out.println("Enter the page number you want to open (pages start from zero) >> ");

        userAnswer = scanner.next();
        //"^[0-9]{1,7}"
        while (!userAnswer.matches("^0?[1-9]{1}[0-9]{0,6}")) {
            scanner.nextLine();
            System.out.print("Invalid page number! Enter the page number >>\n");
            userAnswer = scanner.next();
        }
        pageNumber = Integer.parseInt(userAnswer);

        if ((pageNumber * SYMBOLS_PER_PAGE) > inFile.length()) {
            System.out.println("There is no such page in this file");
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

        printPageFromFile(inFile, inputByteArr, pageNumber, CHARSET[charSetIndex]);

        do {
            System.out.println("\n Is the text displayed correctly? (y/n/q)");
            userAnswer = scanner.next();
            while (!userAnswer.matches("[y,n,q,Y,N,Q]{1}")) {
                scanner.nextLine();
                userAnswer = scanner.next();
            }
            switch (userAnswer) {
                case "Y":
                case "y":
                    whileExitCond = true;
                    break;
                case "N":
                case "n":
                    whileExitCond = false;
                    charSetIndex++;
                    if (charSetIndex > 4) charSetIndex = 0;
                    printPageFromFile(inFile, inputByteArr, pageNumber, CHARSET[charSetIndex]);
                    break;
                case "Q":
                case "q":
                    whileExitCond = true;
                    break;
            }
        } while (!whileExitCond);
        System.out.println("\nBYE!");
    } //main
} //class
