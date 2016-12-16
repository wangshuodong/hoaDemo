package com.cmiot.hoa.api;


import com.cmiot.hoa.api.base.LogBackUtil;
import com.cmiot.hoa.api.base.PropertiesUtil;
import com.cmiot.hoa.api.base.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 全局设定文件
 *
 * @author xiaoleilu
 */
public class ServerSetting {


    public static Logger logger = LoggerFactory.getLogger(ServerSetting.class);


    public static final int httpPort = PropertiesUtil.getPropertiesValueInt("http.port");
    public static Integer webservicePort = PropertiesUtil.getPropertiesValueInt("webservice.port");

    public static final int ioWorkerCount = PropertiesUtil.getPropertiesValueInt("io.worker.count");
    public static final int executorThreadCount = PropertiesUtil.getPropertiesValueInt("executor.thread.count");
    public static final int dubboPort = PropertiesUtil.getPropertiesValueInt("dubbo.port");
    public static final String zookeeperAddress = PropertiesUtil.getPropertiesValueStr("zookeeper.address");

    /**
     * 初始化设置
     */
    static {
        System.setProperty("app.properties", getFilePath("app.properties"));
        LogBackUtil.initLogBack();
        PropertiesUtil.initPropertiesConfigs();
        SpringContextUtil.initSptingConfigs();
    }

    /**
     * 获取外部的资源文件
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    private static String FILE_PATH = null;

    protected static String getFilePath(String fileName) {
        if (FILE_PATH == null) {
            String myPath = ServerSetting.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            FILE_PATH = new File(myPath).getParent() + File.separator;
        }
        return FILE_PATH + fileName;
    }


}
