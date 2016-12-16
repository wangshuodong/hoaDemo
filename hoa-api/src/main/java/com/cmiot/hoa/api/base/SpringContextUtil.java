package com.cmiot.hoa.api.base;

import com.cmiot.hoa.api.ServerSetting;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by zjial on 2016/6/3.
 */
public class SpringContextUtil extends ServerSetting implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;


    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }


    /**
     * 获取对象
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 初始化Spring配置文件
     */
    public static void initSptingConfigs() {
        String v1 = "file:" + getFilePath("dubbo-common.xml");
        String v2 = "file:" + getFilePath("dubbo-provider.xml");
        String v3 = "file:" + getFilePath("dubbo-consumer.xml");
        String v4 = "file:" + getFilePath("applicationcontext.xml");
        applicationContext = new FileSystemXmlApplicationContext(new String[]{v1, v2, v3, v4});
    }

}
