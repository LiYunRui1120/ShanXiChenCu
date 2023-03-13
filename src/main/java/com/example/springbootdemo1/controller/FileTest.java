package com.example.springbootdemo1.controller;

import java.io.*;

public class FileTest {

    public static void main(String[] args) throws IOException {
//        operateFile1();
        operateFile2();
//        operateFile3();
    }
    public static void operateFile1() {
        File f = new File("D:\\project\\USBExporter\\ue-server\\target\\classes\\cdreader\\data\\archser.db");
        try {
            //创建一个流对象
            InputStream in = new FileInputStream(f);
            //读取数据，并将读取的数据存储到数组中
            byte[] b = new byte[(int) f.length()];//数据存储的数组
            int len = 0;
            int temp = 0;
            while((temp = in.read()) != -1){//循环读取数据，未到达流的末尾
                b[len] = (byte) temp;//将有效数据存储在数组中
                len ++;
            }
            System.out.println("b===="+b);
            System.out.println("数据===="+new String(b, 0, len));
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void operateFile2() {
        File f = new File("D:\\tool\\2022\\1117\\新建文本文档.txt");
        try {
            InputStream in = new FileInputStream(f);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                String str = new String(b, 0, len);
                long l = str.length();
                System.out.println("000000000000000000=="+l);
//                System.out.println("数据===="+new String(b, 0, len));
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void operateFile3() throws IOException {
        File file = new File("D:\\tool\\2022\\1117\\新建文本文档.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String str = "saftehrdbberg34g32g6";
        System.out.println("fileOutputStream==="+fileOutputStream);
        for (int i = 0; i < str.length(); i++){
            int b = (int) str.charAt(i);
            fileOutputStream.write(b);
        }
        fileOutputStream.close();
    }


}
