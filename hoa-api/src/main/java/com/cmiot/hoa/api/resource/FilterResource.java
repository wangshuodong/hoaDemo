package com.cmiot.hoa.api.resource;

import com.cmiot.hoa.api.base.DigestUtil;
import org.apache.commons.lang.StringUtils;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.resteasy.spi.HttpRequest;

import javax.ws.rs.core.HttpHeaders;
import java.net.InetSocketAddress;

/**
 * Created by ZJL on 2016/9/19.
 */
public class FilterResource {
    public void securityCheck(ChannelHandlerContext ctx, HttpRequest httpRequest) {
        InetSocketAddress address = (InetSocketAddress) ctx.getChannel().getRemoteAddress();
        String ip = address.getAddress().getHostAddress();
        String authorization = httpRequest.getHttpHeaders().getHeaderString(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {//走ip白名单认证
            boolean ipAut = true;
            boolean servicesMethodAut = true;
            if (ipAut && servicesMethodAut) {

            } else {

            }
        } else {//走DIGEST认证
            String password = null;
            boolean servicesMethodAut = true;
            boolean digestAuthentication = DigestUtil.digestAuthentication(httpRequest.getHttpMethod(), authorization, password);
            if (servicesMethodAut && digestAuthentication) {

            } else {

            }
        }
    }
}
