package com.example.springbootdemo1.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadFile {

    public static void main(String[] args) {
        String photoUrl = "D:\\tool\\2022\\1117\\新建文本文档.txt";   //文件URL地址
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("\\"));     //为下载的文件命名
        String filePath = "D:\\tool\\2022\\test";      //保存目录
//        File file = saveUrlAs(photoUrl, filePath + fileName,"GET");

        String file = "D:\\tool\\2022\\1117";
        String path = getFileNames(file).toString();
        System.out.println("path==="+path);
    }


    public static File saveUrlAs(String url, String filePath, String method){
        //System.out.println("fileName---->"+filePath);
        //创建不同的文件夹目录
        File file=new File(filePath);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try
        {
            // 建立链接
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {
                filePath += "/";
            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath+"新建文本文档.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[1024 * 8];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }

        return file;

    }
    private static List<String> getFileNames(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        return getFileNames(fileNames.toString());
    }
}
