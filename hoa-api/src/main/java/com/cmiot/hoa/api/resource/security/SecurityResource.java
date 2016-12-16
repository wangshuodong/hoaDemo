package com.cmiot.hoa.api.resource.security;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.resource.BaseResource;
import com.cmiot.hoa.api.resource.order.MethodExecutionOrder;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
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
public class SecurityResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(SecurityResource.class);

    /**
     * 该方法走IP白名单认证
     *
     * @param ctx
     * @param jsonText
     * @return
     */
    @POST
    @Path("private")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response privateJson(@Context ChannelHandlerContext ctx, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】原始请求入参:{}", gid, jsonText);
        //1:)校验body必须非空且必须为json格式
        JSONObject jsonObject = null;
        if (StringUtils.isNotBlank(jsonText)) {
            try {
                jsonObject = JSONObject.parseObject(jsonText);
            } catch (Exception e) {
                return Response.ok(buildMap(70001, "BODY非法")).build();
            }
        }

        //2:)获取必要参数
        String ip = getConsumerIp(ctx);
        String autServiceName = "authService"; //鉴权服务名
        String autMethodName = "authorization";//鉴权方法名
        String uid = jsonObject.getString("uid"); //uid
        String utype = jsonObject.getString("utype");//utype
        String serviceName = getServiceName(jsonObject); //获取业务服务名
        String methodName = getMethodName(jsonObject);//获取业务方法名
        //3:)构建验证ip白名单参数
        JSONObject autParameters = new JSONObject();
        autParameters.put("ip", ip);
        autParameters.put("uid", uid);
        autParameters.put("utype", utype);
        autParameters.put("serviceName", serviceName);
        autParameters.put("methodName", methodName);
        //4:)调用鉴权
        Map<String, Object> checkPermissionResultObjectMap = methodExecution(new MethodExecutionOrder(gid, ip, autServiceName, autMethodName, autParameters));
        if (Integer.parseInt(String.valueOf(checkPermissionResultObjectMap.get("resultCode"))) != 0) {
            return Response.ok(checkPermissionResultObjectMap).build();
        }

        //5:)调用业务
        JSONObject parameters = assemblyParameters(checkPermissionResultObjectMap, getParameters(jsonObject), utype);
        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ip, serviceName, methodName, parameters));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * 该方法走Oauth2.0单认证
     *
     * @param request
     * @param jsonText
     * @return
     */
    @POST
    @Path("protect")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response protectJson(@Context ChannelHandlerContext ctx, @Context HttpRequest request, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】原始请求入参:{}", gid, jsonText);
        String accessToken = request.getHttpHeaders().getHeaderString("Access-Token");
        if (StringUtils.isBlank(accessToken)) {
            return Response.ok(BaseResource.newErrorMap(502, "Access Token不能为空")).build();
        }
        //1:)校验body必须非空且必须为json格式
        JSONObject jsonObject = null;
        if (StringUtils.isNotBlank(jsonText)) {
            try {
                jsonObject = JSONObject.parseObject(jsonText);
            } catch (Exception e) {
                return Response.ok(buildMap(70001, "BODY非法")).build();
            }
        }
        //2:)获取必要参数
        String ip = getConsumerIp(ctx);
        String autServiceName = "authService"; //鉴权服务名
        String autMethodName = "authorization";//鉴权方法名
        String utype = jsonObject.getString("utype");//utype
        String serviceName = getServiceName(jsonObject); //获取业务服务名
        String methodName = getMethodName(jsonObject);//获取业务方法名
        //3:)构建验证ip白名单参数
        JSONObject autParameters = new JSONObject();
        autParameters.put("uid", accessToken);
        autParameters.put("utype", utype);
        autParameters.put("serviceName", serviceName);
        autParameters.put("methodName", methodName);
        //4:)调用鉴权
        Map<String, Object> checkPermissionResultObjectMap = methodExecution(new MethodExecutionOrder(gid, ip, autServiceName, autMethodName, autParameters));
        if (Integer.parseInt(String.valueOf(checkPermissionResultObjectMap.get("resultCode"))) != 0) {
            return Response.ok(checkPermissionResultObjectMap).build();
        }
        //5:)调用业务
        JSONObject parameters = assemblyParameters(checkPermissionResultObjectMap, getParameters(jsonObject), utype);
        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ip, serviceName, methodName, parameters));
        return Response.ok(resultObjectMap).build();
    }


    /**
     * 根据验证权限结果组装业务参数
     *
     * @param checkPermissionResultObjectMap
     * @param parameters
     * @param utype
     * @return
     */
    private static JSONObject assemblyParameters(Map<String, Object> checkPermissionResultObjectMap, JSONObject parameters, String utype) {
        if (parameters != null && StringUtils.isNotBlank(utype) && "1".equals(utype)) { //iot平台
            String mUid = (String) checkPermissionResultObjectMap.get("uid");
            String mUserName = (String) checkPermissionResultObjectMap.get("userName");
            parameters.put("uid", mUid);
            parameters.put("OpenId", mUid);
            parameters.put("userName", mUserName);
        }
        return parameters;
    }

}
