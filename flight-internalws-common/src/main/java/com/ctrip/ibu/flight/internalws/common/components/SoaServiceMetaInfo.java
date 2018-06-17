package com.ctrip.ibu.flight.internalws.common.components;

/**
 * Soa服务信息
 * Create by kyxie on 2018/4/11 14:14
 */

public class SoaServiceMetaInfo {

    private String actionId;

    private String actionName;

    private String actionNote;

    private boolean enableLog;

    private boolean enableMetrics;

    private SoaServiceMetaInfo(Builder builder){
        this.actionId = builder.actionId;
        this.actionName = builder.actionName;
        this.actionNote = builder.actionNote;
        this.enableLog = builder.enableLog;
        this.enableMetrics = builder.enableMetrics;
    }

    public String getActionId() {
        return actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public String getActionNote() {
        return actionNote;
    }

    public boolean isEnableMetrics() {
        return enableMetrics;
    }

    public boolean isEnableLog() {
        return enableLog;
    }

    static class Builder{

        private String actionId;

        private String actionName;

        private String actionNote;

        private boolean enableLog;

        private boolean enableMetrics;

        private Builder(){

        }

        public static Builder newInstance(){
            return new Builder();
        }

        public Builder actionId(String actionId){
            this.actionId = actionId;
            return this;
        }

        public Builder actionName(String actionName){
            this.actionName = actionName;
            return this;
        }

        public Builder actionNote(String actionNote){
            this.actionNote = actionNote;
            return this;
        }

        public Builder enableLog(Boolean enableLog){
            this.enableLog = enableLog;
            return this;
        }

        public Builder enableMetrics(Boolean enableMetrics){
            this.enableMetrics = enableMetrics;
            return this;
        }

        public SoaServiceMetaInfo build(){
            return new SoaServiceMetaInfo(this);
        }
    }
}
