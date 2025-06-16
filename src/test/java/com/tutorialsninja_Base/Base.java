package com.tutorialsninja_Base;

import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Base {

    public Properties prop;
    public Properties dataProp;
    public SoftAssert softAssert = new SoftAssert();

    public Base() {
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
//        FileInputStream fis = null;
        try {
            FileInputStream fis = new FileInputStream(propfile);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataProp = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData.properties");
        try {
            FileInputStream datafis = new FileInputStream(dataPropFile);
            dataProp.load(datafis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
