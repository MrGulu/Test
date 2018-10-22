package other;

import java.io.*;

/**
* @Description:    将导出的sql文件去除records committed...和commit;行
* @Author:         tangwenlong
* @CreateDate:     2018/10/22 15:58
*/
public class DeleteStrDemo {

    private static int count;

    public static void main(String[] args) throws IOException {
        File dir = new File("D:\\test");
        deleteStrPro(dir);
        System.out.println("去除成功！");
        System.out.println("共去除" + count + "行");
    }

    private static void deleteStrPro(File dir) throws IOException {
        if (dir.isDirectory()) {
            //判断是否是文件夹
            File[] files = dir.listFiles();
            if (null != files) {
                for (File file : files) {
                    if (!file.isDirectory()) {
                        //核心处理方法
                        removeStrCore(file);
                    }
                }
            }
        }
    }

    /**
     * 去除指定行的核心方法
     *
     * @param file
     */
    private static void removeStrCore(File file) {
        //读缓冲器
        BufferedReader bufferReader = null;
        //写缓冲器
        BufferedWriter bufferWriter = null;
        try {
            //注意，这个地方test2的目录是必须要有的，文件里可以是空的，它会自动创建！
            //由于没有append=true，所以就算有文件也会将内容覆盖
            //指定写编码
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("D:\\test2" + File.separator + file.getName()), "UTF-8");
            //指定读编码
            InputStreamReader in = new InputStreamReader(new FileInputStream(file), "GBK");
            //读缓冲器
            bufferReader = new BufferedReader(in);
            //写缓冲器
            bufferWriter = new BufferedWriter(out);
            while (bufferReader.ready()) {
                String line = bufferReader.readLine();
                //如果包含要去除的消息，继续读下一行，不写入？
                if (line.contains("records committed...") || line.equals("commit;")) {
                    count++;
                    continue;
                }
                bufferWriter.write(line);
                bufferWriter.newLine();
                bufferWriter.flush();//刷新缓冲区
            }
            bufferReader.close();//关闭流
            bufferWriter.close();
        } catch (IOException e) {
        } finally {
            try {
                if (null != bufferReader) {
                    bufferReader.close();
                }
                if (null != bufferWriter) {
                    bufferWriter.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
