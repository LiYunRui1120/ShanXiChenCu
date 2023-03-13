package com.example.springbootdemo1.controller;

import java.io.File;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        String paths = "/0000/2022/123.pdf,/0000/2022/456.pdf,/SX00/2022/123.pdf,/SX00/2022/456.pdf";
        String url = "D:\\tool\\2022\\1117\\encoder\\encoder\\date\\file";
        String str2 = testqa(paths,url);


    }
    public static String testqa(String path,String url) throws IOException {
        String[] str = path.split(",");
        int i = 0;
        while (i<str.length){
            System.out.println("str===="+str[i]);
            String s = str[i];
            int of = s.lastIndexOf("/");
            String catalog = s.substring(0, of);//截取文件夹目录
            String name = s.substring(of, s.length());//截取文件名称

            File catalogPath = new File(url+catalog);
            File file = new File(catalogPath + File.separator + name);
            // 创建文件夹
            if (!catalogPath.exists() && !catalogPath.isDirectory()) {
                catalogPath.mkdirs();
            }
            // 创建文件
            if (!file.exists()) {
                file.createNewFile();
            }
            i++;
        }
        return null;
    }
}
