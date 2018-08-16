package File;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {
    public static void main(String[] args) throws IOException {
        FileTest fileTest = new FileTest();
        File file = new File("bb.txt");
        System.out.println(file.exists());
        file.createNewFile();
        System.out.println(file.exists());
        System.out.println(file.getName());//获取文件的名字
        System.out.println(file.getAbsolutePath());//获取文件的绝对路径
        System.out.println(file.getPath());//获取文件的相对路径
        System.out.println(file.getParent());//获取文件的父路径
        System.out.println(file.canRead());//文件是否可读
        System.out.println(file.canWrite());//文件是否可写
        System.out.println(file.length());//文件的长度
        System.out.println("毫秒显示修改时间："+file.lastModified());//文件最后一次修改的时间
        System.out.println("转换后显示修改时间："+fileTest.DateConverter(file.lastModified()));
        System.out.println(file.isDirectory());//判断文件是否是一个目录
        System.out.println(file.isHidden());//文件是否隐藏
        System.out.println(file.isFile());//判断文件是否存在

        file.delete();
        System.out.println(file.exists());

        fileTest.fileNameList("D:\\");
        fileTest.listFiles("D:\\workspace\\Test\\");
    }
    public String DateConverter(long time){
//        long tmp_time = time + 5000*1000;
        long tmp_time = time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        Date date = new Date(tmp_time);
        return simpleDateFormat.format(date);
    }
    public void fileNameList(String s){
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
}
