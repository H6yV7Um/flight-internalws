package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.common.businesshelper.GdprExclusionStrategy;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 序列化工具类
 * Created by kyxie on 2017/8/7.
 */
public final class SerializeUtil {

    private final static ILog CLOG = LogManager.getLogger(SerializeUtil.class);

    private final static Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private final static Gson GDPR_GSON = new GsonBuilder()
            .addSerializationExclusionStrategy(GdprExclusionStrategy.Builder.newInstance(SerializeUtil.class).build())
            .disableHtmlEscaping()
            .create();

    private final static String DEFAULT_ENCODING = "utf-8";

    /**
     * 将对象序列化为二进制
     * @param obj 要序列化的对象
     * */
    public static <T> byte[] toByte(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            CLOG.error(LogConst.CLOGTITLE_SYSTEMMONITOR, String.format("将对象序列化为二进制时异常，异常信息:\n%s", ThrowableUtils.getExceptionDesc(e)));
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                CLOG.error(LogConst.CLOGTITLE_SYSTEMMONITOR, String.format("OutputStream关闭异常，异常信息:\n%s", ThrowableUtils.getExceptionDesc(e)));
            }

        }

        return null;
    }

    /**
     * 转换为Json
     */
    public static <T> String toJson(T obj) {
        return GSON.toJson(obj);
    }

    /**
     * 转换为JSON字符串，GDPR字段忽略
     * */
    public static <T> String toJsonWithGdpr(T obj){
        return GDPR_GSON.toJson(obj);
    }

    /**
     * URL编码
     * */
    public static <T extends String> String encode(T str){
        try {
            return URLEncoder.encode(str,DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            CLOG.error(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format("不支持的编码格式:%s",DEFAULT_ENCODING));
        }

        return null;
    }
}
