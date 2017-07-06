package gb.level3.lesson3.task3;

import java.util.Scanner;

public class MyParser {
    public static void main(String[] args) {
        int i = 0;
        byte byteArr[] = new byte[20];
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        do {
            scanner.next();
        } while (!scanner.hasNextInt());
        i = scanner.nextInt();
        System.out.println(i);
        //System.out.println(scanner.next("([y,n,q,Y,N,Q]{1})"));

    }//main
}//class
