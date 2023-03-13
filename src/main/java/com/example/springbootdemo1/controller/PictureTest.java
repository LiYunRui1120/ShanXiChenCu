package com.example.springbootdemo1.controller;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 李云瑞
 * @date 2023/1/6 15:14
 */
public class PictureTest {

    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://img01.jituwang.com/180505/256658-1P5051S50512.jpg");
        String str = String.valueOf(url);
//        String s = String.valueOf(image2byte(str));
//        System.out.println("sdaddddddsssssss====="+image2byte(str));
        getImageBinary();
//        PictureTest pictureTest = new PictureTest();
//        pictureTest.ss2();
    }


        private void ss2(){
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
    }


    private static void getImageBinary(){
        File file = new File("C:\\Users\\Administrator\\Pictures\\Camera Roll\\256658-1P5051S50512.jpg");
//        String file = "blob:http://localhost/e007f4af-25d7-4876-9c2d-ce897b404f09";
        BufferedImage bi;
        try {
            bi = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi,"jpg",baos);
            byte[] bytes = baos.toByteArray();
            String str = encoder.encodeBuffer(bytes).trim();
            base64(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void base64(String base64String){
        try {
            byte[] bytes = decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage bi = ImageIO.read(bais);
            File file = new File("D:\\311\\timg.jpg");
            ImageIO.write(bi,"jpg",file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    //图片到byte数组
    public static byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

}
