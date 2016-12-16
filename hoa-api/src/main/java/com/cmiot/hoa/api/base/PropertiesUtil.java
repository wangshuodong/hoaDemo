package com.cmiot.hoa.api.base;

import com.cmiot.hoa.api.ServerSetting;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by ZJL on 2016/7/21.
 */
public class PropertiesUtil extends ServerSetting {

    public static final Properties properties = new Properties();

    /**
     * 获取Properties
     */
    private static Properties getMyProperties() {
        synchronized (properties) {
            if (properties.isEmpty()) {
                initPropertiesConfigs();
            }
            return properties;
        }
    }

    /**
     * 初始化Properties文件
     */
    public static void initPropertiesConfigs() {
        try {
            String path = getFilePath("app.properties");
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            logger.info("初始化Properties文件,异常：{}", e);
        }
    }

    /**
     * 根据KEY获取Properties文件的值
     *
     * @param key
     * @return
     */
    public static String getPropertiesValueStr(String key) {
        return getMyProperties().getProperty(key);
    }

    /**
     * 根据KEY获取Properties文件的INT值
     *
     * @param key
     * @return
     */
    public static int getPropertiesValueInt(String key) {
        return Integer.valueOf(getMyProperties().getProperty(key));
    }

    /**
     * 根据KEY获取Properties文件的Boolean值
     *
     * @param key
     * @return
     */
    public static Boolean getPropertiesValueBoolean(String key) {
        return Boolean.valueOf(getMyProperties().getProperty(key));
    }
}
