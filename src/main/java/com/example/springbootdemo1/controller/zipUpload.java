package com.example.springbootdemo1.controller;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;


//保存电子票据信息并返回文件数量


public class zipUpload {

    public static void main(String[] args) {

    }


    public static String zip(MultipartFile multipartfile) throws IOException {
        //获取文件名
        String originalFilename = multipartfile.getOriginalFilename();
        //校验文件类型
        if (!originalFilename.endsWith(".zip")) {
            return "请上传zip格式文件";
        }
        //由于zip4j只能通过磁盘文件读取，故需先将MultipartFile转存储File到磁盘上，使用结束后再进行删除
        //获取当前程序路径
        String realPath = System.getProperty("user.dir");
        System.out.println("当前路径：" + realPath);
        File file = new File(realPath + "/upload/" + originalFilename);
        file.getParentFile().mkdirs();
        multipartfile.transferTo(file);

        //读取zip包
        ZipFile zipFile = new ZipFile(realPath + "/upload/" + originalFilename);

        //zip包内文件数量
        String filtCount = "0";

        //对zip内文件进行相关处理
        filtCount = saveZipFile(file, zipFile);

        return filtCount;
    }

    private static String saveZipFile(File file1, ZipFile zipFile) throws IOException {
        System.out.println("处理zip中的文件");
        //有需要的话可先循环判断zip中文件类型
        ZipInputStream zipInputStream1 = new ZipInputStream(new FileInputStream(file1));
        LocalFileHeader localFileHeader1;
        while ((localFileHeader1 = zipInputStream1.getNextEntry()) != null) {
            String fileName = localFileHeader1.getFileName();
            if (!(fileName.endsWith("pdf") || fileName.endsWith("PDF"))) {
                return "上传的电子票据应为票据版式文件(pdf)";
            }
        }
        //释放判断文件类型使用到的资源
        zipInputStream1.close();

        //对文件进行相应处理
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file1), StandardCharsets.UTF_8);
        LocalFileHeader localFileHeader;
        //文件数量
        int i = 0;
        while ((localFileHeader = zipInputStream.getNextEntry()) != null) {
            //文件数量加一
            i = i + 1;
            //获取当前获取到的文件名
            String fileName = localFileHeader.getFileName();
            //通过文件名读取一个文件到输入流
            FileHeader fileHeader = zipFile.getFileHeader(fileName);
            InputStream inputStream = zipFile.getInputStream(fileHeader);

            //有需要的话可以转byte
//            byte[] bytes = IOUtils.toByteArray(inputStream);

            try {
                //处理或存储文件信息等
                System.out.println("存储");
            } catch (Exception e) {
                //出异常释放资源
                inputStream.close();
                //有需要的话删除已经处理存储的数据
                System.out.println("删除已存储数据");
                //zip4j文件名使用的是Cp437转码的需转回国内文件名编码GBK
                return (new String(fileName.getBytes("Cp437"), "GBK") + ":" + e.getMessage());
            }
            //释放资源
            inputStream.close();
        }
        //关闭zip流
        zipInputStream.close();
        return String.valueOf(i);
    }
}