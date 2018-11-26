package utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by zhanghaoyu on 2017/9/11.
 */
public class RSASignUtils {
    private static final Logger logger = LoggerFactory.getLogger(RSASignUtils.class);

    //为KEY_ALGORITHM指定加密算法
    public static final String KEY_ALGORITHM = "RSA";
    //为签名指定生成的加密算法
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";



    //生成签名
    public static String rsaSign(Object bizContent,String privateKey)throws Exception{
        //对内容进行utf-8编码 防止乱码
        String content = URLEncoder.encode((String)bizContent,"UTF-8");
        //利用重载的方法生成签名
        String rsaSign = RSASignUtils.rsaSign(content.getBytes("UTF-8"),privateKey);
        return rsaSign;
    }
    //生成签名
    public static String rsaSign(byte[] data,String privateKey)throws Exception{
        //对使用 MIME base64 编码的私钥进行解码
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        //用PKCS#8编码格式来表示私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //获得一个加密算法是KEY_ALGORITHM指定的加密算法的factory对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //通过刚才获得的keyFactory获得私钥对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //获得一个生成签名算法是由SIGNATURE_ALGORITHM指定的加密算法的生成签名/校验签名的对象
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        //初始化用私钥签署签名的对象
        signature.initSign(priKey);
        //根据初始化类型，更新要签名或验证的字节数据data[]。
        signature.update(data);
        //返回用Base64编码的所有已更新数据的签名字节。
        return Base64.encodeBase64String(signature.sign());
    }


    //通过公钥校验签名
    public static boolean verify(String bizContent,String publicKey,String sign)throws Exception{
        //对bizContent进行URLencode的UTF-8编码
        byte[] date = URLEncoder.encode(bizContent,"UTF-8").getBytes("UTF-8");
        //对用Base64编码的公钥进行解码
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        //构造一个由X509编码的的对象对解码后的公钥进行转换.
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        //获得一个加密算法由KEY_ALGORITHM指定的加密算法的keyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //通过keyfactory获得由X509编码之后的公钥对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        //获得一个加密算法由SIGNATURE_ALGORITHM指定加密算法的生成签名/校验签名对象
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        //初始化用来验证签名的Signature对象
        signature.initVerify(pubKey);
        //更新有公钥验证的数据
        signature.update(date);
        //用这个生成签名/校验签名对象来校验解码后的签名是否正确
        return signature.verify(Base64.decodeBase64(sign));
    }


}
