package utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author tangwenlong
 * @description 对称密钥工具类
 */
@Slf4j
public class SymmetricKeyUtils {

    /**
     * 随机生成秘钥
     */
    public static String getKey(String algorithm, int keySize) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            //要生成多少位，只需要修改这里即可128, 192或256
            kg.init(keySize);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String key = byteArrayToHex(b);
            log.info("Algorithm:[{}]随机生成密钥:[{}]", algorithm, key);
            return key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("没有此算法:[{}]。", algorithm);
        }
        return null;
    }

    /**
     * 使用指定的seed生成秘钥
     */
    public static String getKeyByPass(String algorithm, int keySize, String seed) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            // kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
            //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
            kg.init(keySize, new SecureRandom(seed.getBytes()));
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String key = byteArrayToHex(b);
            log.info("Algorithm:[{}]随机生成密钥:[{}],seed:[{}]", algorithm, key, seed);
            return key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("没有此算法:[{}]。", algorithm);
        }
        return null;
    }

    /**
     * 使用指定的seed生成秘钥
     */
    public static String getKeyByPass(String algorithm, int keySize, long seed) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            // kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
            //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(seed);
            kg.init(keySize, secureRandom);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String key = byteArrayToHex(b);
            log.info("Algorithm:[{}]随机生成密钥:[{}],seed:[{}]", algorithm, key, seed);
            return key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("没有此算法:[{}]。", algorithm);
        }
        return null;
    }

    public static String encrypt(String algorithm, String key, String content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        byte[] bytes = hexToByteArray(key);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bytes, 0, bytes.length, algorithm));
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        //将数据加密，返回字节数组
        byte[] cipherBytes = cipher.doFinal(contentBytes);
        //加密之后的数据转换成BASE64编码输出
//        String encodeString = new BASE64Encoder().encrypt(cipherBytes);
        //加密之后将字节数组转为刻度的十六进制字符串
        String encodeString = byteArrayToHex(cipherBytes);
        log.info("对称密钥加密明文：[{}]，密文：[{}]", content, encodeString);
        //11.将字符串返回
        return encodeString;
    }

    public static String decrypt(String algorithm, String key, String content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        byte[] bytes = hexToByteArray(key);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(bytes, 0, bytes.length, algorithm));
        //先将加密时字节数组转为刻度的十六进制字符串，转回字节数组
        byte[] contentBytes = hexToByteArray(content);
        //将数据解密，返回字节数组
        byte[] cipherBytes = cipher.doFinal(contentBytes);
        //解密后的数组，返回原始明文字符串
        String decodeString = new String(cipherBytes, StandardCharsets.UTF_8);
        log.info("对称密钥加密密文：[{}]，明文：[{}]", content, decodeString);
        //11.将字符串返回
        return decodeString;
    }


    /**
     * ps：与下面有细微区别，暂时用的下面的方法转换
     * byte数组转化为16进制字符串
     *
     * @param bytes 待转换byte数组
     * @return 转成的16进制字符串
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String strHex = Integer.toHexString(aByte);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0").append(strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }


    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String byteArrayToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    /**
     * hex字符串转byte数组
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }


    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }
}