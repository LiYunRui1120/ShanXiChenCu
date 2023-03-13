package com.example.springbootdemo1.controller;

import jdk.nashorn.internal.ir.debug.ClassHistogramElement;

import java.io.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;



public class ZipTest {

    private static final String PASSKEY = "12345678";
    private static final String DESKEY = "asfsdfsdf";
    private static long fileSize = 3758096384L;

    public static void main(String[] args) {
        File file2 = new File("D:\\tool\\2022\\1117\\encoder\\encoder\\date\\file\\1670397895706\\C041\\2021");
        String str = "定期30年.gif";
        String ss= encoderOrdecoder(String.valueOf(file2),str,Cipher.ENCRYPT_MODE);
//        String sss= encoderOrdecoder(String.valueOf(file2),str,Cipher.DECRYPT_MODE);
    }


    /**
     * @Comments ：对文件进行加密
     * @param filePath  要加密的文件路径
     * @param fileName 文件
     * @param mode 加密模式  加密：Cipher.ENCRYPT_MODE 解密：Cipher.DECRYPT_MODE
     * @return
     */
    public static String encoderOrdecoder(String filePath, String fileName, int mode) {
        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;

        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(DESKEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(PASSKEY.getBytes());
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(mode, securekey, iv, sr);

            File encoderFile = new File(filePath + File.separator + "encoder");
            if (!encoderFile.exists()) {
                encoderFile.mkdir();
            }

            is = new FileInputStream(filePath + File.separator + fileName);
            out = new FileOutputStream(filePath + File.separator + "encoder"
                    + File.separator + fileName);
            cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024 * 8];
            int r;
            while ((r = cis.read(buffer)) >= 0) {
                out.write(buffer, 0, r);

                String str2 = new String(buffer,0,r);
                long lon = str2.length();
                if (lon>fileSize){
                    out.close();

                }
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (cis != null) {
                    cis.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1){

            }
        }
        return filePath + File.separator + "encoder" + File.separator
                + fileName;
    }
    public static void encoderOrdecoder2(String filePath, String fileName, int mode) throws IOException {
        InputStream is = new FileInputStream(filePath + File.separator + fileName);
        OutputStream out = new FileOutputStream(filePath + File.separator + "encoder"
                + File.separator + fileName);
        byte[] buffer = new byte[1024 * 8];
        int r;
        while ((r = is.read(buffer)) >= 0) {
            out.write(buffer, 0, r);

            String str2 = new String(buffer,0,r);
            long lon = str2.length();
            if (lon>fileSize){
                out.close();

            }
        }
    }
}
