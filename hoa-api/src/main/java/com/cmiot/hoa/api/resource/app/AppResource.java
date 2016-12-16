package com.cmiot.hoa.api.resource.app;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.resource.BaseResource;
import com.cmiot.hoa.api.resource.order.MethodExecutionOrder;
import org.jboss.resteasy.spi.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/7.
 */
@Path("/")
public class AppResource extends BaseResource {
    public static Logger logger = LoggerFactory.getLogger(AppResource.class);

    @POST
    @Path("app")
    @Produces(MediaType.APPLICATION_JSON)
    public Response app(@Context HttpRequest request, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】原始请求入参:{}", gid, jsonText);
        //1:)校验body必须非空且必须为json格式
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(9001, "BODY必须非空且必须为JSON格式")).build();
        }
        //2:)获取必要参数
        String autServiceName = "authService"; //鉴权服务名
        String autMethodName = "authorization";//鉴权方法名
        String uid = jsonObject.getString("uid"); //uid
        String utype = jsonObject.getString("utype");//utype
        String serviceName = getServiceName(jsonObject); //获取业务服务名
        String methodName = getMethodName(jsonObject);//获取业务方法名

        //3:)构建验证ip白名单参数
        JSONObject autParameters = new JSONObject();
        autParameters.put("uid", uid);
        autParameters.put("utype", utype);
        autParameters.put("serviceName", serviceName);
        autParameters.put("methodName", methodName);
        //4:)调用鉴权
        Map<String, Object> checkPermissionResultObjectMap = methodExecution(new MethodExecutionOrder(gid, "APP", autServiceName, autMethodName, autParameters));
        if (Integer.parseInt(String.valueOf(checkPermissionResultObjectMap.get("resultCode"))) != 0) {
            return Response.ok(checkPermissionResultObjectMap).build();
        }

        //5:)调用业务
        JSONObject parameters = getParameters(jsonObject);
        if (parameters != null) {
            String mUid = (String) checkPermissionResultObjectMap.get("uid");
            String mUserName = (String) checkPermissionResultObjectMap.get("userName");
            parameters.put("uid", mUid);
            parameters.put("OpenId", mUid);
            parameters.put("userName", mUserName);
            if (parameters.containsKey("requestJson")) {
                Map<String, Object> requestJson = (Map<String, Object>) parameters.get("requestJson");
                requestJson.put("OpenId", mUid);
            }
        }
        //6:)调用业务
        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, "APP", serviceName, methodName, parameters));
        return Response.ok(resultObjectMap).build();
    }

}
