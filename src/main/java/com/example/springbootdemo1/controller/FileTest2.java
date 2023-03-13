package com.example.springbootdemo1.controller;

import java.io.*;
import java.util.concurrent.ThreadFactory;

public class FileTest2 {

    public static void main(String[] args) {
        operateFile1();
    }


    public static void operateFile1() {
        File f = new File("D:\\tool\\2022\\1117\\encoder\\encoder\\date\\file\\2022\\123.pdf");
        try {
            //创建一个流对象
            InputStream in = new FileInputStream(f);
            //读取数据，并将读取的数据存储到数组中
            System.out.println("length===="+f.length());
            byte[] b = new byte[(int) f.length()];//数据存储的数组
            int len = 0;
            int temp = 0;
            while((temp = in.read()) != -1){//循环读取数据，未到达流的末尾
                b[len] = (byte) temp;//将有效数据存储在数组中
                len ++;
            }
            System.out.println("数据===="+new String(b));
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
