package com.cmiot.hoa.api.resource;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.base.SpringContextUtil;
import com.cmiot.hoa.api.resource.order.MethodExecutionOrder;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/7.
 */
public class BaseV2Resource {

    private static Logger logger = LoggerFactory.getLogger(BaseV2Resource.class);


    public static final String newGid() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date(System.currentTimeMillis())) + RandomStringUtils.randomNumeric(9);
    }


    private static final String RESULT_CODE = "resultCode";
    private static final String RESULT_MESSAGE = "errorMsg";


    public static final Map<String, Object> buildMap(int key, String msg) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(RESULT_CODE, key);
        objectMap.put(RESULT_MESSAGE, msg);
        return objectMap;
    }


    /**
     * 获取i消费者IP地址
     *
     * @param context
     * @return
     */
    public static final String getConsumerIp(ChannelHandlerContext context) {
        String ip = null;
        try {
            InetSocketAddress address = (InetSocketAddress) context.channel().remoteAddress();
            ip = address.getAddress().getHostAddress();
        } catch (Exception e) {

        }
        return ip;
    }


    /**
     * 方法执行
     *
     * @param executionOrder
     * @return
     */
    public static Map<String, Object> methodExecution(MethodExecutionOrder executionOrder) {
        String gid = executionOrder.getGid();
        String consumerIp = executionOrder.getConsumerIp();
        String serviceName = executionOrder.getServiceName().trim();
        String methodName = executionOrder.getMethodName().trim();
        JSONObject parameters = executionOrder.getJsonObject();
        Map<String, Object> resultObjectMap = null;
        try {
            logger.info("【GID={}】消费者ip={}调用服务【{}.{}】入参:{}", gid, consumerIp, serviceName, methodName, parameters);
            resultObjectMap = invokeMethod(serviceName, methodName, parameters);
            logger.info("【GID={}】消费者ip={}调用业务【{}.{}】出参:{}", gid, consumerIp, serviceName, methodName, resultObjectMap);
        } catch (Exception e) {
            logger.info("【GID={}】消费者ip={}调用服务【{}.{}】异常:{}", gid, consumerIp, serviceName, methodName, e);
            resultObjectMap = buildMap(500, "调用服务" + serviceName + "." + methodName + "异常");
        }
        return resultObjectMap;
    }


    /**
     * 调用服务方法
     *
     * @param serviceName
     * @param methodName
     * @param jsonObject
     * @return
     * @throws Exception
     */
    public static Map<String, Object> invokeMethod(String serviceName, String methodName, JSONObject jsonObject) throws Exception {
        if (jsonObject == null) {
            Object service = SpringContextUtil.getBean(serviceName);
            if (service == null) {
                return buildMap(70006, "服务" + serviceName + "不存在！");
            }
            Method method = ReflectionUtils.findMethod(service.getClass(), methodName);
            if (method == null) {
                return buildMap(70007, "无参方法" + serviceName + "." + methodName + "不存在！");
            }
            return (Map<String, Object>) ReflectionUtils.invokeMethod(method, service);
        } else {
            Object service = SpringContextUtil.getBean(serviceName);
            if (service == null) {
                return buildMap(70006, "服务" + serviceName + "不存在！");
            }
            Method method = ReflectionUtils.findMethod(service.getClass(), methodName, Map.class);
            if (method == null) {
                return buildMap(70007, "有参方法" + serviceName + "." + methodName + "不存在！");
            }
            return (Map<String, Object>) ReflectionUtils.invokeMethod(method, service, jsonObject);
        }
    }
}
