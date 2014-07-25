package br.com.supportcomm.mktcall.util;

import java.io.IOException;
import java.util.Properties;

public final class Configuration {
    private static final Properties properties = new Properties();

    static {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("resources/config.properties"));
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String getSetting(String key) {
        return properties.getProperty(key);
    }
}
