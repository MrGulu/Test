package io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class FileStream {
    @Test
    public void creatFile() throws IOException {
        File file = new File("E:"+File.separator+"testFileInputStream.txt");
        File file1 = new File("E:"+File.separator+"testFileOutputStream.txt");
        //如果文件已经存在，返回的是false
        System.out.println(file.createNewFile());
        System.out.println(file1.createNewFile());
    }

    @Test
    public void fileInputStreamTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:"+File.separator+"testFileInputStream.txt");
        //append表示是否在文件为追加
        FileOutputStream fileOutputStream = new FileOutputStream("E:"+File.separator+"testFileOutputStream.txt", true);
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
        //为什么第一个写不进去？因为上面已经读了一次了，再读的时候是接着上次的继续读！
        while ((a = fileInputStream.read()) != -1) {
            fileOutputStream.write(a);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

//    创建一个 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。
//    创建一个内部缓冲区数组并将其存储在 buf 中,该buf的大小默认为8192。
//    public BufferedInputStream(InputStream in);
//
//    创建具有指定缓冲区大小的 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。
//    创建一个长度为 size 的内部缓冲区数组并将其存储在 buf 中。
//    public BufferedInputStream(InputStream in,int size);
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

    /*
    * 如何处理过时的StringBufferInputStream-->用下面的ByteArrayInputStream代替
    * */
    /*
    * 还需要注意一个问题，为什么要用UTF-8，本人的源程序是GBK格式的，但是用str.getBytes("GBK")就是不行，
    * 字符串显示不出来。经过反复试验，基本认定是因为源程序要编译后才运行，编译后，里面的字符串存储的格式全部是unicode了。
    * 所以需要用UTF-8
    * */
    @Test
    public void execute() throws Exception {
        StringBufferInputStream inputStream = new StringBufferInputStream("Hello World! This is a text string response from a Struts 2 Action.");
        String str = new String("中文stream");
        ByteArrayInputStream inputStreamNEW = new ByteArrayInputStream(str.getBytes("UTF-8"));
        System.out.println(inputStreamNEW.toString());
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
        /*
         在上面写入到ByteArrayOutputStream之后，用toByteArray转化为字节数组并用一个
         新的字节数组接收
        */
        bytes1 = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(bytes1));

        String str = "tang";
        ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(str.getBytes("UTF-8"));
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        int ch;
        while ((ch = byteArrayInputStream1.read()) != -1) {
            byteArrayOutputStream1.write(ch);
        }
        //在上面写入到ByteArrayOutputStream之后，用toString转化为字符串并输出
        System.out.println(byteArrayOutputStream1.toString());
        byteArrayInputStream.close();
        byteArrayOutputStream.close();
        byteArrayInputStream1.close();
        byteArrayOutputStream1.close();
    }

    @Test
    public void dataInputStreamTest() throws IOException {
        byte b = 65;
        short s = 66;
        int i = 67;
        long l = 68;
//        float f = 69;
//        double d = 70;
//        boolean b1 = true;
//        char c = '中';
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("dataStreamTest.txt"));
        dataOutputStream.writeByte(b);
        dataOutputStream.writeShort(s);
        dataOutputStream.writeInt(i);
        dataOutputStream.writeLong(l);
//        dataOutputStream.writeFloat(f);
//        dataOutputStream.writeDouble(d);
//        dataOutputStream.writeBoolean(b1);
//        dataOutputStream.writeChar(c);
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("dataStreamTest.txt"));
        byte rb = dataInputStream.readByte();
        short rs = dataInputStream.readShort();
        int ri = dataInputStream.readInt();
        long rl = dataInputStream.readLong();
//        float rf = dataInputStream.readFloat();
//        double rd = dataInputStream.readDouble();
//        boolean rb1 = dataInputStream.readBoolean();
//        char rc = dataInputStream.readChar();
        System.out.println("输出：\r\n" + rb + "\t" + rs + "\t" + ri + "\t" + rl);
//        System.out.println("输出：\r\n"+rb+"\t"+rs+"\t"+ri+"\t"+rl+"\t"+rf+"\t"+rd+"\t"+rb1+"\t"+rc);
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
    public void inputStreamReaderTest() throws IOException {
//        使用new FileOutputStream时，如果不存在文件，则会创建
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("InputStreamReaderTestIn.txt"));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("OutputStreamWriterTestOut.txt"));
        int b;
        while ((b = inputStreamReader.read()) != -1) {
            outputStreamWriter.write(b);
        }
        outputStreamWriter.flush();
        inputStreamReader.close();
        outputStreamWriter.close();
    }


    /*
    * 当你有一组应用程序接口（API）只允许用Writer或Reader作为输入，但你又想使用String，
    * 这时可以用StringWriter或StringReader。
    * Writer类输出数据到一个文件；不过有时候数据必须保留在内存中，比如想在数据被输出以前在图形用户界面（GUI）上
    * 先显示一下，这时可以用StringWriter；
    * StringWriter不是把数据写到某种形式的输出设备上，相反，它写到内存中。
    * */
    @Test
    public void stringReaderTest() throws IOException {
        StringReader stringReader = new StringReader("" +
                "                   _ooOoo_\n" +
                "                  o8888888o\n" +
                "                  88\" . \"88\n" +
                "                  (| -_- |)\n" +
                "                  O\\  =  /O\n" +
                "               ____/`---'\\____\n" +
                "             .'  \\\\|     |//  `.\n" +
                "            /  \\\\|||  :  |||//  \\\n" +
                "           /  _||||| -:- |||||-  \\\n" +
                "           |   | \\\\\\  -  /// |   |\n" +
                "           | \\_|  ''\\---/''  |   |\n" +
                "           \\  .-\\__  `-`  ___/-. /\n" +
                "         ___`. .'  /--.--\\  `. . __\n" +
                "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
                "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
                "======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "                   `=---='\n" +
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "         佛祖保佑       永无BUG\n" +
                "\n" +
                "---------------------------------------------\n");
        StringWriter stringWriter = new StringWriter();
        char[] chars = new char[1024];
        int b;
        while ((b = stringReader.read(chars)) != -1) {
            stringWriter.write(chars, 0, b);
        }
//        System.out.println(stringWriter.toString());
//        toString产生一个字符串，getBuffer获得一个StringBuffer对象
        System.out.println(stringWriter.getBuffer().toString());
        stringWriter.flush();
        stringReader.close();
        stringWriter.close();
    }

    /*
    * ByteArrayReader没有？
    * */

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
