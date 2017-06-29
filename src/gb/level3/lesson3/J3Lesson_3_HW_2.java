package gb.level3.lesson3;

import java.io.*;
import java.util.*;

public class J3Lesson_3_HW_2 {

    public static void main(String[] args) {
        byte bArr[] = new byte[40];
        Set<File> fileListSet = new LinkedHashSet<>();
        Set<InputStream> inputStreamSet = new LinkedHashSet<>();
        SequenceInputStream seqInputStream;
        Enumeration<InputStream> inputStreamsEnum;

        File someDir = new File("SOMEDIR");
        File outputFile = new File(someDir, "result.txt");
        try {
            outputFile.createNewFile();
        } catch (IOException e1) {
            System.out.println("An error occurred while creating the output file!");
            e1.printStackTrace();
        }
        if (!someDir.isDirectory()) {
            System.out.println(someDir.getName() + " is not a folder or it does not exist."
                    + " Specify the correct folder name!");
            return;
        }
        if (someDir.list().length == 0) {
            System.out.println("The specified folder is empty!");
            return;
        }
        System.out.println(Arrays.deepToString(someDir.list()));
        for (File file: someDir.listFiles()) {
            if (file.isDirectory()) {
                System.out.println("DIR: " + file.getName());
            } else fileListSet.add(file);
        }
        System.out.println(fileListSet);
        Iterator<File> fileIterator = fileListSet.iterator();
        while (fileIterator.hasNext()) {
            try {
                inputStreamSet.add(new BufferedInputStream(new FileInputStream(fileIterator.next())));
            } catch (FileNotFoundException e) {
                System.out.println("Some files are locked or missing or an HDD error!");
                e.printStackTrace();
            }
        }
        inputStreamsEnum = Collections.enumeration(inputStreamSet);
        seqInputStream = new SequenceInputStream(inputStreamsEnum);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            int bytesRead = seqInputStream.read(bArr);
            while(bytesRead != -1) {
                try {
                    bos.write(bArr, 0, bytesRead);
                } catch (IOException e) {
                    System.out.println("An error occurred while writing the output file!");
                    e.printStackTrace();
                }
                bytesRead = seqInputStream.read(bArr);
            }
        } catch (IOException e) {
            System.out.println("Some problems with the output file or stream or an HDD error!");
            e.printStackTrace();
        }
        try {
            seqInputStream.close();
        } catch (IOException e) {
            System.out.println("An error occurred while closing the output file!");
            e.printStackTrace();
        }
        System.out.println("Operation complete!");
    }//main
} //class