package restassured.util;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;

import java.io.File;

public class PropertyHelper {
    public static void writeVariableValue (String variable, String value) {
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = null;
            config = configs.properties("util.properties");
            config.setProperty(variable, value);
            FileHandler fileHandler = new FileHandler(config);
            fileHandler.save(new File("util.properties"));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeVariableValue (String variable, int value) {
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = null;
            config = configs.properties("util.properties");
            config.setProperty(variable, value);
            FileHandler fileHandler = new FileHandler(config);
            fileHandler.save(new File("util.properties"));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readVariableValueString (String variable) {
        String value;
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = configs.properties("util.properties");
            value = config.getString(variable, "");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static int readVariableValueInt (String variable) {
        int value;
        try {
            Configurations configs = new Configurations();
            PropertiesConfiguration config = configs.properties("util.properties");
            value = config.getInt(variable, 0);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static void incrementVariable (String variable) {
        int counter = readVariableValueInt(variable);
        counter++;
        Configurations configs = new Configurations();
        try {
            PropertiesConfiguration config = configs.properties("util.properties");
            config.setProperty(variable, counter);
            FileHandler fileHandler = new FileHandler(config);
            fileHandler.save(new File("util.properties"));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
