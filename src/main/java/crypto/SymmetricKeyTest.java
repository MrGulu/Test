package crypto;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.SymmetricKeyUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 方法getInstance(String var0)的参数为String类型，指定加密算法的名称。
 * 可以是 “Blowfish”、“DES”、“DESede”、“HmacMD5”或“HmacSHA1”等。
 * 这些算法都可以实现加密，这里我们不关心这些算法的细节，只要知道其使用上的特点即可。
 * 其中“DES”是目前最常用的对称加密算法，但安全性较差。
 * 针对DES安全性的改进产生了能满足当前安全需要的TripleDES算法，
 * 即“DESede”。“Blowfish”的密钥长度可达448位，安全性很好。
 * “AES”是一种替代DES算法的新算法，可提供很好的安全性
 * ————————————————
 * 版权声明：本文为CSDN博主「liaomin416100569」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liaomin416100569/article/details/5432083
 * <p>
 * <p>
 * 方法init(int var1)的参数为int类型，指定密钥的长度。
 * 如果该步骤省略的话，会根据算法自动使用默认的密钥长度。
 * 指定长度时，若第一步密钥生成器使用的是“DES”算法，则密钥长度必须是56位；
 * 若是“DESede”，则可以是112或168位，其中112位有效；
 * 若是“AES”，可以是128, 192或256位；
 * 若是“Blowfish”，则可以是32至448之间可以被8整除的数；
 * “HmacMD5”和“HmacSHA1”默认的密钥长度都是64个字节。
 * ————————————————
 * 版权声明：本文为CSDN博主「liaomin416100569」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liaomin416100569/article/details/5432083
 */

/**
 * @description 对称密钥工具类
 * @author tangwenlong
 */
@Slf4j
public class SymmetricKeyTest {

    public static final String ALGORITHM_DES = "DES";

    public static final String ALGORITHM_AES = "AES";

    /**
     * @description 测试各种方式生成对称密钥
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    @Test
    public void generateAesKey() throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        //keysize: must be equal to 56
        SymmetricKeyUtils.getKey(ALGORITHM_DES, 56);
        //keysize: must be equal to 128, 192 or 256
        SymmetricKeyUtils.getKey(ALGORITHM_AES, 128);

        SymmetricKeyUtils.getKeyByPass(ALGORITHM_DES, 56, "hello");
        SymmetricKeyUtils.getKeyByPass(ALGORITHM_AES, 128, "hello");

        SymmetricKeyUtils.getKeyByPass(ALGORITHM_DES, 56, 123456);
        SymmetricKeyUtils.getKeyByPass(ALGORITHM_AES, 128, 123456);


        //使用seed生成对称密钥，一般使用当前时间戳，System.currentTimeMillis();
        SymmetricKeyUtils.getKeyByPass(ALGORITHM_DES, 56, System.currentTimeMillis());
        SymmetricKeyUtils.getKeyByPass(ALGORITHM_AES, 128, System.currentTimeMillis());
    }

    /**
     * @description 测试使用对称密钥加解密
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    @Test
    public void encodeAnddecode() throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        String keyByPass = SymmetricKeyUtils.getKeyByPass(ALGORITHM_AES, 128, System.currentTimeMillis());
        String encode = SymmetricKeyUtils.encrypt(ALGORITHM_AES, keyByPass, "测试对称密钥~~");
        String decode = SymmetricKeyUtils.decrypt(ALGORITHM_AES, keyByPass, encode);
        log.info("******************************");
    }
}