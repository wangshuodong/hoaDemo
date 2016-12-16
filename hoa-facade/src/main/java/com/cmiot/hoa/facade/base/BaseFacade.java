package com.cmiot.hoa.facade.base;

import java.util.Map;

/**
 * Created by ZJL on 2016/9/8.
 */
public interface BaseFacade {
    /**
     * 对外服务调用
     *
     * @param url
     * @param parameters
     * @return
     */
    public Map<String, Object> sendJson(String url, Map<String, Object> parameters);


    /**
     * 对外服务调用实现了HttpDigest 认证
     *
     * @param userName
     * @param password
     * @param url
     * @param parameters
     * @return
     */
    public Map<String, Object> sendJson(String userName, String password, String url, Map<String, Object> parameters);
}
