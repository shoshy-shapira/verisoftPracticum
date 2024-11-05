package Verisoft.config;

import Verisoft.Browser.AutomationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";
    private static ConfigReader instance;

    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new AutomationException("Unable to find " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new AutomationException("Failed to load properties file", e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new AutomationException("Property '" + key + "' not found in configuration");
        }
        return value;


    }
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }


}
