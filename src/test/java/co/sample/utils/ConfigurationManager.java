package co.sample.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private Properties prop = new Properties();

    private ConfigurationManager(String configFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(configFile);
        prop.load(inputStream);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static ConfigurationManager getInstance() throws IOException {
        if (instance == null) {
            String configFile = getPropertyFilePath() + File.separator + "config.properties";
            instance = new ConfigurationManager(configFile);
        }
        return instance;
    }

    private static String getPropertyFilePath() {
        String propFilePath = "";
        String currentDir = System.getProperty("user.dir");
        propFilePath = currentDir + File.separator + "caps";
        return propFilePath;
    }
}
