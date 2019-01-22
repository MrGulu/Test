package property;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Properties;

public class PropertyTest {
    /**
     * 读取类路径下
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        //读的是classpath下的文件
        InputStream in = PropertyTest.class.getResourceAsStream("/read.properties");
        Properties properties = new Properties();
        properties.load(in);
        String value = properties.getProperty("testKey");
        System.out.println(value);
        in.close();
    }

    /**
     * 最常用方式
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        File file = new File("C:" + File.separator + "Users" + File.separator + "Administrator" + File.separator + "Desktop" + File.separator + "testReadProperties.properties");
        InputStream in = new FileInputStream(file);
        //在配置文件中存在汉字的情况下，还需要再将InputStream封装一次，使用UTF-8编码格式！
        InputStreamReader reader = new InputStreamReader(in,"UTF-8");
        Properties properties = new Properties();
        properties.load(reader);
        String value = properties.getProperty("testKey");
        System.out.println(value);
        in.close();
    }

    /**
     * 使用spring的类先读取资源文件
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        File file = new File("C:" + File.separator + "Users" + File.separator + "Administrator" + File.separator + "Desktop" + File.separator + "testReadProperties.properties");
        Resource resource = new FileSystemResource(file);
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(),"UTF-8");
        Properties properties = new Properties();
        properties.load(reader);
        String value = properties.getProperty("testKey");
        System.out.println(value);
    }
}
