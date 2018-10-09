package other;

import java.io.*;


public class DeleteStrDemo {

    private static int count;

    public static void main(String[] args) throws IOException {
        File dir = new File("D:\\test");
        deleteStrPro(dir);
        System.out.println("去除成功！");
        System.out.println("共去除" + count + "行");
    }

    private static void deleteStrPro(File dir) throws IOException {
        if (dir.isDirectory()) {//判断是否是文件夹
            File[] files = dir.listFiles();
            if (null != files) {
                for (File file : files) {
                    if (!file.isDirectory()) {
                        removeStrCore(file);//核心处理方法
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
        BufferedReader bufferReader = null;//读缓冲器
        BufferedWriter bufferWriter = null;//写缓冲器
        try {
            //注意，这个地方test2的目录是必须要有的，文件里可以是空的，它会自动创建！
            //由于没有append=true，所以就算有文件也会将内容覆盖
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("D:\\test2" + File.separator + file.getName()), "UTF-8");//指定写编码
            InputStreamReader in = new InputStreamReader(new FileInputStream(file), "GBK");//指定读编码
            bufferReader = new BufferedReader(in);//读缓冲器
            bufferWriter = new BufferedWriter(out);//写缓冲器
            while (bufferReader.ready()) {
                String line = bufferReader.readLine();
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
