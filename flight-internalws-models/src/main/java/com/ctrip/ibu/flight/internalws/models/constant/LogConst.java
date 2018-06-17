package com.ctrip.ibu.flight.internalws.models.constant;

/**
 * 日志相关常量
 * Created by kyxie on 2017/8/25.
 */
public final class LogConst {

    //不允许实例化
    private LogConst() throws IllegalAccessException{
        throw new IllegalAccessException("Can not be instantiated");
    }

    /**
     * Clog常量
     * */
    public final static class Clog {

        /**
         * LogTitle
         * */
        public final static class LogTitle {

            public static final String SYSTEM_MONITOR = "SystemMonitor";

            public static final String APP_LOG = "AppLog";

            public static final String WS_LOG = "WSLog";

            public static final String GDPR_OPERATE = "GDPRLog";
        }
    }

    /**
     * Elk常量
     * */
    public final static class Elk {

        public final static String SCENRIO = "ibu-flight-internalws-java";

    }

    //SOA系统监控Title
    public static final String CLOGTITLE_SYSTEMMONITOR = "SystemMonitor";

    //ClassName Log Tag
    public static final String LOGTAG_CLASSNAME = "ClassName";

    //ClassName Method Tag
    public static final String LOGTAG_METHODNAME = "MethodName";

    //region --日志Note--
    public static final String LOGNOTE_ORDERDETAILFAIL = "获取订单详情失败";
    //endregion
}
