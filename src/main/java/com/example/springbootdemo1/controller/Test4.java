package com.example.springbootdemo1.controller;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Test4 {

    private static final int BUFFER_LENGTH = 512;

    public static void main(String[] args) {
        /*int i = 1073741824;
        int i2 = (int) 3.5;
        int i3 = i*i2;
        createBigFile(i3);*/
        /*long size = getFileSize2("D:\\tool\\2022\\1117\\encoder\\encoder\\date\\file\\1669864876912\\0000\\2022");
        System.out.println("size===="+size);*/
        deleteDir("D:\\测试\\cdreader\\data\\file");

//        readFromFile("D:\\tool\\2022\\1117\\encoder\\encoder\\date\\file\\1669864876912\\0000\\2022\\123.pdf");

    }

    public static void readFromFile(String filePath){
        StringBuilder sb=new StringBuilder();
        InputStream in=null;
        try{
            in=new FileInputStream(filePath);
            byte buffer[]=new byte[BUFFER_LENGTH];
            while(in.read(buffer,0,BUFFER_LENGTH)!=-1){//-1表示读取结束
                sb.append(new String(buffer));
                System.out.println("zazazazaza::============"+new String(buffer));
//                long l = 3758096384L;
                long l = 102400;
                FileInputStream fis= new FileInputStream(sb.toString());
                FileChannel fc = fis.getChannel();
                if (fc.size()<= l ){
                    System.out.println("已超出100KB");
                }
            }
//            System.out.println("全文："+sb.toString());
        }catch(Exception ex){
            System.out.println(ex.toString());
        }finally{//不管是否出现异常，都要确保关闭以释放资源
            try{
                in.close();
            }catch(IOException ioEx){
                System.out.println(ioEx.toString());
            }
        }
    }

    public static long getFileSize2(String filePath) {
        long size = 0;
        String paths = "/0000/2022/123.pdf";
        FileInputStream fis= null;
        FileChannel fc= null;
        try {
            File f = new File(filePath);
            if (f.exists() && f.isFile()) {
                fis = new FileInputStream(f);
                fc = fis.getChannel();
                size = fc.size();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return size;
    }



    public static void  deleteDir(String filename) {
        File file = new File(filename);
        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for(String name : content){
            File temp = new File(file, name);
            if(temp.isDirectory()){//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            }else{
                if(!temp.delete()){//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }
    }


    public static void createBigFile(int length) { //1MB文件 length = 1024*1024 = 1048576
        File file = new File("D:\\tool\\2022\\1117\\encoder\\encoder\\date\\file1669771766690\\0000\\2022\\123.pdf");
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("create");
            }
            fileOutputStream = new FileOutputStream(file);
            fileChannel = fileOutputStream.getChannel();
            fileChannel.write(ByteBuffer.allocate(1), length - 1);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
