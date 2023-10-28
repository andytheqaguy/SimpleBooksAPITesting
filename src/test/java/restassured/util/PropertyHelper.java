package restassured.util;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class PropertyHelper {
    private static final String PROPERTY_FILE_NAME = "util.properties";

    private static void createPropertiesFileIfNotExists() {
        File file = new File(PROPERTY_FILE_NAME);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    writeVariableValue("counter", 0);
                    System.out.println("Created new util.properties file");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void writeVariableValue(String variable, String value) {
        createPropertiesFileIfNotExists();
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = configs.properties(PROPERTY_FILE_NAME);
            config.setProperty(variable, value);
            FileHandler fileHandler = new FileHandler(config);
            fileHandler.save(new File(PROPERTY_FILE_NAME));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeVariableValue (String variable, int value) {
        createPropertiesFileIfNotExists();
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = configs.properties(PROPERTY_FILE_NAME);
            config.setProperty(variable, value);
            FileHandler fileHandler = new FileHandler(config);
            fileHandler.save(new File(PROPERTY_FILE_NAME));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readVariableValueString (String variable) {
        createPropertiesFileIfNotExists();
        String value;
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = configs.properties(PROPERTY_FILE_NAME);
            value = config.getString(variable, "");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static int readVariableValueInt (String variable) {
        createPropertiesFileIfNotExists();
        int value;
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = configs.properties(PROPERTY_FILE_NAME);
            value = config.getInt(variable, 0);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static void incrementVariable (String variable) {
        createPropertiesFileIfNotExists();
        int counter = readVariableValueInt(variable);
        counter++;
        Configurations configs = new Configurations();
        try {
            PropertiesConfiguration config = configs.properties(PROPERTY_FILE_NAME);
            config.setProperty(variable, counter);
            FileHandler fileHandler = new FileHandler(config);
            fileHandler.save(new File(PROPERTY_FILE_NAME));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
