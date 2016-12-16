package com.cmiot.hoa.api.base;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.resource.BaseResource;
import org.apache.commons.lang.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HoaHttpUtil {

    public static String doPostJson(String url, String json) throws Exception {
        String resultString = null;
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            if (StringUtils.isNotBlank(json)) {
                httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } finally {
            // 关闭到客户端的连接
            if (response != null) response.close();
            // 关闭http请求
            if (httpClient != null) httpClient.close();
        }
        return resultString;
    }

    public static String doPostJson(String userName, String password, String url, String json) throws Exception {
        String resultString = null;
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            //创建Digest 认证上下文
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setCredentialsProvider(credentialsProvider);
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            if (StringUtils.isNotBlank(json)) {
                httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
            }
            // 执行http请求
            response = httpClient.execute(httpPost, localContext);
            if (response.getStatusLine().getStatusCode() == 401) {
                resultString = JSONObject.toJSONString(BaseResource.errorMap(-1, "远程认证失败401"));
            } else {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } finally {
            // 关闭到客户端的连接
            if (response != null) response.close();
            // 关闭http请求
            if (httpClient != null) httpClient.close();
        }
        return resultString;
    }

}
