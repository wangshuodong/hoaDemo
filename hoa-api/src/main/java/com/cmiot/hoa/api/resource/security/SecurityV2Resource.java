package com.cmiot.hoa.api.resource.security;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.resource.BaseV2Resource;
import com.cmiot.hoa.api.resource.order.MethodExecutionOrder;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.spi.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/7.
 */
@Path("/")
public class SecurityV2Resource extends BaseV2Resource {
    private static Logger logger = LoggerFactory.getLogger(SecurityV2Resource.class);

    /**
     * 该方法走IP白名单认证
     *
     * @param ctx
     * @param jsonText
     * @return
     */
    @POST
    @Path("private/{serviceName}/{methodName}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response privateJson(@Context ChannelHandlerContext ctx,
                                       @Context HttpRequest request,
                                       @PathParam("serviceName") String serviceName,
                                       @PathParam("methodName") String methodName, String jsonText) {

        final String gid = newGid();
        logger.info("【GID={}】原始BODY报文:{}", gid, jsonText);
        String ip = getConsumerIp(ctx);

        String uid = request.getHttpHeaders().getHeaderString("UID");
        if (StringUtils.isBlank(uid)) {
            return Response.ok(buildMap(70010, "Header UID 不能为空")).build();
        }

        JSONObject parameters = null;
        if (StringUtils.isNotBlank(jsonText)) {
            try {
                parameters = JSONObject.parseObject(jsonText);
            } catch (Exception e) {
                return Response.ok(buildMap(70001, "BODY非法")).build();
            }
        }

        String autServiceName = "authService"; //鉴权服务名
        String autMethodName = "authorization";//鉴权方法名
        JSONObject autParameters = new JSONObject();
        autParameters.put("ip", ip);
        autParameters.put("uid", uid);
        autParameters.put("utype", "1");
        autParameters.put("serviceName", serviceName);
        autParameters.put("methodName", methodName);

        //4:)调用鉴权
        Map<String, Object> checkPermissionResultObjectMap = methodExecution(new MethodExecutionOrder(gid, ip, autServiceName, autMethodName, autParameters));
        if (Integer.parseInt(String.valueOf(checkPermissionResultObjectMap.get("resultCode"))) != 0) {
            return Response.ok(checkPermissionResultObjectMap).build();
        }
        //5:)调用业务
        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ip, serviceName, methodName, parameters));
        return Response.ok(resultObjectMap).build();

    }


}
