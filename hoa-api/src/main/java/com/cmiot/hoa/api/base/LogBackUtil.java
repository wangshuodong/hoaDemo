package com.cmiot.hoa.api.base;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import com.cmiot.hoa.api.ServerSetting;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by zjial on 2016/5/25.
 */
public class LogBackUtil extends ServerSetting {
    /**
     * 初始化LOGBACK_CONFIGS
     *
     * @throws Exception
     */
    public static void initLogBack() {
        try {
            String externalConfigFileLocation = getFilePath("logback.xml");
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            File externalConfigFile = new File(externalConfigFileLocation);
            if (!externalConfigFile.exists()) {
                throw new IOException("Logback External Config File Parameter does not reference a file that exists");
            } else {
                if (!externalConfigFile.isFile()) {
                    throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
                } else {
                    if (!externalConfigFile.canRead()) {
                        throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
                    } else {
                        JoranConfigurator configurator = new JoranConfigurator();
                        configurator.setContext(lc);
                        lc.reset();
                        configurator.doConfigure(externalConfigFileLocation);
                        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
                    }
                }
            }
        } catch (Exception e) {

        }

    }
}
