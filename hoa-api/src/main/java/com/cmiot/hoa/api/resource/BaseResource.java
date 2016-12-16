package com.cmiot.hoa.api.resource;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.base.PropertiesUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/7.
 */
public class BaseResource extends BaseV2Resource {

    public static Map<String, Object> newErrorMap(int errorCode, String errorMsg) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error_code", errorCode);
        errorMap.put("error_msg", errorMsg);
        errorMap.put("resultCode", errorCode);
        errorMap.put("resultMsg", errorMsg);
        return errorMap;
    }


    public static Map<String, Object> errorMap(int resultCode, String resultMsg) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("resultCode", resultCode);
        errorMap.put("resultMsg", resultMsg);
        return errorMap;
    }


    /**
     * 获取服务名
     *
     * @param jsonObject
     * @return
     */
    public static final String getServiceName(JSONObject jsonObject) {
        String serviceName = jsonObject.getString("serviceName");
        if (StringUtils.isBlank(serviceName)) {
            if (jsonObject.containsKey("CmdType")) {
                String cmdType = PropertiesUtil.getPropertiesValueStr(jsonObject.getString("CmdType"));
                if (StringUtils.isBlank(cmdType)) return null;
                serviceName = cmdType.split(":")[0].trim();
            }
        }
        return serviceName.trim();
    }

    /**
     * 获取方法名
     *
     * @param jsonObject
     * @return
     */
    public static final String getMethodName(JSONObject jsonObject) {
        String methodName = jsonObject.getString("methodName");
        if (StringUtils.isBlank(methodName)) {
            if (jsonObject.containsKey("CmdType")) {
                String cmdType = PropertiesUtil.getPropertiesValueStr(jsonObject.getString("CmdType"));
                if (StringUtils.isBlank(cmdType)) return null;
                methodName = cmdType.split(":")[1].trim();
            }
        }
        return methodName.trim();
    }


    /**
     * 获取接口参数
     *
     * @param jsonObject
     * @return
     */
    public static final JSONObject getParameters(JSONObject jsonObject) {
        if (jsonObject.containsKey("parameters")) {
            return jsonObject.getJSONObject("parameters");
        } else if (jsonObject.containsKey("CmdType")) {
            return jsonObject;
        } else {
            return null;
        }
    }


}
