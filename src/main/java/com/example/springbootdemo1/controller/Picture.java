package com.example.springbootdemo1.controller;

import io.undertow.server.handlers.form.FormData;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 李云瑞
 * @date 2023/1/3 10:56
 */
public class Picture {


    public static void main(String[] args) throws IOException {
        File file = new File("http://localhost/62622190-e19a-4020-9428-b8b99d9959ac");
        HttpServletRequest httpServletRequest = null;
        httpServletRequest.setAttribute("image",file);
        uploadFile(httpServletRequest);
    }

    public static String uploadFile(HttpServletRequest request) throws IOException {
        String filePath="/root/nginx/static/"; //定义上传文件的存放位置
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("image");//file是form-data中二进制字段对应的name
        System.out.println(multipartFile.getSize());
        String fileName = multipartFile.getOriginalFilename();//获取到上传文件的名字
        System.out.println(multipartFile.getName());//获取到file
        File dir = new File(filePath,fileName);
        System.out.println(multipartFile.getSize());
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        multipartFile.transferTo(dir);
        //返回图片所在url
        return "http://127.0.0.1:8080"+"/"+fileName;
    }


}
