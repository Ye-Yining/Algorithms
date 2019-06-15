package security;
        
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SymmetricEncoder{
    /*
    *AES加密
    *Step 1：构造AES密钥生成器并且初始化密钥生成器
    *Step 2: 生成AES密钥
    *Step 3：创建并初始化AES密码器
    *Step 4：加密内容
    *Step 5：返回加密后的字符串
    */
    public static String AESEncode(String encodeRules, String content){

        try{
            //1.构造AES密钥生成器,其中“AES”可以用“aes”代替
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //2.根据encodeRules规则初始化密钥生成器，使其具有确定的密钥大小128位
            //SucureRandom会生成安全随机数序列，其中encodeRules.getBytes为种子
            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));

            //3.产生原始对称密钥
            SecretKey originalKey = keyGenerator.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");

            //6.生成AES密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为ENCRYPT_MODE加密模式，也可以是DECRTPY_MODE解密模式
            //第二个参数为使用的密钥
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //8.获得加密内容的字节数组（使用UTF-8编码-中英文）
            byte[] byteContent = content.getBytes("utf-8");
            //9.加密数据
            byte[] byteResult = cipher.doFinal(byteContent);
            //10.将加密后的数据转换成字符串,此处使用BASE64做转码功能起到2次加密的作用。  
            String result = new String(new BASE64Encoder().encode(byteResult));

            //返回加密后的字符串
            return result;
         } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //如果有错就返回null
        return null;
    }

    public static String AESDecode(String encodeRules, String content){
        try{
            //1.构造密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //2.初始化密钥生成器
            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));

            //3.产生原始对称密钥
            SecretKey originalKey = keyGenerator.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            
             //6.生成AES密码器
             Cipher cipher = Cipher.getInstance("AES");
             //7.初始化密码器，设置其为DECRTPY_MODE解密模式
             //第二个参数为使用的密钥
             cipher.init(Cipher.DECRYPT_MODE, key);

             //8.将加密并编码后的内容解码成字节数组（先用base64解密 ）
             byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
             //9.解密
             byte[] byteResult = cipher.doFinal(byteContent);
             //10.将解密后的字节数组转换成字符串
             String result = new String(byteResult, "utf-8");
             //11.返回字符串
             return result; 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
            
            //如果有错就返加nulll
            return null;         
        }

    public static void main(String[] args){
        //SymmetricEncoder se=new SymmetricEncoder();
        Scanner scanner=new Scanner(System.in);
        //加密
        System.out.println("使用AES对称加密，请输入加密的规则");
        String encodeRules=scanner.next();
        System.out.println("请输入要加密的内容:");
        String content = scanner.next();
        System.out.println("根据输入的规则"+encodeRules+"加密后的密文是:"+SymmetricEncoder.AESEncode(encodeRules, content));

        //解密
        System.out.println("使用AES对称解密，请输入加密的规则：(须与加密相同)");
         encodeRules=scanner.next();
        System.out.println("请输入要解密的内容（密文）:");
         content = scanner.next();
        System.out.println("根据输入的规则"+encodeRules+"解密后的明文是:"+SymmetricEncoder.AESDecode(encodeRules, content));
    }
}