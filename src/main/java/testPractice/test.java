package testPractice;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class test {

    public static final Logger logger = LoggerFactory.getLogger(test.class);

    @Test
    public void test1() throws IOException {
        String fileName = "/testReadProperties.properties";
        Properties properties = System.getProperties();
        String home = properties.getProperty("CONF_HOME");
        logger.info("home:" + home);
        InputStream inputStream = null;
        Properties readProperties = new Properties();
        try {
            inputStream = new FileInputStream(home + "\\testReadProperties.properties");
            if ("/testReadProperties.properties".equals(fileName)) {
                //默认的编码就是UTF-8，这里写不写都行，utf-8也可以
                readProperties.load(new InputStreamReader(inputStream, "utf-8"));
            } else {
                readProperties.load(inputStream);
            }
            String value = readProperties.getProperty("testKey");
            logger.info("value:" + value);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
