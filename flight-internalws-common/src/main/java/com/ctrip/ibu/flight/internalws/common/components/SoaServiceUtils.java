package com.ctrip.ibu.flight.internalws.common.components;

import com.ctrip.ibu.flight.internalws.models.annotation.SoaServiceProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SOA服务工具类
 * Create by kyxie on 2018/4/11 13:52
 */
public final class SoaServiceUtils {

    private static final Map<Class<?>,SoaServiceMetaInfo> SERVICE_PROCESSOR_DESC_MAP = new ConcurrentHashMap<>();

    /**
     * 获取服务信息
     * @param clazz 服务实现类
     * */
    public static <T> SoaServiceMetaInfo getSoaServiceInfo(Class<T> clazz){

        if (clazz == null){
            return null;
        }

        if (SERVICE_PROCESSOR_DESC_MAP.containsKey(clazz)){
            return SERVICE_PROCESSOR_DESC_MAP.get(clazz);
        }

        SoaServiceProcessor anno = clazz.getAnnotation(SoaServiceProcessor.class);
        if (anno == null){
            return null;
        }

        SoaServiceMetaInfo soaServiceMetaInfo = SoaServiceMetaInfo.Builder.newInstance()
                .actionId(anno.actionId())
                .actionName(anno.actionName())
                .actionNote(anno.actionNote())
                .enableMetrics(anno.enableMetrics())
                .enableLog(anno.enableLog())
                .build();

        SERVICE_PROCESSOR_DESC_MAP.putIfAbsent(clazz, soaServiceMetaInfo);

        return soaServiceMetaInfo;
    }

}
