package File;

import org.junit.Test;

import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {
    public static void main(String[] args) throws IOException {
        FileTest fileTest = new FileTest();
        File file = new File("cc.txt");
        System.out.println(file.exists());
        boolean b = file.createNewFile();
        System.out.println(b);
        System.out.println(file.exists());
        System.out.println(file.getName());//获取文件的名字
        System.out.println(file.getAbsolutePath());//获取文件的绝对路径
        System.out.println(file.getPath());//获取文件的相对路径
        System.out.println(file.getParent());//获取文件的父路径
        System.out.println(file.canRead());//文件是否可读
        System.out.println(file.canWrite());//文件是否可写
        System.out.println(file.length());//文件的长度
        System.out.println("毫秒显示修改时间：" + file.lastModified());//文件最后一次修改的时间
        System.out.println("转换后显示修改时间：" + fileTest.DateConverter(file.lastModified()));
        System.out.println(file.isDirectory());//判断文件是否是一个目录
        System.out.println(file.isHidden());//文件是否隐藏
        System.out.println(file.isFile());//判断文件是否存在

//        file.delete();
        System.out.println(file.exists());

        fileTest.fileNameList("E:\\");
//        fileTest.listFiles("D:\\workspace\\BigDecimalAboutRound\\");
//        fileTest.listFiles("D:"+File.separator+"workspace"+File.separator+"BigDecimalAboutRound");
        /**
         * 与系统有关的路径分隔符。此字段被初始为包含系统属性 path.separator 值的第一个字符。
         * 此字符用于分隔以路径列表 形式给定的文件序列中的文件名。在 UNIX 系统上，此字段为 ':'；
         * 在 Microsoft Windows 系统上，它为 ';'。
         */
        fileTest.listFiles("E:"+File.separator+"workspace"+File.separator+"conf_home"+File.separator+"mdd-conf");
    }

    public String DateConverter(long time) {
//        long tmp_time = time + 5000*1000;
        long tmp_time = time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        Date date = new Date(tmp_time);
        return simpleDateFormat.format(date);
    }

    public void fileNameList(String s) {
        File file = new File(s);
        String[] filelist = file.list();
        /*只是名字，不可以操作，而下面函数的listFiles，
        是相当于在那个路径下每一个文件都执行File file = new File("...");方法；产生的是file对象，可以操作的。*/
        System.out.println("获取指定目录下的所有文件或者文件夹的名称数组");
        for (String ss :
                filelist) {
            System.out.println(ss);
        }
    }

    public void listFiles(String s) {
        File file = new File(s);
        File[] files = file.listFiles();
        System.out.println("获取指定目录下的所有文件或者文件夹的File数组");
        for (File f :
                files) {
            System.out.println(f);
        }
    }

    /**
     * 通过下面的test1就可以知道为什么前面的文件都直接写文件名就可以取到File对象
     */
    @Test
    public void test1() {
//        我们可以通过读取user.dir系统属性来获取JVM的当前工作目录,然后通过代码建立新文件时，都会在这个工作目录下
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
    }

    /**
     * 在使用相对路径创建File对象时，即使这个文件不存在也不会报错
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        File file = new File("test2.txt");
        System.out.println(file.exists());
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println(file.isFile());
        file.renameTo(new File("test2Rename.txt"));
    }

    /**
     * 在使用绝对路径创建File对象时，即使这个文件不存在也不会报错
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        File file = new File("E:"+File.separator+"test3.txt");
        System.out.println(file.exists());
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println(file.isFile());
        file.renameTo(new File("test2Rename.txt"));
    }

    /**
     * 1.当路径存在时，即使文件不存在也不会报错；
     * 2.当路径不存在时，如果文件上面只有一级目录不存在，使用File.mkdir()方法创建父目录
     * 3.当路径不存在时，如果文件上面有多级目录不存在，使用File.mkdirs()方法创建多级父目录
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        File file = new File("E:"+File.separator+"testDirectory2"+File.separator+"testDirectory2"+File.separator+"test3.txt");
        System.out.println(file.exists());
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        System.out.println(file.isFile());
        file.renameTo(new File("test2Rename.txt"));
    }

    /**
     * 1.当路径存在，文件不存在时，File file = new File(String path)不会报错；
     * 2.当路径不存在时，File file = new File(String path)会报错：文件路径不存在（IOException）
     * 2.当路径不存在时，如果文件上面只有一级目录不存在，使用File.mkdir()方法
     * 3.当路径不存在时，如果文件上面有多级目录不存在，使用File.mkdirs()方法
     * 4.当构建输入输出流对象时，需要声明FileNotFoundException，这时如果之前在
     *   File file = new File(String path)时，并未判断是否存在，然后创建目录
     *   或文件时，InputStream in = new FileInputStream(File file)就会报错
     *   ：FileNotFoundException

     * @throws IOException
     */
    @Test
    public void test5() throws FileNotFoundException {
        File file = new File("E:"+File.separator+"testDirectory2"+File.separator+"testDirectory2"+File.separator+"test3.txt");
        System.out.println(file.exists());
        InputStream in = new FileInputStream(file);
    }
}
