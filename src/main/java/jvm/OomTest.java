package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM options：
 * <p>
 * -Xms50m
 * -Xmx50m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:-OmitStackTraceInFastThrow
 * -Dfile.encoding=UTF-8
 * -XX:ErrorFile=C:\Users\Administrator\Desktop\java_error_in_idea_%p.log
 * -XX:HeapDumpPath=C:\Users\Administrator\Desktop\java_error_in_idea.hprof
 */

public class OomTest {

    public static void main(String[] args) {
        int size = 1024 * 1024;
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            System.out.println("JVM 写入数据" + (i + 1) + "M");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new byte[size]);
        }
    }
}
