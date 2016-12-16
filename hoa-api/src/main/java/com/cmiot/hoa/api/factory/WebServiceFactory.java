package com.cmiot.hoa.api.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJL on 2016/11/17.
 */
public class WebServiceFactory {
    public static Map<String, Object> webServiceMap = new HashMap();

    public static Object getService(String name) {
        return webServiceMap.get(name);
    }

    public static void add(String name, Object service) {
        webServiceMap.put(name, service);
    }


}
