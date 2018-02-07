package com.onionch.webapp.website.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by onionch on 17-1-6.
 */
public class PropertiesUtil {
    public static String get(String key) {
        Properties p = new Properties();
        try {
            InputStream in =PropertiesUtil.class.getClassLoader().getResourceAsStream("center.properties");
            p.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p.get(key).toString();
    }
}
