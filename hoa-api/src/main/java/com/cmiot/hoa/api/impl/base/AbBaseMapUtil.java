package com.cmiot.hoa.api.impl.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJL on 2016/8/12.
 */
public class AbBaseMapUtil {

    private final static String RESULT_CODE = "resultCode";

    private final static String RESULT_Message = "resultMessage";

    private final static String RESULT_DESCRIPTION = "resultDescription";


    public static Map<String, Object> initErrorMap(Integer resultCode, String resultMessage) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put(RESULT_CODE, resultCode);
        errorMap.put(RESULT_Message, resultMessage);
        return errorMap;
    }

    public static Map<String, Object> initExceptionMap(Integer resultCode, String resultMessage, Exception exception) {
        Map<String, Object> exceptionMap = initErrorMap(resultCode, resultMessage);
        exceptionMap.put(RESULT_DESCRIPTION, exception);
        return exceptionMap;
    }

}
