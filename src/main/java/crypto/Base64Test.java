package crypto;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

public class Base64Test {
    @Test
    public void test1() throws UnsupportedEncodingException {
        String str = "Base64测试~~~";
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        String encodeStr = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        System.out.println("Base64加密结果："+Arrays.toString(encodeBytes));
        System.out.println("Base64加密结果："+new String(encodeBytes,"utf-8"));
        System.out.println("Base64加密结果："+encodeStr);
        System.out.println("*****************************************************************************************");
        byte[] decodeBytes = Base64.getDecoder().decode(encodeStr);
        System.out.println("Base64解密结果："+ Arrays.toString(decodeBytes));
        System.out.println("Base64解密结果："+ new String(decodeBytes,"utf-8"));
    }
}
