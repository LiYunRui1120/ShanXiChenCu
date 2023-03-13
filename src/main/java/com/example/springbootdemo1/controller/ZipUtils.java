package com.example.springbootdemo1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtils {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\tool\\2022\\1117\\新建文本文档.txt");
        String zipFile = "D:\\tool\\2022\\1117\\新建文本文档.txt";
        String srcFile = "D:\\tool\\2022\\1117\\test.zip";
        doCompress(zipFile,srcFile);
    }

    public static void doCompress(String srcFile, String zipFile) throws Exception {
        doCompress(new File(srcFile), new File(zipFile));
    }

    /**
     * 文件压缩
     * @param srcFile  目录或者单个文件
     * @param destFile 压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File destFile) throws Exception {
        System.out.println("srcFile===="+srcFile);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));

        if(srcFile.isDirectory()){
            File[] files = srcFile.listFiles();
            for(File file : files){
                doCompress(file, out);
            }
        }else {
            doCompress(srcFile, out);
        }
    }
    public static void doCompress1(List<String> pathnames, ZipOutputStream out) throws IOException{
        for(String path:pathnames){
            doCompress(new File(path), out);
        }
    }

    public static void doCompress(String pathname, ZipOutputStream out) throws IOException{
        doCompress(new File(pathname), out);
    }

    public static void doCompress(File file, ZipOutputStream out) throws IOException{
        if( file.exists() ){
            byte[] buffer = new byte[1024];
//            String ss = DESDemo2.encrypt("jiamimima","D:\\tool\\2022\\1117\\新建文本文档.txt");
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fous = new FileOutputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            System.out.println("name===="+file.getName());
            int len = fis.read(buffer);
            String str = out.toString();
            byte[] data = str.getBytes();
            // 读取文件的内容,打包到zip文件
            out.write(data, 0, len);
            System.out.println("out===="+out);
            /*while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
                System.out.println("out===="+out);
            }*/
            out.flush();
            out.closeEntry();
            fis.close();
        }
    }

}
