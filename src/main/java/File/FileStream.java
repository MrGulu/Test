package File;

import java.io.*;

public class FileStream {
    public static void main(String[] args) throws IOException {
        FileStream fileStream = new FileStream();
//        fileStream.aVoid();
//        fileStream.bVoid();
        fileStream.cVoid();
    }
    public void aVoid() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("aa.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("bb.txt",true);//append表示是否在文件为追加
        int test = fileInputStream.read();
        System.out.println(test);        //读出来的是ASCII码对应字符的十进制表示
        System.out.println((char)test);  //强转之后读出来的就是字符
        //写进去是ASCII码对应十进制的字符表示
        fileOutputStream.write(120);
        fileOutputStream.write(65);
        fileInputStream.close();
        fileOutputStream.close();
    }
    public void bVoid() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("aaa.txt"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("bbb.txt"));
        int b;
        while ((b = bufferedInputStream.read())!= -1) {
            bufferedOutputStream.write(b);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
    public void cVoid() throws IOException {
        FileReader fileReader = new FileReader("aaa.txt");
        int b;
        while ((b = fileReader.read()) != -1) {
            System.out.println((char)b);
        }
        fileReader.close();

        FileWriter fileWriter = new FileWriter("bbb.txt",true);
        fileWriter.write("aaa");
        fileWriter.close();

        //字符流的拷贝
        FileReader fileReader1 = new FileReader("aaa.txt");
        FileWriter fileWriter1 = new FileWriter("bbb.txt");
        int ch;
        while ((ch = fileReader1.read()) != -1) {
            fileWriter1.write(ch);
        }
        fileReader1.close();
        fileWriter1.close();
    }
}
