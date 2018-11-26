package crypto;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AboutAlgorithm {
    public static void main(String[] args) {

    }

    @Test
    public void toHex() {

        String str = "intusr";
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder hexValue1 = new StringBuilder("");
        byte[] bs = str.getBytes();
        System.out.println("字节数组……");
        System.out.println(Arrays.toString(bs));
        int bit;
        System.out.println("第一种方式转换成十六进制……字母是大写的");
        for (int i = 0; i < bs.length; i++) {
            byte b = bs[i];
            bit = (b & 0x0f0) >> 4;
            hexValue1.append(chars[bit]);
            bit = b & 0x0f;
            hexValue1.append(chars[bit]);
            hexValue1.append(' ');
        }
        System.out.println(hexValue1.toString().trim());

        System.out.println("第二种方式转换成十六进制……字母是小写的");
        StringBuilder hexValue2 = new StringBuilder();
        for (int i = 0; i < bs.length; i++) {
            int val = ((int) bs[i]) & 0xff;
            if (val < 16) {
                hexValue2.append("0");
            }
            hexValue2.append(Integer.toHexString(val));
            hexValue2.append(' ');
        }
        System.out.println(hexValue2.toString().trim());
    }

    @Test
    public void md5Test() throws Exception {
        String str = "Message Digest Algorithm MD5（中文名为消息摘要算法第五版）";
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5Encode(str));
    }

    /***
     * MD5加密 生成32位md5码
     * @param inStr 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    @Test
    public void shaTest() throws Exception {
        String str = "安全散列算法SHA（Secure Hash Algorithm，SHA)";
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + shaEncode(str));
    }

    /***
     * SHA加密 生成40位SHA码
     * @param inStr 待加密字符串
     * @return 返回40位SHA码
     */
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 在用户注册时，首先对输入密码进行MD5加密，然后将其转换成十六进制存储到数据库
     * 或者登陆时用于验证密码一致性
     */
    @Test
    public void checkPwdAbouMd5ToHex() {
        String usrCde = "intusr";
        String pwd = "intusr";
        try {
            String checkPwd = usrCde + pwd;
            MessageDigest messageDigest;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                messageDigest.update(checkPwd.getBytes("UTF-8"));
            } catch (NoSuchAlgorithmException e) {
                System.out.println("NoSuchAlgorithmException caught	MD5!");
                throw new Exception("密码校验方式有误！");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new Exception("密码校验方式有误！");
            }
            byte[] byteArray = messageDigest.digest();
            System.out.println("MD5加密之后结果:\n" + Arrays.toString(byteArray));
            System.out.println("***************************************************************************");
            StringBuilder md5StrBuff = new StringBuilder();
            System.out.println("MD5加密之后开始进行十六进制转换……");
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(
                            Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer
                            .toHexString(0xFF & byteArray[i]));
                }
            }
            String passwordCheck = md5StrBuff.toString().toUpperCase();
            System.out.println("MD5加密之后,再转成十六进制结果:\n" + passwordCheck);
            System.out.println("251EE0D392100E8F6A0C6F63C4F2297E".equals(passwordCheck));
        } catch (Exception e) {
            System.out.println("catch捕获==》校验密码时发生异常！");
        }
    }

}
