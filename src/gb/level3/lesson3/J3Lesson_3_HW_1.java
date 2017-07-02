package gb.level3.lesson3;

import java.io.*;

public class J3Lesson_3_HW_1 {

    public static void main(String[] args) throws IOException {
        File someFile = new File("1.txt");
        char cArr[] = new char[40];
        int byteCounter;
        String outStr;

        if (!someFile.exists()) {
            System.out.println("ERROR: File not found!");
            return;
        }
        System.out.println(someFile.getCanonicalPath());
        try (BufferedReader br = new BufferedReader(new FileReader(someFile))) {
            byteCounter = br.read(cArr);
            while (byteCounter != -1) {
                outStr = String.valueOf(cArr, 0, byteCounter);
                System.out.print(outStr);
                byteCounter = br.read(cArr); //@TODO или лучше печатать побайтово как сдеално ниже?
//                for (int i = 0; i < byteCounter; i++) {
//                    System.out.print(cArr[i]);
//                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File is missing or HDD error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}