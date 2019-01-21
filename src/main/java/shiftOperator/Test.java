package shiftOperator;

public class Test {
    /**
     * 1.k和hex分别定义二进制和十六进制数，可以用byte、short、int、long、float、double、char接收，
     *   但这里统一用int来接收
     */
    @org.junit.Test
    public void test() {
        int i = 1;
        int j = 4;
        int k = 0b1000;
        //注意下面的十六进制数0x0010，转换成二进制就是0000 0000 0001 0000，对应十进制的16
        int hex = 0x0010;
        int iresult = i << 4;
        System.out.println("int i = 1 << 4\r\n"+iresult);
        int jresult = j << 4;
        System.out.println("int j = 4 << 4\r\n"+jresult);
        int kresult = k << 1;
        System.out.println("int k = 0b1000 << 1\r\n"+kresult);
        int hexresult = hex << 1;
        System.out.println("int hex = 0x0010 << 1\r\n"+hexresult);
    }

    /**
     * java中有三种移位运算符

     <<      :     左移运算符，num << 1,相当于num乘以2

     >>      :     右移运算符，num >> 1,相当于num除以2

     >>>    :     无符号右移，忽略符号位，空位都以0补齐
     */
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
        System.out.println("i   = "+Integer.toBinaryString(i));
        System.out.println("j   = "+Integer.toBinaryString(j));
        System.out.println("i&j = "+Integer.toBinaryString(i&j));
        System.out.println("i|j = "+Integer.toBinaryString(i|j));
        System.out.println("i^j = "+Integer.toBinaryString(i ^ j));
        System.out.println("~k  = "+Integer.toBinaryString(~k));
    }
}
