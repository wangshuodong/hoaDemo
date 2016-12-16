package com.cmiot.hoa.api.resource;

import com.cmiot.hoa.api.resource.security.SecurityResource;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ZJL on 2016/9/7.
 */
@Path("/")
public class IndexResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(IndexResource.class);


    /**
     * 该方法走IP白名单认证
     *
     * @param ctx
     * @param jsonText
     * @return
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response indexJson(@Context ChannelHandlerContext ctx, String jsonText) {
        return  SecurityResource.privateJson(ctx, jsonText);
    }

}
