package org.mediacat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfigs {
    private static final Object LOCK = new Object();
    private static AppConfigs instance;

    private final Properties properties = new Properties();

    private interface Key {
        String name = "org.mediacat.app.name";
        String version = "org.mediacat.app.version";
    }

    public AppConfigs(InputStream is) {
        try {
            this.properties.load(is);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public String getAppName() {
        synchronized (LOCK) {
            return properties.getProperty(Key.name);
        }
    }

    public String getVersion() {
        synchronized (LOCK) {
            return properties.getProperty(Key.version);
        }
    }

    public static AppConfigs instance(InputStream is) {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new AppConfigs(is);
            }
        }
        return instance;
    }

    public static AppConfigs instance(String filePath) {
        Path p = Paths.get(filePath);
        AppConfigs appConfigs;
        try (InputStream is = Files.newInputStream(p)) {
            appConfigs = instance(is);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        return appConfigs;
    }
}
