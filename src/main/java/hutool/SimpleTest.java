package hutool;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PublicKey;

public class SimpleTest {

    @Test
    public void test1() {
        byte[] data = "我是一段测试字符串".getBytes();
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        //签名
        byte[] signed = sign.sign(data);
        //验证签名
        boolean verify = sign.verify(data, signed);
    }

    @Test
    public void test2() {
        RSA rsa = new RSA();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        System.out.println(publicKeyBase64);
        System.out.println("************************************************************");
        System.out.println(privateKeyBase64);

        byte[] encrypt = rsa.encrypt(StrUtil.bytes("hello world", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

        Assert.assertEquals("hello world", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
        System.out.println("hello world".equals(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8)));

        KeyPair keyPair = SecureUtil.generateKeyPair("RSA");
        PublicKey keyPairPublic = keyPair.getPublic();
        System.out.println(keyPairPublic.toString());
        byte[] encoded = keyPairPublic.getEncoded();
        System.out.println(Base64Encoder.encode(encoded));
    }
}
