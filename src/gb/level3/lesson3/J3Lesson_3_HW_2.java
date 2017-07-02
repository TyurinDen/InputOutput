package gb.level3.lesson3;

import java.io.*;
import java.util.*;

public class J3Lesson_3_HW_2 {

    public static void main(String[] args) {
//        byte bArr[] = new byte[40];
        Set<File> fileListSet = new LinkedHashSet<>();
        Set<InputStream> inputStreamSet = new LinkedHashSet<>();
        SequenceInputStream seqInputStream;
        Enumeration<InputStream> inputStreamsEnum;
        File someDir = new File("SOMEDIR");
        File outputFile = new File(someDir, "result.txt");

        //@TODO Надо ли сообщения об ошибках заменить на константы?
        if (!someDir.exists()) {
            System.out.println("ERROR: The specified folder not found!");
            return;
        }

        try {
            outputFile.createNewFile();//@TODO War: Result of 'File.createNewFile()' is ignored
        } catch (IOException e) {
            System.out.println("ERROR: An error occurred while creating the output file!");
            e.printStackTrace();
        }

        if (!someDir.isDirectory()) {
            System.out.println("ERROR: " + someDir.getName() + " is not a folder."
                    + " Specify the correct folder name!");
            return;
        }

        if (someDir.list().length == 0) { //@TODO War: Dereference of 'someDir.list()' may produce 'java.lang.NullPointerException'
            System.out.println("ERROR: The specified folder is empty!");
            return;
        }

        System.out.println(Arrays.deepToString(someDir.list()));
        for (File file: someDir.listFiles()) { //@TODO War: Dereference of 'someDir.listFiles()' may produce 'java.lang.NullPointerException'
            if (file.isDirectory()) {
                System.out.println("DIR: " + file.getName());
            } else fileListSet.add(file);
        }

        System.out.println(fileListSet);
        for (File aFileListSet : fileListSet) { //DONE! //@TODO War: 'while' loop replaceable with 'foreach'
            try {
                inputStreamSet.add(new BufferedInputStream(new FileInputStream(aFileListSet)));
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: Some files are locked or missing or an HDD error!");
                e.printStackTrace();
            }
        }

        inputStreamsEnum = Collections.enumeration(inputStreamSet);
        seqInputStream = new SequenceInputStream(inputStreamsEnum);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile))) {
//            int bytesRead = seqInputStream.read(bArr);
            int bytesRead = seqInputStream.read();
            System.out.println(bytesRead);
            while(bytesRead != -1) {
                try {
                    bos.write(bytesRead);
                    //bos.write(bArr, 0, bytesRead);
                } catch (IOException e) {
                    System.out.println("ERROR: An error occurred while writing the output file!");
                    e.printStackTrace();
                }
                bytesRead = seqInputStream.read();
//                System.out.println(bytesRead);
            }
        } catch (IOException e) {
            System.out.println("ERROR: Some problems with the output file or stream or an HDD error!");
            e.printStackTrace();
        }

        try {
            seqInputStream.close();
        } catch (IOException e) {
            System.out.println("ERROR: An error occurred while closing the output file!");
            e.printStackTrace();
        }
        System.out.println("OK: Operation complete!");
    } //main
} //class