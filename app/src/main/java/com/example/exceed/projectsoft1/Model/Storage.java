package com.example.exceed.projectsoft1.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by exceed on 4/14/16 AD.
 */
public class Storage {
    private static Map<String,Day> dayMap;
    private static Storage instance = null;
    private Storage(){
        dayMap = new HashMap<>();
    }
    public static Storage getInstance(){
        if(instance==null) instance = new Storage();
        return instance;
    }
    public double getTotal(){
        double result = 0;
        for(String key:dayMap.keySet()) {
            result += dayMap.get(key).getTotal();
        }
        return result;
    }
}
