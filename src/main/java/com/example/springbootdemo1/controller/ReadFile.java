package com.example.springbootdemo1.controller;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        String path = "D:\\project\\USBExporter\\ue-server\\target\\classes\\excel\\压缩文件1669107504159.zip";
        ZipFile zf = new ZipFile(path);
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        Charset gbk = Charset.forName("gbk");
        ZipInputStream zin = new ZipInputStream(in,gbk);
        ZipEntry ze;
        while((ze = zin.getNextEntry()) != null){
            if(ze.toString().endsWith("txt")){
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(zf.getInputStream(ze)));
                String line;
                while((line = br.readLine()) != null){
                    System.out.println("line===="+line.toString());
                }
                br.close();
            }
            System.out.println();
        }
        zin.closeEntry();
    }
}
