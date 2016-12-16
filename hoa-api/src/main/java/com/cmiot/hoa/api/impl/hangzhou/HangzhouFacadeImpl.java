package com.cmiot.hoa.api.impl.hangzhou;

import com.alibaba.fastjson.JSONObject;
import com.cmiot.hoa.api.base.HoaHttpUtil;
import com.cmiot.hoa.api.impl.base.AbBaseMapUtil;
import com.cmiot.hoa.facade.hangzhou.HangzhouFacade;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJL on 2016/8/9.
 */
public class HangzhouFacadeImpl implements HangzhouFacade {

    public static Logger logger = LoggerFactory.getLogger(HangzhouFacadeImpl.class);

    @Override
    public Map<String, Object> sendJson(String url, Map<String, Object> objectMap) {
        logger.info("调用杭研平台，入参：{}", objectMap);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String txt = JSONObject.toJSONString(objectMap);
            String jsonString = HoaHttpUtil.doPostJson(url, txt);
            if (StringUtils.isBlank(jsonString)) {
                resultMap = AbBaseMapUtil.initErrorMap(-1, "杭研平台返回body为空！");
            } else {
                resultMap = JSONObject.parseObject(jsonString, Map.class);
            }
        } catch (Exception e) {
            logger.info("调用杭研平台，异常：{}", e);
            resultMap = AbBaseMapUtil.initExceptionMap(500, "调用远程方法异常!", e);
        }
        logger.info("调用杭研平台，出参：{}", resultMap);
        return resultMap;
    }
}
