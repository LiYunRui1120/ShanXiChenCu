package com.example.springbootdemo1.controller;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;


/**
* MD5加密法
* */
public class IDEADemo {
    public static void main(String[] args) {

        String str = "nisf342njk.txt";
        String ss=getMD5Code(str);
        System.out.println("ssssssssssssss========"+ss);
        System.out.println(getMD5Code("nisf342njk.txt"));
        System.out.println(DigestUtils.md5Hex(str));

    }

    // md5加密
    public static String getMD5Code(String message) {
        String md5Str = "";
        try {
            //创建MD5算法消息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //生成的哈希值的字节数组
            byte[] md5Bytes = md.digest(message.getBytes());
            md5Str = bytes2Hex(md5Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    // 2进制转16进制
    public static String bytes2Hex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        int temp;
        try {
            for (int i = 0; i < bytes.length; i++) {
                temp = bytes[i];
                if (temp < 0) {
                    temp += 256;
                }
                if (temp < 16) {
                    result.append("0");
                }
                result.append(Integer.toHexString(temp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}