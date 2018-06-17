package com.ctrip.ibu.flight.internalws.common.businesshelper;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.GaPassengerInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.orderdetailsearch.v1.ContactInfo;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.models.annotation.GdprInfo;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking.RebookingContactInfo;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * GDPR敏感信息序列化不输出策略
 * Create by kyxie on 2018/4/20 17:22
 */
public class GdprExclusionStrategy implements ExclusionStrategy {

    private static final ILog CLOG = LogManager.getLogger(GdprExclusionStrategy.class);

    private static final String LOG_PATTERN = "类%s中，Gson序列化%s类的%s字段为GDPR敏感字段(%s)，序列化时忽略！！";

    private final Class<?> targetClass;

    private Map<String,GDPRType> exclusionFields;

    private GdprExclusionStrategy(Class<?> targetClass,Map<String,GDPRType> exclusionFields){
        super();
        this.targetClass = targetClass;
        this.exclusionFields = exclusionFields;

        initExclusions();
    }

    /**
     * @param f the field object that is under test
     * @return true if the field should be ignored; otherwise false
     */
    @Override
    public boolean shouldSkipField(FieldAttributes f) {

        Map<String,String> logTags = new HashMap<>();
        logTags.put(LogConst.LOGTAG_CLASSNAME,"GdprExclusionStrategy");
        logTags.put(LogConst.LOGTAG_METHODNAME,"shouldSkipField");

        Class<?> fieldHolderClass = f.getDeclaringClass();
        String fieldName = f.getName();

        String fieldFullName = getGdprFieldFullName(fieldHolderClass,fieldName);

        if (this.exclusionFields.containsKey(fieldFullName)){
            GDPRType gdprType = this.exclusionFields.get(fieldFullName);
            LoggerHelper.appendResponseContent(String.format("类%s的%s字段为GDPR敏感字段(%s)，GSON序列化时忽略!!",fieldHolderClass.getName(),fieldName,String.valueOf(gdprType)));
            CLOG.info(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format(LOG_PATTERN,this.targetClass.getName(),fieldHolderClass.getName(),fieldName,this.exclusionFields.get(fieldFullName)),logTags);
            return true;
        }

        GdprInfo gdprInfoAnno = f.getAnnotation(GdprInfo.class);
        if (gdprInfoAnno != null){
            LoggerHelper.appendResponseContent(String.format("类%s的%s字段为GDPR敏感字段(%s)，GSON序列化时忽略!!",fieldHolderClass.getName(),fieldName,String.valueOf(gdprInfoAnno.value())));
            CLOG.info(LogConst.CLOGTITLE_SYSTEMMONITOR,String.format(LOG_PATTERN,this.targetClass.getName(),fieldHolderClass.getName(),fieldName,this.exclusionFields.get(fieldFullName)),logTags);
            return true;
        }

        return false;
    }

    /**
     * @param clazz the class object that is under test
     * @return true if the class should be ignored; otherwise false
     */
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    private static String getGdprFieldFullName(@NotNull Class<?> target,@NotNull String field){
        return String.format("%s|%s",target.getName(),field).toLowerCase();
    }

    /**
     * 新增一条Exclusion
     * */
    private void addExclusion(@NotNull Class<?> clazz, @NotNull String fieldName, GDPRType gdprType){
        String fieldFullName = getGdprFieldFullName(clazz,fieldName);
        if (exclusionFields != null && !exclusionFields.containsKey(fieldFullName)){
            exclusionFields.put(fieldFullName,gdprType);
        }
    }

    private void initExclusions(){
        //请求中联系人邮箱
        addExclusion(SendEmailRequestType.class,"recipientEmailList",GDPRType.GDPR_USER_EMAIL);

        //API乘机人
        addExclusion(GaPassengerInfo.class,"name",GDPRType.GDPR_USER_NAME);
        addExclusion(GaPassengerInfo.class,"nameCN",GDPRType.GDPR_USER_NAME);
        addExclusion(GaPassengerInfo.class,"firstName",GDPRType.GDPR_TRAVELER_FIRSTNAME);
        addExclusion(GaPassengerInfo.class,"middleName",GDPRType.GDPR_TRAVELER_MIDDLENAME);
        addExclusion(GaPassengerInfo.class,"lastName",GDPRType.GDPR_TRAVELER_LASTNAME);
        addExclusion(GaPassengerInfo.class,"idNo",GDPRType.GDPR_TRAVELER_IDCARDNO);

        //API联系人
        addExclusion(ContactInfo.class,"name",GDPRType.GDPR_CONTACT_NAME);
        addExclusion(ContactInfo.class,"phone",GDPRType.GDPR_CONTACT_MOBILEPHONE);
        addExclusion(ContactInfo.class,"email",GDPRType.GDPR_CONTACT_EMAIL);

        //API改签相关
        addExclusion(RebookingContactInfo.class,"contactName",GDPRType.GDPR_USER_NAME);
        addExclusion(RebookingContactInfo.class,"mobilePhone",GDPRType.GDPR_USER_MOBILEPHONE);
        addExclusion(RebookingContactInfo.class,"emailAddress",GDPRType.GDPR_USER_EMAIL);
    }

    public static class Builder {

        private final Class<?> targetClass;

        private Map<String,GDPRType> exclusionFields = new HashMap<>();

        private Builder(Class<?> targetClass){
            this.targetClass = targetClass;
        }

        public static Builder newInstance(Class<?> targetClass) {
            return new Builder(targetClass);
        }

        public Builder registerExclusion(Class<?> target, String field, GDPRType gdprType){
            String gdprFieldFullName = getGdprFieldFullName(target,field);

            if (!exclusionFields.containsKey(gdprFieldFullName)){
                exclusionFields.put(gdprFieldFullName,gdprType);
            }
            return this;
        }

        public GdprExclusionStrategy build(){
            return new GdprExclusionStrategy(this.targetClass,this.exclusionFields);
        }
    }

}
