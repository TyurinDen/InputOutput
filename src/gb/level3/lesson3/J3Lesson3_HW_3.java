package gb.level3.lesson3;

import java.io.*;
import java.util.*;

public class J3Lesson3_HW_3 {

    public static void main(String[] args) {
        final int SYMBOLS_PER_PAGE = 1800;
        int pageNumber = 0, sw = 0;
        byte inputByteArr[] = new byte[SYMBOLS_PER_PAGE];
        String outputStr = "";
        File inFile = new File("book1.txt");
        Scanner scanner = new Scanner(System.in);
        boolean b;
        BufferedReader br = null;
        BufferedInputStream bis = null;
        RandomAccessFile raf = null;

        //TODO разобраться с нулевой страницей, оформить вывод = имя файла =
        if (!inFile.canRead()) {
            System.out.println("ERROR: Can't read this file...");
            return;
        }

        //TODO смысла использовать RAF не вижу. То же самое реализуется другими способами.
        //TODO и как его буфферизировать?
        try {
            raf = new RandomAccessFile(inFile, "r");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: An error occurred while opening the input file!");
            e.printStackTrace();
        }

        System.out.println("The number of pages in the file " + inFile.getName() + " is "
                + (inFile.length() / SYMBOLS_PER_PAGE));
        System.out.println("Enter the page number you want to open >> ");
        do {
            try {
                pageNumber = scanner.nextInt();
                b = true;
            } catch (InputMismatchException e) {
                b = false;
                scanner.nextLine();
                switch (sw) {
                    case 0:
                        System.out.println("Enter the page number using numbers >> ");
                        sw++;
                        break;
                    case 1:
                        System.out.println("Numbers please... >> ");
                        sw++;
                        break;
                    case 2:
                        System.out.println(" O_o it seems like a long time... >> ");
                        sw++;
                        break;
                    case 3:
                        System.out.println("The digits are '0' or '1' or '2' and so on up to '9'. Use them >> ");
                        sw = 0;
                        break;
                }
                //e.printStackTrace();
            }
        } while (!b);

        System.out.println(pageNumber);
        if ((pageNumber * SYMBOLS_PER_PAGE) > inFile.length()) {
            System.out.println("There is no such page in this file...");
            return;
        }

        try {
            br = new BufferedReader(new FileReader(inFile));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: The specified file is locked or missing or an HDD error!");
            e.printStackTrace();
        }

        try {
            raf.seek(pageNumber * SYMBOLS_PER_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            raf.read(inputByteArr);
        } catch (IOException e) {
            System.out.println("ERROR: Some problem occurred when reading the file...");
            e.printStackTrace();
        }
        try {
            outputStr = new String(inputByteArr, "WINDOWS-1251");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Damn it! There are some problems with the output string now ...");
            e.printStackTrace();
        }
        System.out.println(outputStr);
    } //main
} //class
