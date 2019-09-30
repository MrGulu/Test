package shiftOperator;

public class Test {
    /**
     * 对于正数，原码、反码、补码都是一样的
     * 对于负数，计算机中都是用补码表示
     * 因为下面都是使用int类型，4字节，32位，所以Integer.toBinaryString(j)之后的二进制表示是32位的。
     * 例如-4这个负数，原码为10000000000000000000000000000100，
     *              反码为11111111111111111111111111111011，
     *              补码为11111111111111111111111111111100
     * （原码最高位为符号位，0正1负；反码为除了符号位取反（0变1,1变0）；补码为在反码基础上+1.
     *  所以对于负数，要求原码，逆向操作即可：-1，取反。）
     */

    /**
     * java中有三种移位运算符

     <<      :     左移运算符，num << 1,相当于num乘以2

     >>      :     右移运算符，num >> 1,相当于num除以2

     >>>    :     无符号右移，忽略符号位，空位都以0补齐
     */

    /**
     * 1.k和hex分别定义二进制和十六进制数，可以用byte、short、int、long、float、double、char接收，
     *   但这里统一用int来接收
     */
    @org.junit.Test
    public void test() {
        int i = 1;
        //1
        System.out.println("i=1二进制表示： " + Integer.toBinaryString(i));
        int j = 4;
        //100
        System.out.println("j=4二进制表示： " + Integer.toBinaryString(j));
        //二进制码，对应十进制8
        int k = 0b1000;

        //注意下面的十六进制数0x0010，转换成二进制就是0000 0000 0001 0000，对应十进制的16
        int hex = 0x0010;
        int iresult = i << 4;
        System.out.println("int i = 1 << 4\r\n" + iresult);
        int jresult = j << 4;
        System.out.println("int j = 4 << 4\r\n" + jresult);
        int kresult = k << 1;
        System.out.println("int k = 0b1000 << 1\r\n" + kresult);
        int hexresult = hex << 1;
        System.out.println("int hex = 0x0010 << 1\r\n" + hexresult);
        System.out.println("*********************************************");
        int l = -1;
        int m = -4;
        //二进制码，对应十进制8
        int n = -0b1000;
        int lresult = l << 4;
        System.out.println("l = -1的补码： " + Integer.toBinaryString(l));
        System.out.println("int l = -1 << 4\r\n" + lresult);
        int mresult = m << 4;
        System.out.println("m = -4的补码： " + Integer.toBinaryString(m));
        System.out.println("int m = -4 << 4\r\n" + mresult);
        //有符号右移运算符>>，即使是负数也会除以2的n次方
        System.out.println("int m = -4 >> 4\r\n" + (mresult >> 4));
        int nresult = n << 1;
        System.out.println("n = -0b1000的补码： " + Integer.toBinaryString(n));
        System.out.println("int n = -0b1000 << 1\r\n" + nresult);
    }

    @org.junit.Test
    public void test1() {
        int i = 0b1010 >>> 1;
        System.out.println(i);
        int j = 0b1010 >>> 2;
        System.out.println(j);
        int k = 0b1010 >>> 3;
        System.out.println(k);
        int l = 0b1010 >>> 4;
        System.out.println(l);
        int m = 0b1010 >>> 5;
        System.out.println(m);
    }

    @org.junit.Test
    public void test2() {
        int i = 0b1011;
        int j = 0b1110;
        int k = 0b100101;
        int l = 0b00100101;
        System.out.println("i   = "+Integer.toBinaryString(i));
        System.out.println("j   = "+Integer.toBinaryString(j));
        System.out.println("k   = " + Integer.toBinaryString(k));
        System.out.println("i&j = "+Integer.toBinaryString(i&j));
        System.out.println("i|j = "+Integer.toBinaryString(i|j));
        System.out.println("i^j = "+Integer.toBinaryString(i ^ j));
        System.out.println("~k  = "+Integer.toBinaryString(~k));
        System.out.println("~l  = " + Integer.toBinaryString(~l));
    }
}
