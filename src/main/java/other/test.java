package other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class test {
    public static final Logger logger = LoggerFactory.getLogger(test.class);
    public static void main(String[] args) {
//        String s = "admin";
//        System.out.println(Long.valueOf(s));
        Long l = 0L;
        System.out.println(l.equals(0L));
        logger.info("hello");

    }
}
