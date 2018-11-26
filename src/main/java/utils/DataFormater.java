package utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *  @author 郑翔
 * 
 */
public class DataFormater{
    /**
     * 字节转2位16进制字窜
     * @param b
     * @return
     */
    private static final  String[] hexDigits = { 
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

    public DataFormater() {
    }

    /**
     * short 转 byte 数组
     * @param s int
     * @return 4位 byte 数组
     * 数组0位存储int最高位信息，3位存贮最低位信息
     */
    public static byte[] short2byte(short s) {
        byte[] ba = new byte[2];
        ba[0] = (byte) (s >> 8);
        ba[1] = (byte) s;
        return ba;
    }

    /**
     * unsigned int 转 byte 数组
     * @param i int
     * @return 4位 byte 数组
     * 数组0位存储int最高位信息，3位存贮最低位信息
     */
    public static byte[] unsignedInt2byte(long n) {
        byte[] ba = new byte[4];
        ba[0] = (byte) ((n & 0xFF000000L) >> 24);
        ba[1] = (byte) ((n & 0x00FF0000L) >> 16);
        ba[2] = (byte) ((n & 0x0000FF00L) >> 8);
        ba[3] = (byte) (n & 0x000000FFL);
        return ba;
    }

    /**
     * int 转 byte 数组
     * @param n int
     * @return 4位 byte 数组
     * 数组0位存储int最高位信息，3位存贮最低位信息
     */
    public static byte[] int2byte(int n) {
        byte[] ba = new byte[4];
        ba[0] = (byte) (n >> 24);
        ba[1] = (byte) (n >> 16);
        ba[2] = (byte) (n >> 8);
        ba[3] = (byte) n;
        return ba;
    }

    /**
     * byte 数组 转 unsigned int
     * @param ba byte 数组
     * @param beginIndex 从beginIndex开始读取后4位字节转化为int，不足4位的取到数组的末位
     * @return int 整数
     */
    public static long byte2UnsignedInt(byte[] ba, int beginIndex) {
        return byte2int(ba, beginIndex) & 0xFFFFFFFFL;
    }

    /**
     * byte 数组 转 int
     * @param ba byte 数组
     * @param beginIndex 从beginIndex开始读取后4位字节转化为int，不足4位的取到数组的末位
     * @return int 整数
     */
    public static int byte2int(byte[] ba, int beginIndex) {
        int n = 0;
        if (ba != null) {
            for (int i = beginIndex; (i < ba.length) && (i < beginIndex + 4); i++) {
                n = n << 8;
                n = n | (ba[i] & 0xff);
            }
        }
        return n;
    }

    /**
     * byte 数组 转 unsigned int
     * @param ba byte 数组
     * @return int 整数
     */
    public static long byte2UnsignedInt(byte[] ba) {
        return byte2int(ba) & 0xFFFFFFFFL;
    }

    /**
     * byte 数组 转 int
     * @param ba byte 数组
     * @return int 整数
     */
    public static int byte2int(byte[] ba) {
        int n = 0;
        if (ba != null) {
            for (int i = 0; (i < ba.length) && (i < 4); i++) {
                n = n << 8;
                n = n | (ba[i] & 0xff);
            }
        }
        return n;
    }

    /**
     * long转为byte
     * @param n long
     * @return 8位byte数组
     */
    public static byte[] long2byte(long n) {
        byte[] b = new byte[8];
        b[0] = (byte) (int) (n >> 56);
        b[1] = (byte) (int) (n >> 48);
        b[2] = (byte) (int) (n >> 40);
        b[3] = (byte) (int) (n >> 32);
        b[4] = (byte) (int) (n >> 24);
        b[5] = (byte) (int) (n >> 16);
        b[6] = (byte) (int) (n >> 8);
        b[7] = (byte) (int) n;
        return b;
    }

    /**
     * byte转为long
     * @param b 8位byte数组
     * @return long
     */
    public static long byte2long(byte[] b) {
        return (long) b[7] & (long) 255 | ((long) b[6] & (long) 255) << 8 | ((long) b[5] & (long) 255) << 16 | ((long) b[4] & (long) 255) << 24 | ((long) b[3] & (long) 255) << 32
                | ((long) b[2] & (long) 255) << 40 | ((long) b[1] & (long) 255) << 48 | (long) b[0] << 56;
    }

    /**
     * byte转为unsigned long
     * @param b 8位byte数组
     * @return
     */
    public static String byte2unsignedLong(byte[] b) {
        //由于java中的long为signedLong，所以需要通过将最高位的进行剥离，进行特殊处理
        //最高位为1时unsignedLong为9223372036854775808，需要拆分成"9"+"223372036854775808"
        StringBuffer unsignedLong = new StringBuffer();
        long signedLong = byte2long(b);

        //小于0表示高位为1，需要特殊处理
        if (signedLong < 0) {
            //1、取模
            long newSignedLong = signedLong & 0x7FFFFFFFFFFFFFFFL;

            //2、添加拆分信息
            String sNewSignedLong = String.valueOf(newSignedLong + 223372036854775808L);

            //3、添加高位拆分"9"计算
            if (sNewSignedLong.length() > 18) {
                int top = (9 + Integer.parseInt(sNewSignedLong.substring(0, 1)));
                unsignedLong = unsignedLong.append(top).append(sNewSignedLong.substring(1));
            }
            else {
                unsignedLong = unsignedLong.append(9).append(sNewSignedLong);
            }
        }
        else {
            unsignedLong = unsignedLong.append(signedLong);
        }
        return unsignedLong.toString();
    }

    /**
     * 将时间戳转成字符
     * @param format 时间的标示格式
     * @return 时间戳字符串
     */
    public static String getTimeStamp(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String sTimeStamp = sdf.format(date);
        return sTimeStamp;
    }

    /**
     * 将BCD编码的一个字节转化成对应的字符窜
     * @param b BCD编码的字节
     * @return 2位的正数字符窜，如08 、13
     */
    public static String BCD2String(byte b) {
        byte low = (byte) (b & 0x0f);
        byte high = (byte) ((b >> 4) & 0x0f);
        return String.valueOf(high * 10 + low);
    }

    /**
     *  @Date:2014-1-11
     *  @author:温亦汝
     *  @param b
     *  @return
     * 
     */
    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 字节码转换成16进制字符串

     * @param b
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + "";
            }
        }
        return hs.toLowerCase();
    }
}
