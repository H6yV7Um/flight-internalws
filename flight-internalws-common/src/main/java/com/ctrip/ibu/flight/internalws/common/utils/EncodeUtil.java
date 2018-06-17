package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 编码工具类
 * Create by kyxie on 2018/2/1 11:01
 */
public final class EncodeUtil {

    private static final ILog LOGGER = LogManager.getLogger(EncodeUtil.class);

    private static final String DEFAULT_ENCODING_FORMAT = "utf-8";

    public static String encodeUrl(String url){
        try {
            return URLEncoder.encode(url,DEFAULT_ENCODING_FORMAT);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("urlEncode编码异常：编码方式不支持。异常信息：%s",e.getMessage()));
        }
        return null;
    }

}
