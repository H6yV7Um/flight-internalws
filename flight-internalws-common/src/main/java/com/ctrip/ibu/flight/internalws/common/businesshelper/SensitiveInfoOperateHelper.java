package com.ctrip.ibu.flight.internalws.common.businesshelper;

import com.ctrip.arch.coreinfo.CoreInfoClient;
import com.ctrip.arch.coreinfo.entity.InfoData;
import com.ctrip.arch.coreinfo.entity.InfoKey;
import com.ctrip.arch.coreinfo.enums.KeyType;
import com.ctrip.arch.coreinfo.enums.Status;
import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.models.util.SensitiveInfoType;

import java.util.HashMap;
import java.util.Map;

/**
 * 敏感信息操作帮助类
 * Create by kyxie on 2018/3/13 16:50
 */
public final class SensitiveInfoOperateHelper {

    private final static ILog LOGGER = LogManager.getLogger(SensitiveInfoOperateHelper.class);

    private final static CoreInfoClient CORE_INFO_CLIENT = CoreInfoClient.getInstance();

    /**
     * 加密
     * @param sensitiveInfoType 敏感信息类型
     * @param originalVal 原始数据
     * */
    public static String encrypt(SensitiveInfoType sensitiveInfoType,String originalVal){

        //暂不支持的加密类型，返回原始值
        if (sensitiveInfoType == null || sensitiveInfoType == SensitiveInfoType.Generic){
            return originalVal;
        }

        String encryptResultStr = originalVal;

        Map<String,String> logTags = new HashMap<>();
        logTags.put(LogConst.LOGTAG_CLASSNAME,"SensitiveInfoOperateHelper");
        logTags.put(LogConst.LOGTAG_METHODNAME,"encrypt");

        try {
            InfoKey encryptInfo = new InfoKey(convertSensitiveInfoType(sensitiveInfoType),originalVal);

            InfoData encryptResult = CORE_INFO_CLIENT.encrypt(encryptInfo);

            if (encryptResult != null){
                if (encryptResult.getStatus() == Status.SUCCESS){
                    encryptResultStr = encryptResult.getResult();
                }
            }
        } catch (Exception e){
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e,logTags);
        }

        return encryptResultStr;
    }

    /**
     * 解密
     * @param sensitiveInfoType 解密类型
     * @param encryptedVal 需要解密的值
     * */
    public static String decrypt(SensitiveInfoType sensitiveInfoType,String encryptedVal){

        //暂不支持的加密类型，返回原始值
        if (sensitiveInfoType == null || sensitiveInfoType == SensitiveInfoType.Generic){
            return encryptedVal;
        }

        String decryptResultStr = encryptedVal;

        Map<String,String> logTags = new HashMap<>();
        logTags.put(LogConst.LOGTAG_CLASSNAME,"SensitiveInfoOperateHelper");
        logTags.put(LogConst.LOGTAG_METHODNAME,"decrypt");
        StringBuilder logDesc = new StringBuilder();

        try {
            InfoKey encryptedInfo = new InfoKey(convertSensitiveInfoType(sensitiveInfoType),encryptedVal);

            InfoData decryptResult = CORE_INFO_CLIENT.decrypt(encryptedInfo);

            if (decryptResult != null){
                if (decryptResult.getStatus() == Status.SUCCESS){
                    decryptResultStr = decryptResult.getResult();
                    logDesc.append(String.format("原始字符串：%s\n加密结果：%s",encryptedVal,decryptResultStr));
                } else {
                    logDesc.append(String.format("解密返回Status=%s为不成功,此时的返回解密结果:%s(返回原始值%s,不使用该结果)",String.valueOf(decryptResult.getStatus()),decryptResult.getResult(),encryptedVal));
                }
            } else {
                logDesc.append("CoreInfoClient.decrypt(encryptedInfo)方法返回为NULL");
            }
            LOGGER.info(LogConst.CLOGTITLE_SYSTEMMONITOR,logDesc.toString(),logTags);
        } catch (Exception e){
            LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e,logTags);
        }
        return decryptResultStr;
    }

    /**
     * 转换敏感信息枚举类型到CoreInfoClient的枚举类型
     * @param sensitiveInfoType 敏感信息类型
     * */
    private static KeyType convertSensitiveInfoType(SensitiveInfoType sensitiveInfoType){
        if (sensitiveInfoType == null || sensitiveInfoType == SensitiveInfoType.Generic){
            return KeyType.OtherDocument;
        }

        switch (sensitiveInfoType){
            case MTP:
                return KeyType.MTP;
            case Mail:
                return KeyType.Mail;
            case Phone:
                return KeyType.Phone;
            case Address:
                return KeyType.Address;
            case Passport:
                return KeyType.Passport;
            case HKMacPass:
                return KeyType.HKMacPass;
            case HomePermit:
                return KeyType.HomePermit;
            case TaiwanPass:
                return KeyType.TaiwanPass;
            case MilitaryCard:
                return KeyType.MilitaryCard;
            case Soldier_Card:
                return KeyType.Soldier_Card;
            case Identity_Card:
                return KeyType.Identity_Card;
            case OtherDocument:
                return KeyType.OtherDocument;
            case Driver_License:
                return KeyType.Driver_License;
            case Student_Id_Card:
                return KeyType.Student_Id_Card;
            case Travel_Document:
                return KeyType.Travel_Document;
            case Birth_Certificate:
                return KeyType.Birth_Certificate;
            case Seafarer_Passport:
                return KeyType.Seafarer_Passport;
            case Household_Register:
                return KeyType.Household_Register;
            case Police_Certificate:
                return KeyType.Police_Certificate;
            case Interim_Identity_Card:
                return KeyType.Interim_Identity_Card;
            case Foreigner_Permanent_Residence_Card:
                return KeyType.Foreigner_Permanent_Residence_Card;
            case Foreigner_Permanent_Resident_ID_Card:
                return KeyType.Foreigner_Permanent_Resident_ID_Card;
            default:
                return KeyType.OtherDocument;
        }
    }
}
