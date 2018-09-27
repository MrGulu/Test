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
        //写进去是ASCII码对应十进制的字符表示
//        fileOutputStream.write(120);
//        fileOutputStream.write(65);
        int a;
        //为什么第一个6写不进去？
        while ((a = fileInputStream.read()) != -1) {
            fileOutputStream.write(a);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void bufferedInputStreamTest() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("aa.txt"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("bb.txt", true));
        int b;
        while ((b = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(b);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
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
            fileWriter1.write(chars,0,ch);
            printWriter.write(chars,0,ch);
        }
        fileWriter1.flush();
        printWriter.flush();
        fileReader1.close();
        fileWriter1.close();
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
        byte[] bytes = new byte[50];
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
}
