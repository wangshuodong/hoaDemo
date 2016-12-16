package com.cmiot.hoa.api.base;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP_DIGEST工具类
 * Created by ZJL on 2016/3/8.
 */
public class DigestUtil {
    private static Logger logger = LoggerFactory.getLogger(DigestUtil.class);

    private static final String BLANK = " ";//空格符
    private static final String SEPARATOR = ":";//分隔符
    private static final String realm = "IOT-HOA";

    /**
     * 组装 WWW-Authenticate
     *
     * @return
     */
    public static final String newWwwAuthenticate(String nonce) {
        StringBuilder autBuilder = new StringBuilder("Digest")
                .append(BLANK).append("realm=").append("\"").append(realm).append("\",")
                .append(BLANK).append("nonce=").append("\"").append(nonce).append("\",")
                .append(BLANK).append("qop=").append("\"").append("auth").append("\"");
        return autBuilder.toString();
    }

    /**
     * DIGEST AUTHENTICATION 认证
     *
     * @param method
     * @param authorization
     * @param password
     * @return
     */
    public static boolean digestAuthentication(String method, String authorization, String password) {
        Map<String, String> authorizationMap = authorizationToMap(authorization);
        String nc = authorizationMap.get("nc"); //请求计数
        String uri = authorizationMap.get("uri");//请求地址
        String qop = authorizationMap.get("qop");  //保护质量
        String realm = authorizationMap.get("realm");//认证域
        String nonce = authorizationMap.get("nonce");//服务器密码随机数
        String cnonce = authorizationMap.get("cnonce"); //客户端密码随机数
        String response = authorizationMap.get("response");//加密后的MD5
        String username = authorizationMap.get("username");//用户名
        String ha1 = DigestUtils.md5Hex(username + SEPARATOR + realm + SEPARATOR + password);
        String ha2 = DigestUtils.md5Hex(method + SEPARATOR + uri);
        String hResponse = null;
        if ("auth".equals(qop) || "auth-int".equals(qop)) {
            hResponse = ha1 + SEPARATOR + nonce + SEPARATOR + nc + SEPARATOR + cnonce + SEPARATOR + qop + SEPARATOR + ha2;
        } else {
            hResponse = ha1 + SEPARATOR + nonce + SEPARATOR + ha2;
        }
        String md5vResponse = DigestUtils.md5Hex(hResponse);
        return response.equals(md5vResponse);
    }

    /**
     * 将AUTHORIZATION转换为MAP方便取值
     *
     * @param authorization
     * @return
     */
    public static Map<String, String> authorizationToMap(String authorization) {
        Map<String, String> authorizationMap = new HashMap<>();
        authorization = authorization.replaceAll("\"", "");
        authorization = authorization.replaceAll("Digest", "");
        String[] authorizationStrings = authorization.split(",");
        for (String s : authorizationStrings) {
            int i = s.indexOf("=");
            String key = s.substring(0, i).trim();
            String value = s.substring(i + 1, s.length()).trim();
            authorizationMap.put(key, value);
        }
        return authorizationMap;
    }

}
