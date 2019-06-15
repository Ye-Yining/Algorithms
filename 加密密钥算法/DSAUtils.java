import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;

/**
 * Created by cuiran on 19/1/11.
 * 生成一对文件 publicKey.key 和 privateKey.key ,
 * 公钥要用户发送 ( 文件 , 网络等方法 ) 给其它用户 , 私钥保存在本地
 * 1.生成秘钥对
 * 2.使用私钥进行签名
 * 3.使用公钥校验签名
 * 意义上的加密解密 非内容型的加密解密
 */
public class DSAUtils {

    public static void main(String[] args) {
        //初始化秘钥对写入到文件 生成的是X.509编码格式的 生成的私钥是PKCS#8编码格式
        getKeyPairs();
        //明文签名
        SignatureData("我是cayden，银行账户为622XXXX");
        //校验签名文件
        checkSignature();

    }



    /**
     * 生成秘钥对写入到文件
     * @return
     */
    public static boolean getKeyPairs() {
        try {
            //初始化秘钥管理器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            //获取秘钥对
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            //直接写入公钥
            ObjectOutputStream out_pub = new ObjectOutputStream(new FileOutputStream("publicKey.key"));
            out_pub.writeObject(publicKey);
            out_pub.close();
            System.out.println("生成的公钥内容为_____:\n "+publicKey);
            //直接写入私钥
            ObjectOutputStream out_pri = new ObjectOutputStream(new FileOutputStream("privateKey.key"));
            out_pri.writeObject(privateKey);
            out_pri.close();
            System.out.println("生成的私钥内容为_____:\n "+privateKey);
            System.out.println("\n生成密钥对成功...");
            return true;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 使用私钥进行签名
     * @return
     */
    public static boolean SignatureData(String  info){
        try {
            //1.读取生成的私钥对明文进行签名
            ObjectInputStream in_pri = new ObjectInputStream(new java.io.FileInputStream("privateKey.key"));
            PrivateKey privateKey = (PrivateKey) in_pri.readObject();
            in_pri.close();
            //初始化签名 对明文开始签名
            Signature signature = Signature.getInstance("DSA");
            signature.initSign(privateKey);
            //Updates the data to be signed or verified, using the specified array of bytes.
            signature.update(info.getBytes());
            // 对信息的数字签名
            //sign() Returns the signature bytes of all the data updated.
            byte[] signedbytes = signature.sign();
            System.out.println("签名为_____:"+signedbytes);
            //把签名的密文存到文件中
            ObjectOutputStream out_signature =new ObjectOutputStream(new FileOutputStream("signature.data"));
            //把明文和签名一起写入 也可以分别写入
            out_signature.writeObject(info);
            out_signature.writeObject(signedbytes);
            out_signature.close();
            System.out.println("秘钥签名完成.......");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("私钥签名失败....");
        }
        return false;
    }

    /**
     * 用公钥进行校验
     * @return
     */
    public static boolean checkSignature(){
        try {
            //读取公钥
            ObjectInputStream in_pub=new ObjectInputStream(new FileInputStream("publicKey.key"));
            PublicKey publicKey = (PublicKey) in_pub.readObject();
            //读取签名文件
            ObjectInputStream in_signature=new ObjectInputStream(new FileInputStream("signature.data"));
            //读取签名信息
            String info = (String) in_signature.readObject();
            //用公钥进行校验
            byte[] signedbytes = (byte[]) in_signature.readObject();
            Signature signature = Signature.getInstance("DSA");
            signature.initVerify(publicKey);
            signature.update(info.getBytes());
            //签名信息校验
            if (signature.verify(signedbytes)) {
                System.out.println("签名的内容为____:" + info);
                System.out.println("签名文件校验正常....");
                return true;
            } else{
                System.out.println("签名校验失败");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}