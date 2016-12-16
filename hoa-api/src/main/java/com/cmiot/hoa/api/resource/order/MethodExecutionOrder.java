package com.cmiot.hoa.api.resource.order;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.resource.BaseV2Resource;
import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;

/**
 * Created by ZJL on 2016/10/26.
 */
public class MethodExecutionOrder implements Serializable {
    private static final long serialVersionUID = -7405041542676504302L;
    private String gid;
    private String consumerIp;
    private String serviceName;
    private String methodName;
    private JSONObject jsonObject;
    private ChannelHandlerContext context;

    public MethodExecutionOrder() {

    }

    public MethodExecutionOrder(String gid, String consumerIp, String serviceName, String methodName, JSONObject jsonObject) {
        this.gid = gid;
        this.consumerIp = consumerIp;
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.jsonObject = jsonObject;
    }


    public MethodExecutionOrder(String gid, ChannelHandlerContext context, String serviceName, String methodName, JSONObject jsonObject) {
        String ip = BaseV2Resource.getConsumerIp(context);
        this.gid = gid;
        this.consumerIp = ip;
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.jsonObject = jsonObject;
    }


    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getConsumerIp() {
        return consumerIp;
    }

    public void setConsumerIp(String consumerIp) {
        this.consumerIp = consumerIp;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }
}
