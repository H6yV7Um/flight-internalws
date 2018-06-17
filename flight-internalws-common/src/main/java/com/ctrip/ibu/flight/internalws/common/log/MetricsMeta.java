package com.ctrip.ibu.flight.internalws.common.log;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Metrics元信息
 * author : kyxie
 * date : 2018/5/11 15:13
 */
public interface MetricsMeta {

    String METRICS_NAME_PREFIX = "ibu.flight.internalws.";

    Integer DEFAULT_INTERVAL = 60;

    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    String metricsName();

    Integer interval();

    TimeUnit intervalTimeUnit();

    List<String> tags();
}
