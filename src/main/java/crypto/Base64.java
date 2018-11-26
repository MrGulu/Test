package crypto;

import java.util.ArrayList;
import java.util.List;

public class Base64 {

    private static String base64Code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static void main(String[] args) {
        System.out.println(encode("Base64"));
        System.out.println(decode("QmFzZTY0"));
    }

    public static String encode(String srcData) {
        if(srcData == null || srcData.length() == 0) {
            return srcData;
        }
        char[] chArr = srcData.toCharArray();
        String asciiBin = null;
        StringBuilder asciiBin_all = new StringBuilder();
        for(int i= 0; i< chArr.length; i++) {
            //将字符转换成ASCII编码再转换成对应二进位
            asciiBin = Integer.toBinaryString((int)chArr[i]);
            //给不足8位的在高位补0直到补足8位
            while(asciiBin.length()< 8) {
                asciiBin= "0"+ asciiBin;
            }
            //最后把所有二进位拼接成一个字串
            asciiBin_all.append(asciiBin);
        }
        //若长度不能被6整除，则在低位补0到能被6整除为止
        while(asciiBin_all.length()% 6!= 0) {
            asciiBin_all.append("0");
        }
        String asciiBinStr = asciiBin_all.toString();
        //按6个一组拆分成字串数组
        List<String> bin6List = new ArrayList<String>();
        String temp = null;
        while(asciiBinStr.length()/ 6> 0) {
            temp = asciiBinStr.substring(0, 6);
            asciiBinStr = asciiBinStr.substring(6);
            bin6List.add(temp);
        }
        String[] bin6Str = bin6List.toArray(new String[bin6List.size()]);
        int[] index = new int[bin6Str.length];
        //确定最终补位长度
        int overLen = 0;
        if(srcData.length()% 3 != 0) {
            overLen = 3- srcData.length()% 3;
        }
        //设定存放最终编码的容器
        char[] code = new char[index.length+ overLen];
        for(int i= 0; i< index.length; i++) {
            //将二进位转换成十进制数字
            index[i] = Integer.parseInt(bin6Str[i], 2);
            //Base64 : Value -> Encoding
            code[i] = base64Code.charAt(index[i]);
        }
        switch(overLen) {
            case 2:code[code.length- 2] = '=';//不需要break
            case 1:code[code.length- 1] = '=';
            default:
        }
        return String.valueOf(code);
    }

    public static String decode(String srcData) {
        //检测元数据中“=”的个数，并将之去除
        int counter = 0;
        if(srcData.contains("=")) {
            counter = 1;
            if(srcData.substring(srcData.length()- 2, srcData.length()- 1).equals("=")) {
                counter = 2;
            }
        }
        srcData = srcData.replaceAll("=", "");
        //将密文根据Base64编码表转换成对应Value，再转换成二进位 ，然后将所有二进位补足6位，最后将所有二进位存进一个字串
        char[] srcCh = srcData.toCharArray();
        StringBuffer bin6SB = new StringBuffer();
        int index;
        String bin6Str;
        for(int i= 0; i< srcCh.length; i++) {
            //获得Base64编码表的Value
            index = base64Code.indexOf(srcCh[i]);
            //将Value转为二进位
            bin6Str = Integer.toBinaryString(index);
            //在长度不足6位的二进位的高位上补0直到补足6位，再保存进字串
            while(bin6Str.length()< 6) {
                bin6Str = "0"+ bin6Str;
            }
            bin6SB.append(bin6Str);
        }
        String bin6Str_all = bin6SB.toString();
        //如果二进位字串后有多补的0，将之去除
        if(counter == 1) {
            bin6Str_all = bin6Str_all.substring(0, bin6Str_all.length()- 2);
        } else if(counter == 2) {
            bin6Str_all = bin6Str_all.substring(0, bin6Str_all.length()- 4);
        }
        //按8个一组拆分成字串数组
        List<String> bin8List = new ArrayList<String>();
        String temp;
        while(bin6Str_all.length()/ 6> 0) {
            temp = bin6Str_all.substring(0, 8);
            bin6Str_all = bin6Str_all.substring(8);
            bin8List.add(temp);
        }
        String[] bin8Str = bin8List.toArray(new String[bin8List.size()]);
        //将该字串数组的每个元素（即一组二进位）转成十进制数，再强制转换成char类型
        char[] ascii = new char[bin8Str.length];
        for(int i= 0; i< ascii.length; i++) {
            ascii[i] = (char)Integer.parseInt(bin8Str[i], 2);
        }
        return String.valueOf(ascii);
    }

}
