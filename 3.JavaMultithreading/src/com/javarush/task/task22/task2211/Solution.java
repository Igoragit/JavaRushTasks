package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {


        FileInputStream inputStream1 = new FileInputStream(args[0]);
        FileOutputStream outputStream = new FileOutputStream(args[1]);


        Charset set1 = Charset.forName("UTF-8");
        Charset set2 = Charset.forName("Windows-1251");


        byte[] buf = new byte[inputStream1.available()];
        inputStream1.read(buf);

        String s = new String(buf,"UTF-8");
        buf = s.getBytes("Windows-1251");
        outputStream.write(buf);

    }
}
