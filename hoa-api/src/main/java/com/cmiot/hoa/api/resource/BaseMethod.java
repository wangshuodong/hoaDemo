package com.cmiot.hoa.api.resource;

import com.cmiot.hoa.api.base.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/7.
 */
public class BaseMethod extends BaseResource {

    /**
     * 验证权限
     *
     * @param serviceName
     * @param methodName
     * @param parameters
     * @return
     * @throws Exception
     */
    public static Map<String, Object> checkPermission(String serviceName, String methodName, Map<String, Object> parameters) throws Exception {
        return InvokeMethod(serviceName, methodName, parameters);
    }


    public static Map<String, Object> InvokeBusiness(String serviceName, String methodName, Map<String, Object> parameters) throws Exception {
        return InvokeMethod(serviceName, methodName, parameters);
    }

    /**
     * 调用服务方法
     *
     * @param serviceName
     * @param methodName
     * @param parameters
     * @return
     * @throws Exception
     */
    private static Map<String, Object> InvokeMethod(String serviceName, String methodName, Map<String, Object> parameters) throws Exception {
        if (StringUtils.isBlank(serviceName)) return errorMap(-99, "服务名不能为空！");
        if (StringUtils.isBlank(methodName)) return errorMap(-98, "方法名不能为空！");
        if (parameters == null) {
            Object service = SpringContextUtil.getBean(serviceName);
            Method method = ReflectionUtils.findMethod(service.getClass(), methodName);
            return (Map<String, Object>) ReflectionUtils.invokeMethod(method, service);
        } else {
            Object service = SpringContextUtil.getBean(serviceName);
            Method method = ReflectionUtils.findMethod(service.getClass(), methodName, Map.class);
            return (Map<String, Object>) ReflectionUtils.invokeMethod(method, service, parameters);
        }
    }


}
