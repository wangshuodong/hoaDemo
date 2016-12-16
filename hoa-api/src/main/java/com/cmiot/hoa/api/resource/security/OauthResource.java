package com.cmiot.hoa.api.resource.security;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.resource.BaseResource;
import com.cmiot.hoa.api.resource.order.MethodExecutionOrder;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
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
@Path("oauth")
public class OauthResource extends BaseResource {
    public static Logger logger = LoggerFactory.getLogger(OauthResource.class);

    @POST
    @Path("2.0/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(@Context ChannelHandlerContext ctx, String jsonText) {
        final String gid = newGid();
        logger.info("【GID={}】获取OAuth2.0Token，原始请求入参:{}", gid, jsonText);

        JSONObject jsonObject = null;
        if (StringUtils.isBlank(jsonText)) {
            return Response.ok(buildMap(70010, "参数不能为空")).build();
        }
        try {
            jsonObject = JSONObject.parseObject(jsonText);
        } catch (Exception e) {
            return Response.ok(buildMap(70011, "参数格式非法")).build();
        }

        String serviceName = "digestUserService"; //鉴权服务名
        String methodName = "token";//鉴权方法名

        Map<String, Object> resultObjectMap = methodExecution(new MethodExecutionOrder(gid, ctx, serviceName, methodName, jsonObject));
        return Response.ok(resultObjectMap).build();
    }

}
