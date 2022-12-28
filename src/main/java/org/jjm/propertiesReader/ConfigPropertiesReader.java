package org.jjm.propertiesReader;

import java.util.ResourceBundle;

public class ConfigPropertiesReader {

    private ConfigPropertiesReader() {
    }

    private static String getProperties(String key) {
        return ResourceBundle.getBundle("config").getString(key);
    }

    public static String getMalagaHotelDataPath() {
        return getProperties("malagaHotelDataPath");
    }

    public static String getTempStatisticsFilePath() {
        return getProperties("tempStatisticsPath");
    }
}
