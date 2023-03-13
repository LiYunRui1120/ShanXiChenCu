package com.example.springbootdemo1.controller;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.Key;
import java.sql.SQLOutput;
import java.util.Base64;

public class DESDemo2 {

    /**
     * 偏移变量，固定占8位字节
     */
    private final static String IV_PARAMETER = "12345678";
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
    /**
     * 默认编码
     */
    private static final String CHARSET = "utf-8";

    public static void main(String[] args) {
//        String s = "/0000/2022/123.pdf,/0000/2022/123.pdf,/SX00/2022/123.pdf,/SX00/2022/123.pdf";
        String s = "btM0/U1ziqFOGap62iNDIrTuxmQB2DdRVd2HUKgfNN+wZPJ3fTvi+xo5fFlxMHxZL5rcjsFvnmbfPw+wNXPJgA94nBnF/po3bkdgbaN/O4s=";

        File file = new File("D:\\tool\\2022\\1117\\新建文本文档.zip");
        File file2 = new File("D:\\project\\USBExporter\\ue-server\\target\\classes\\excel\\压缩文件1669195871431.zip");
        File file3 = new File("D:\\tool\\2022\\1117\\压缩文件1669195871431.zip");
        File file4 = new File("D:\\tool\\2022\\1117\\压缩文件77777777777777.zip");
        String strFile = String.valueOf(file2);
        String dateFile = String.valueOf(file3);
        String strFile1 = String.valueOf(file3);
        String dateFile1 = String.valueOf(file4);

//        String s4 = encryptFile("shujujiamimima",strFile,dateFile);

        String s5 = decryptFile("yuanwenshujujiami",strFile1,dateFile1);
    }

    /**
     * 生成key
     *
     * @param password
     * @return
     * @throws Exception
     */
    private static Key generateKey(String password) throws Exception {
        DESKeySpec dks = new DESKeySpec(password.getBytes(CHARSET));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    /**
     * DES加密字符串
     *
     * @param password 加密密码，长度不能够小于8位
     * @param data     待加密字符串
     * @return 加密后内容
     */
    public static String encrypt(String password, String data) {
        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));

            //JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
            //Android平台可以使用android.util.Base64
            return new String(Base64.getEncoder().encode(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES解密字符串
     *
     * @param password 解密密码，长度不能够小于8位
     * @param data     待解密字符串
     * @return 解密后内容
     */
    public static String decrypt(String password, String data) {
        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES加密文件
     *
     * @param srcFile  待加密的文件
     * @param destFile 加密后存放的文件路径
     * @return 加密后的文件路径
     */
    public static String encryptFile(String password, String srcFile, String destFile) {
        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(password), iv);
            InputStream is = new FileInputStream(srcFile);
            FileOutputStream out = new FileOutputStream(destFile);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
            cis.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密文件
     *
     * @param srcFile  已加密的文件
     * @param destFile 解密后存放的文件路径
     * @return 解密后的文件路径
     */
    public static String decryptFile(String password, String srcFile, String destFile) {
        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            File file = new File(destFile);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(password), iv);
            InputStream is = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(destFile);
            CipherOutputStream cos = new CipherOutputStream(out, cipher);
            System.out.println("cos=="+cos);
            byte[] buffer = new byte[1024 * 8];
            int r = 0;
            while ((r = is.read(buffer)) != -1) {
                cos.write(buffer, 0, r);
            }
            System.out.println("cos======"+cos);
            cos.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
