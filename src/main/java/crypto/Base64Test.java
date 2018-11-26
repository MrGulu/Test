package crypto;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

public class Base64Test {
    /**
     * 1.Base64编码时，首先将String转成byte[]，然后就可以将这个byte[]转成字符串存储到数据库中当作盐
     * 或者将byte[]转成16进制当作盐
     * 2.Base64解码时，解码byte[]与解码String结果是一样的。这是因为在解码String的时候，先将String转成byte[]
     * 之后，又解码的byte[]
     * @throws UnsupportedEncodingException
     */
    /**
     * 字节数组中的数字是ASCII表中的十进制表示，比如运行结果中第一个是81，那么对应ASCII表中的就是Q
     */
    @Test
    public void test1() throws UnsupportedEncodingException {
        String str = "Base64测试~~~";
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        String encodeStr = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        String hexStr = HexUtil.bytesToHexFun2(encodeBytes);
        System.out.println("Base64编码结果byte[]转String通过Arrays.toString：\n" + Arrays.toString(encodeBytes));
        System.out.println("Base64编码结果byte[]转String通过new String：\n" + new String(encodeBytes, "utf-8"));
        System.out.println("Base64编码结果byte[]转String通过new String和utf-8：\n" + new String(encodeBytes, "utf-8"));
        System.out.println("Base64编码结果byte[]转String通过Base64.getEncoder().encodeToString：\n" + encodeStr);
        System.out.println("Base64编码结果byte[]转HEX：\n" + hexStr);
        System.out.println("*****************************************************************************************");
        byte[] decodeBytes = Base64.getDecoder().decode(encodeBytes);
        System.out.println("Base64解码byte[]结果byte[]转String通过Arrays.toString：\n" + Arrays.toString(decodeBytes));
        System.out.println("Base64解码byte[]结果byte[]转String通过new String：\n" + new String(decodeBytes, "utf-8"));
        System.out.println("Base64解码byte[]结果byte[]转String通过new String和utf-8：\n" + new String(decodeBytes, "utf-8"));
        System.out.println("*****************************************************************************************");
        byte[] decodeString = Base64.getDecoder().decode(encodeStr);
        System.out.println("Base64解码String结果byte[]转String通过Arrays.toString：\n" + Arrays.toString(decodeString));
        System.out.println("Base64解码String结果byte[]转String通过new String：\n" + new String(decodeString, "utf-8"));
        System.out.println("Base64解码String结果byte[]转String通过new String和utf-8：\n" + new String(decodeString, "utf-8"));
    }

    /**
     * 通过下面的代码与上面的比较，发现没有使用Base64编码的字符串转成byte[]数组的大小是不一样的，
     * 而且将其直接转成String还是原来的字符串，也不会起到编码加密的作用。
     * Base64编码会把3字节的二进制数据编码为4字节的文本数据，长度增加33%
     * 所以没经过Base64编码的是15个字节，上面经过Base64编码的是20个字节，正好增加1/3
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test2() throws UnsupportedEncodingException {
        String str = "Base64测试~~~";
        byte[] bytes = str.getBytes();
        String encodeStr = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        System.out.println("byte[]转String通过Arrays.toString：\n" + Arrays.toString(bytes));
        System.out.println("byte[]转String通过new String：\n" + new String(bytes, "utf-8"));
        System.out.println("byte[]转String通过new String和utf-8：\n" + new String(bytes, "utf-8"));
        System.out.println("Base64编码结果byte[]转String通过Base64.getEncoder().encodeToString：\n" + encodeStr);
        System.out.println("*****************************************************************************************");
        String str1 = "Base";
        byte[] encodeBytes = Base64.getEncoder().encode(str1.getBytes());
        System.out.println(Arrays.toString(encodeBytes));
        System.out.println(new String(encodeBytes));
        System.out.println("*****************************************************************************************");
        String str2 = "Base64";
        byte[] encodeBytes2 = Base64.getEncoder().encode(str2.getBytes());
        System.out.println(Arrays.toString(encodeBytes2));
        System.out.println(new String(encodeBytes2));

    }
}
