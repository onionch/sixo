package com.onionch.webapp.website.staticless;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("tokenStatic")
public class TokenStatic {


    private static Map<String,Object> valueMap = new ConcurrentHashMap<String, Object>();

    public static  Map<String, Object> getValueMap() {
        return valueMap;
    }

    public static void setValueMap(Map<String, Object> valueMap) {
        valueMap = valueMap;
    }


}
