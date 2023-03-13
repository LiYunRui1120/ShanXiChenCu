package com.example.springbootdemo1.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class SplitFileTest {
    private static long count = 0L;

    public static void main(String[] args) {
        String file = "D:\\tool\\2022\\1117\\encoder\\encoder\\file\\1671587168673\\0000\\2022\\123.pdf";
        test(file);
    }
    public static void test(String file) {
        InputStream is = null;
        Long size = 3758096384L;
        long size2 = size.longValue();
        int fileSize = size.intValue();
//        is = new FileInputStream(file);


        File file1 = new File(file);
        byte l = (byte) file1.length();
        System.out.println("zzzzzzzzzzz"+l);
        count = count+l;
        System.out.println("count===="+count);

    }

}
