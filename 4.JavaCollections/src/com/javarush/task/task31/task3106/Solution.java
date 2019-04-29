package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;


/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];

        int partsCount = args.length - 1;
        String[] fileNamePart = new String[partsCount];

        for (int i = 0; i < partsCount; i++) {
            fileNamePart[i] = args[i + 1];
        }

        Arrays.sort(fileNamePart);

        List<FileInputStream> fisList = new ArrayList<>();

        for (int i = 0; i < partsCount; i++) {
            fisList.add(new FileInputStream(fileNamePart[i]));
        }

        SequenceInputStream SIS = new SequenceInputStream(Collections.enumeration(fisList));
        ZipInputStream zipInStream = new ZipInputStream(SIS);
        FileOutputStream fileOutStream = new FileOutputStream(resultFileName);
        byte[] buf = new byte[1024 * 1024];
        while (zipInStream.getNextEntry() != null) {
            int count;
            while ((count = zipInStream.read(buf)) != -1) {
                fileOutStream.write(buf, 0, count);
            }
        }

        SIS.close();
        zipInStream.close();
        fileOutStream.flush();
        fileOutStream.close();

    }
}
