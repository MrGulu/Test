package utils;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：YanZiheng：
 * @Description ：Apollo配置中心
 * @date ：2018-9-13
 */
public class ApolloUtil {
    private static final Logger logger = LoggerFactory.getLogger(ApolloUtil.class);

    //取不到配置的默认值
    private static final String DEFAULT_VALUE = "";
    //部门
    private static final String DEPARTMENT = "TEST1.";
    //接口的key
    private static final String HOST_CONFIG = "host.port";

    private static  Config config;

    /**
     * @author ：YanZiheng：
     * @Description ：获取默认namespace的配置
     * @date ：2018-9-13
     */
    public static String getConfig(String key) {
        logger.info("[getConfig]key="+key);
        config = ConfigService.getAppConfig();
        String result = config.getProperty(key, DEFAULT_VALUE);
        logger.info("value = " + result);
        return result;
    }


    /**
     * @author ：YanZiheng：
     * @Description ：获取其他namespace的配置
     * @date ：2018-9-13
     */
    public static String getConfig(String namespace,String key ){
        logger.info("[getConfig]namespace,key="+namespace+","+key);
        config = ConfigService.getConfig(namespace);
        String result =  config.getProperty(key, DEFAULT_VALUE);
        logger.info("value = " + result);
        return result;
    }

    /**
     * @author ：YanZiheng：
     * @Description ：获取为本系统提供服务的接口地址
     * @date ：2018-10-24
     */
    public static String getApiConfig(String project, String apiName) {
        logger.info("[getApiConfig]project,apiName="+project+","+apiName);
        config = ConfigService.getConfig(DEPARTMENT+project);
        String interfaceHost = config.getProperty(HOST_CONFIG, DEFAULT_VALUE);
        String interfacePath = config.getProperty(apiName, DEFAULT_VALUE);
        String result = interfaceHost + interfacePath;
        logger.info("value = " + result);
        return result;
    }



}


