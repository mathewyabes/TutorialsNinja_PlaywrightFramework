package com.tutorialsninja_Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Base {

    public Properties prop;

    public Base() {
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(propfile);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
