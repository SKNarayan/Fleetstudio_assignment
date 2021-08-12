package com.fleetstudio.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    public static Properties properties;

    public ReadConfig(){
        File src = new File("./configuration/config.properties");
        try{
            FileInputStream fileInputStream = new FileInputStream(src);
            properties = new Properties();
            properties.load(fileInputStream);

        }catch (Exception e){
            throw new RuntimeException("Error while reading the config file");
        }
    }

    public String getApplicationURL(){
        String base_Url = properties.getProperty("baseUrl");
        return base_Url;
    }

    public String getChromeDriverPath(){
        String path_chromeDriver = properties.getProperty("chromeDriverPath");
        return path_chromeDriver;
    }

    public String getFirefoxDriverPath(){
        String path_firefoxDriver = properties.getProperty("firefoxDriverPath");
        return path_firefoxDriver;
    }

}
