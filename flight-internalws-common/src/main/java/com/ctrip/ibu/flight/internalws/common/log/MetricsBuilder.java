package com.ctrip.ibu.flight.internalws.common.log;

import com.ctrip.framework.clogging.agent.metrics.aggregator.MetricsAggregator;
import com.ctrip.framework.clogging.agent.metrics.aggregator.MetricsAggregatorFactory;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Metrics监控类Builder
 * Create by kyxie on 2017/12/23 19:59
 */
public class MetricsBuilder {

    //默认聚合时间（单位：秒）
    private final static Integer DEFAULTAGGINTERVAL = 60;

    private final static String METRICS_PREFIX = "ibu.flight.internalws.soamonitor";

    private String metricsName;

    private List<String> tags = new ArrayList<>();

    private Integer aggInterval;

    private MetricsBuilder() throws IllegalAccessException{
        throw new IllegalAccessException("Can not be Instantited!!");
    }

    private MetricsBuilder(String metricsName,Integer aggInterval){
        this.metricsName = metricsName;
        this.aggInterval = aggInterval;
    }

    /**
     * 创建一个新的MetricsBuilder
     * @param metricsName Metrics名称（不能为空）
     * */
    public static MetricsBuilder newBuilder(@NotNull String metricsName){
        return new MetricsBuilder(String.format("%1$s.%2$s",METRICS_PREFIX,metricsName),DEFAULTAGGINTERVAL);
    }

    /**
     * 设置聚合时间间隔
     * @param aggInterval 聚合时间(单位：秒)
     * */
    public MetricsBuilder aggInterval(Integer aggInterval){
        this.aggInterval = aggInterval;
        return this;
    }

    /**
     * 新增一个Tag
     * @param tag Tag名称
     * */
    public MetricsBuilder addTag(String tag){
        this.tags.add(tag);
        return this;
    }

    public MetricsAggregator build(){
        if (this.aggInterval <= 0){
            this.aggInterval = DEFAULTAGGINTERVAL;
        }

        return MetricsAggregatorFactory.createAggregator(this.metricsName,this.aggInterval, CommonUtil.convertListToArray(this.tags,String.class));
    }
}
