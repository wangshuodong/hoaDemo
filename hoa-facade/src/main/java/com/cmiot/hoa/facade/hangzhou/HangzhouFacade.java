package com.cmiot.hoa.facade.hangzhou;

import java.util.Map;

/**
 * Created by ZJL on 2016/8/8.
 */
public interface HangzhouFacade {

    public Map<String, Object> sendJson(String url, Map<String, Object> objectMap);


}
