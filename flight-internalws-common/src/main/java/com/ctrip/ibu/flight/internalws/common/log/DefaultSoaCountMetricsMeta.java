package com.ctrip.ibu.flight.internalws.common.log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 默认的SOA调用次数监控
 * author : kyxie
 * date : 2018/5/11 15:15
 */
public class DefaultSoaCountMetricsMeta implements MetricsMeta {

    private final static String METRICS_NAME = "soamonitor.count";

    @Override
    public String metricsName() {
        return String.format("%1$s.%2$s", METRICS_NAME_PREFIX,METRICS_NAME);
    }

    @Override
    public Integer interval() {
        return DEFAULT_INTERVAL;
    }

    @Override
    public TimeUnit intervalTimeUnit() {
        return DEFAULT_TIME_UNIT;
    }

    @Override
    public List<String> tags() {
        List<String> tags = new ArrayList<>();
        tags.add("methodName");
        return tags;
    }
}
