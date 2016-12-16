package com.cmiot.hoa.api;

import com.cmiot.hoa.api.factory.WebServiceFactory;
import com.cmiot.hoa.api.resource.IndexResource;
import com.cmiot.hoa.api.resource.PBossResource;
import com.cmiot.hoa.api.resource.app.AppResource;
import com.cmiot.hoa.api.resource.security.OauthResource;
import com.cmiot.hoa.api.resource.security.OneTwoResource;
import com.cmiot.hoa.api.resource.security.SecurityResource;
import com.cmiot.hoa.api.resource.security.SecurityV2Resource;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/7.
 */
public class HoaMain {
    private static Logger logger = LoggerFactory.getLogger(HoaMain.class);

    public static void main(String[] args) throws UnknownHostException {
        NettyJaxrsServer netty = new NettyJaxrsServer();
        netty.setPort(ServerSetting.httpPort);
        netty.setDeployment(getResteasyDeployment());
        netty.setIdleTimeout(60);
        netty.setIoWorkerCount(ServerSetting.ioWorkerCount);
        netty.setExecutorThreadCount(ServerSetting.executorThreadCount);
        netty.setRootResourcePath("");
        netty.start();
        initWebServer();
        logger.info("HOA 启动成功☺☺☺☺☺");
    }

    private static final ResteasyDeployment getResteasyDeployment() {
        final ResteasyDeployment resteasyDeployment = new ResteasyDeployment();
        resteasyDeployment.setActualResourceClasses(getActualProviderClasses());
        return resteasyDeployment;
    }

    private static final List<Class> getActualProviderClasses() {
        final List<Class> actualProviderClasses = new ArrayList<>();
        actualProviderClasses.add(IndexResource.class);
        actualProviderClasses.add(AppResource.class);
        actualProviderClasses.add(OauthResource.class);
        actualProviderClasses.add(SecurityResource.class);
        actualProviderClasses.add(PBossResource.class);
        actualProviderClasses.add(OneTwoResource.class);
        actualProviderClasses.add(SecurityV2Resource.class);
        return actualProviderClasses;
    }


    private static void initWebServer() throws UnknownHostException {
        if (WebServiceFactory.webServiceMap.size() > 0) {
            for (Map.Entry<String, Object> entry : WebServiceFactory.webServiceMap.entrySet()) {
                Endpoint.publish(setWebServerAddress(entry.getKey()), entry.getValue());
            }
        }
    }


    private static String WEB_SERVICE_URL = null;

    private static String setWebServerAddress(String name) throws UnknownHostException {
        if (StringUtils.isBlank(WEB_SERVICE_URL)) {
            String ip = InetAddress.getLocalHost().getHostAddress();
            if (ServerSetting.webservicePort == null) {
                ServerSetting.webservicePort = 9097;
            }
            WEB_SERVICE_URL = "http://" + ip + ":" + ServerSetting.webservicePort + "/" + name;
        }
        return WEB_SERVICE_URL;
    }

}
