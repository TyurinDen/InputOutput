package gb.level3.lesson3;

import java.io.*;
import java.util.*;

public class J3Lesson3_HW_3 {
    public static void main(String[] args) {
        final int SYMBOLS_PER_PAGE = 1800;
        int pageNumber;
        char inputCharArr[] = new char[SYMBOLS_PER_PAGE];
        File file = new File("book1.txt");
        Scanner scanner = new Scanner(System.in);
        boolean b = false;
        System.out.println("Enter page number >> ");
        do {
            try {
                pageNumber = scanner.nextInt();
                b = true;
            } catch (InputMismatchException e) {
                b = false;
                scanner.next();
                System.out.println("Enter an integer >> ");
                //e.printStackTrace();
            } finally {
                scanner.next();
            }
        } while (!b);
    } //main
} //class
