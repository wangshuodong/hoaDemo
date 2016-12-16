package com.cmiot.hoa.api.impl.base;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.base.HoaHttpUtil;
import com.cmiot.hoa.api.resource.BaseResource;
import com.cmiot.hoa.facade.base.BaseFacade;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJL on 2016/9/8.
 */
public class BaseFacadeImpl implements BaseFacade {
    public static Logger logger = LoggerFactory.getLogger(BaseFacadeImpl.class);

    @Override
    public Map<String, Object> sendJson(String url, Map<String, Object> parameters) {
        final String gid = BaseResource.newGid();
        Map<String, Object> resultMap = new HashMap<>();
        logger.info("【GID={}】对外服务调用，入参： url={},parameters={}", gid, url, parameters);
        try {
            if (StringUtils.isBlank(url)) {
                return AbBaseMapUtil.initErrorMap(7001, "URL不能为空");
            }
            String txt = null;
            if (parameters == null) {
                txt = JSONObject.toJSONString(parameters);
            }
            String jsonString = HoaHttpUtil.doPostJson(url, txt);
            logger.info("【GID={}】对外服务调用第三方返回，出参：{}", gid, jsonString);
            resultMap = JSONObject.parseObject(jsonString, Map.class);
        } catch (Exception e) {
            logger.info("【GID={}】对外服务调用，异常：{}", gid, e);
            resultMap = AbBaseMapUtil.initExceptionMap(500, "调用远程方法异常!", e);
        }
        logger.info("【GID={}】对外服务调用，出参：{}", gid, resultMap);
        return resultMap;
    }


    @Override
    public Map<String, Object> sendJson(String userName, String password, String url, Map<String, Object> parameters) {
        final String gid = BaseResource.newGid();
        Map<String, Object> resultMap = new HashMap<>();
        logger.info("【GID={}】对外服务调用实现了HttpDigest 认证，入参：userName={},password={}, url={},parameters={}", gid, userName, password, url, parameters);
        try {
            if (StringUtils.isBlank(url)) {
                return AbBaseMapUtil.initErrorMap(7001, "url不能为空");
            }
            if (StringUtils.isBlank(userName)) {
                return AbBaseMapUtil.initErrorMap(7002, "userName不能为空");
            }
            if (StringUtils.isBlank(password)) {
                return AbBaseMapUtil.initErrorMap(7003, "URL不能为空");
            }
            String txt = null;
            if (parameters == null) {
                txt = JSONObject.toJSONString(parameters);
            }
            String jsonString = HoaHttpUtil.doPostJson(userName, password, url, txt);
            logger.info("【GID={}】对外服务调用第三方返回，出参：{}", gid, jsonString);
            resultMap = JSONObject.parseObject(jsonString, Map.class);
        } catch (Exception e) {
            logger.info("【GID={}】对外服务调用实现了HttpDigest 认证，异常：{}", gid, e);
            resultMap = AbBaseMapUtil.initExceptionMap(500, "调用远程方法异常!", e);
        }
        logger.info("【GID={}】对外服务调用实现了HttpDigest 认证，出参：{}", gid, resultMap);
        return resultMap;
    }


}
