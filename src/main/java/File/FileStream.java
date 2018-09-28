package File;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class FileStream {
    @Test
    public void creatFile() throws IOException {
        File file = new File("bufferedReaderTestIn.txt");
        File file1 = new File("bufferedReaderTestOut.txt");
        System.out.println(file.createNewFile());
        System.out.println(file1.createNewFile());
    }

    @Test
    public void fileInputStreamTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("aa.txt");
        //append表示是否在文件为追加
        FileOutputStream fileOutputStream = new FileOutputStream("bb.txt", true);
        int test = fileInputStream.read();
        //读出来的是ASCII码对应字符的十进制表示
        System.out.println(test);
        //强转之后读出来的就是字符
        System.out.println((char) test);
        byte[] bytes = new byte[]{(byte) test, 32};
        //A B C
        byte[] bytes1 = {65, 32, 66, 32, 67, 32};
        /*
        FileOutputStream.write(byte[] bytes)写入文件的是二进制码，
        你写入二进制1和0是不可见字符，必须用二进制/16进制文件格式打开才可以看到
        * */
        fileOutputStream.write(bytes);
        fileOutputStream.write(bytes1);
        //写进去是ASCII码对应十进制的字符表示
        fileOutputStream.write(120);
        fileOutputStream.write(32);
        fileOutputStream.write(65);
        fileOutputStream.write(32);
        //跟上面的结果是一样的
        fileOutputStream.write((char) 120);
        fileOutputStream.write((char) 32);
        fileOutputStream.write((char) 65);
        fileOutputStream.write((char) 32);
        int a;
        //为什么第一个6写不进去？因为上面已经读了一次了，再读的时候是接着上次的继续读！
        while ((a = fileInputStream.read()) != -1) {
            fileOutputStream.write(a);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    /*创建一个 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。
    创建一个内部缓冲区数组并将其存储在 buf 中,该buf的大小默认为8192。
    public BufferedInputStream(InputStream in);

    创建具有指定缓冲区大小的 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。
    创建一个长度为 size 的内部缓冲区数组并将其存储在 buf 中。
    public BufferedInputStream(InputStream in,int size);*/
    @Test
    public void bufferedInputStreamTest() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("aa.txt"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("bb.txt", true));
        //下面是使用read()方法
//        int b;
//        while ((b = bufferedInputStream.read()) != -1) {
//            bufferedOutputStream.write(b);
//        }
        //下面是使用read(byte[] b,int off,int len)方法
        byte[] bytes = new byte[2000];
        int b;
        //下面read()和read(bytes)都可以！如果不加bytes，使用的是默认的内部缓冲数组吗？
        while ((b = bufferedInputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes, 0, b);
        }
        bufferedOutputStream.flush();
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    @Test
    public void byteArrayInputStreamTest() throws IOException {
        byte[] bytes = new byte[]{1,2,3,4,5};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        byte[] bytes1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int b;
        while ((b = byteArrayInputStream.read()) != -1) {
            byteArrayOutputStream.write(b);
        }
        bytes1 = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(bytes1));

        String str = "tang";
        ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(str.getBytes("UTF-8"));
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        int ch = 0;
        while ((ch = byteArrayInputStream1.read()) != -1) {
            byteArrayOutputStream1.write(ch);
        }
        System.out.println(byteArrayOutputStream1.toString());
    }

    /**
     * SequenceInputStream合并流，将与之相连接的流集组合成一个输入流并从第一个输入流开始读取，
     * 直到到达文件末尾，接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
     * 合并流的作用是将多个源合并合一个源。可接收枚举类所封闭的多个字节流对象。
     */
    @Test
    public void sequenceInputStreamTest() throws IOException {
        SequenceInputStream sis;
        BufferedOutputStream bos;
        //这里如果使用List<InputStream> vector = new Vector<>();这样，不会有addElement这个方法！！！
        Vector<InputStream> vector = new Vector<>();
        vector.addElement(new FileInputStream("c.txt"));
        vector.addElement(new FileInputStream("cc.txt"));
        Enumeration<InputStream> e = vector.elements();
        sis = new SequenceInputStream(e);
        bos = new BufferedOutputStream(new FileOutputStream("sequenceStreamTestOut.txt"));
        byte[] bytes = new byte[2000];
        int len;
        while ((len = sis.read(bytes)) != -1) {
            //b 这一次写的数据
            //off 这次从b的第off开始写
            //len 这次写的长度
            bos.write(bytes, 0, len);
            bos.flush();
        }
        sis.close();
        bos.close();
    }

    @Test
    public void bufferedReaderTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("bufferedReaderTestIn.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bufferedReaderTestOut.txt")));
/*
        如果用下面这种方式，写入到目标文件中时，所有的数据都在一行，跟原来文件中
        内容布局就不一样了
*/
//        String s;
//        while ((s = bufferedReader.readLine()) != null) {
//            bufferedWriter.write(s);
//        }
        int b;
        while ((b = bufferedReader.read()) != -1) {
            bufferedWriter.write(b);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    @Test
    public void fileReaderTest() throws IOException {
//        FileReader fileReader = new FileReader("aaa.txt");
//        int c;
//        while ((c = fileReader.read()) != -1) {
//            System.out.print((char) c);
//        }
//        fileReader.close();
//
//        FileWriter fileWriter = new FileWriter("bbb.txt", true);
//        fileWriter.write("aaa");
//        fileWriter.close();

        //字符流的拷贝
        /*
        * 使用FileWriter写入文件；使用PrintWriter打印到控制台！
        * PrintWriter可以输出字符到文件，也可以输出到控制台
        * */
        FileReader fileReader1 = new FileReader("FileReaderTestIn.txt");
        FileWriter fileWriter1 = new FileWriter("FileReaderTestOut.txt", true);
        PrintWriter printWriter = new PrintWriter(System.out);
        char[] chars = new char[100];
        int ch;
        while ((ch = fileReader1.read(chars)) != -1) {
            fileWriter1.write(chars, 0, ch);
            printWriter.write(chars, 0, ch);
        }
        fileWriter1.flush();
        printWriter.flush();
        fileReader1.close();
        fileWriter1.close();
    }

}
