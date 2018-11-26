package utils;

import com.ctrip.framework.apollo.*;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.model.ConfigFileChangeEvent;
import com.ctrip.framework.foundation.Foundation;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class ApolloConfigUtil {

  private String DEFAULT_VALUE = "undefined";
  private Config config;
  private Config publicConfig;
  private ConfigFile applicationConfigFile;
  private ConfigFile xmlConfigFile;

  public ApolloConfigUtil() {
    ConfigChangeListener changeListener = new ConfigChangeListener() {
      @Override
      public void onChange(ConfigChangeEvent changeEvent) {
        System.out.println("Changes for namespace "+changeEvent.getNamespace());
        for (String key : changeEvent.changedKeys()) {
          ConfigChange change = changeEvent.getChange(key);
          System.out.println("Change - key: "+change.getNamespace()+", oldValue: "+change.getOldValue()+"" +
                  ", newValue: "+change.getNewValue()+", changeType: "+change.getChangeType()+"");
        }
      }
    };
    config = ConfigService.getAppConfig();
    config.addChangeListener(changeListener);
    publicConfig = ConfigService.getConfig("TEST1.zhangrenli");
    publicConfig.addChangeListener(changeListener);
    applicationConfigFile = ConfigService.getConfigFile("application", ConfigFileFormat.Properties);
    xmlConfigFile = ConfigService.getConfigFile("datasources", ConfigFileFormat.XML);
    xmlConfigFile.addChangeListener(new ConfigFileChangeListener() {
      @Override
      public void onChange(ConfigFileChangeEvent changeEvent) {
        System.out.println(changeEvent.toString());
      }
    });
  }

  public String getConfig(String key) {
    printEnvInfo();
    String result = config.getProperty(key, DEFAULT_VALUE);
    if (DEFAULT_VALUE.equals(result)) {
      result = publicConfig.getProperty(key, DEFAULT_VALUE);
    }
    System.out.println(String.format("Loading key : %s with value: %s", key, result));
    return result;
  }

  private void print(String namespace) {
    if (namespace.equals("application")) {
      print(applicationConfigFile);
      return;
    } else if (namespace.equals("xml")) {
      print(xmlConfigFile);
      return;
    }
  }

  private void print(ConfigFile configFile) {
    if (!configFile.hasContent()) {
      System.out.println("No config file content found for " + configFile.getNamespace());
      return;
    }
    System.out.println("=== Config File Content for " + configFile.getNamespace() + " is as follows: ");
    System.out.println(configFile.getContent());
  }

  private void printEnvInfo() {
    String message = String.format("AppId: %s, Env: %s, DC: %s, IP: %s", Foundation.app()
        .getAppId(), Foundation.server().getEnvType(), Foundation.server().getDataCenter(),
        Foundation.net().getHostAddress());
    System.out.println(message);
  }

  public  void getFile(String input) {

      if (input.equalsIgnoreCase("application")) {
        print("application");
      }
      if (input.equalsIgnoreCase("xml")) {
        print("xml");
      }

    }

}
