package com.cmiot.hoa.facade.sichuan;

import java.util.Map;

/**
 * Created by ZJL on 2016/8/12.
 */
public interface SichuanFacade {
    /**
     * @param url       调用地址
     * @param objectMap 调用参数
     * @return
     */
    public Map<String, Object> sendJson(String url, Map<String, Object> objectMap);
}
