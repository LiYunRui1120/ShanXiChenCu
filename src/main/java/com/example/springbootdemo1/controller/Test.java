package com.example.springbootdemo1.controller;


import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        String paths = "/0000/2022/123.pdf,/0000/2022/123.pdf,/SX00/2022/123.pdf,/SX00/2022/123.pdf";
        String url = "D:\\project\\USBExporter\\ue-server\\target\\classes\\cdreader\\date\\file";
        if (paths.indexOf(",") != 1) {
            int of = paths.indexOf(",");//第一个，出现的位数
            String str1 = paths.substring(0, of);//截取第一个，之前的数据
            System.out.println("str1====" + str1);
            int op = str1.lastIndexOf("/");//最后一个/出现的位数
            String name = str1.substring(op + 1, str1.length());//截取第一个/之后的数据
            System.out.println("name===" + name);
            String catalog = url + str1.substring(0, op);//拼接字符串
            System.out.println("catalog===" + catalog);

            File catalogPath = new File(catalog);
            File file = new File(catalog + File.separator + name);
            System.out.println("file====" + file);
            if (!catalogPath.exists() && !catalogPath.isDirectory()) {
                catalogPath.mkdirs();
            }
            System.out.println("length===="+paths.length());
            String s = paths.substring(of+1, paths.length());
            int i = s.indexOf("/");
            int len = 0;
            while ((len = s.indexOf("/")) != -1){

            }
            System.out.println("ssssssssssssssssssssss");
        }
            /*if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            InputStream in = new FileInputStream(file);
            byte[] buf = new byte[1024 * 8];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.close();
        }*/
    }
}
