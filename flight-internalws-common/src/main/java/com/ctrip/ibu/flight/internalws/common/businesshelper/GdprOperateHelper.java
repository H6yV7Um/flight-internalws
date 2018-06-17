package com.ctrip.ibu.flight.internalws.common.businesshelper;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.ctrip.ibu.flight.internalws.models.util.GDPRType;
import com.ctrip.ibu.platform.gdpr.util.IbuGDPRUtils;

/**
 * General Data Protection Regulation Operate Helper
 * HOME PAGE : https://www.eugdpr.org/
 * Create by kyxie on 2018/4/20 11:47
 */
public final class GdprOperateHelper {

    private final static ILog LOGGER = LogManager.getLogger(GdprOperateHelper.class);

    private final static String GDPR_LOG_PATTERN = "加密用户(UID : %s)的敏感信息 : %s";

    /**
     * Hash化用户信息
     * @param uid 用户UID
     * @param gdprType GDPR类型
     * @param origVal 原始值
     * */
    public static String hashString(String uid, GDPRType gdprType, String origVal){

        LOGGER.info(LogConst.Clog.LogTitle.GDPR_OPERATE,String.format(GDPR_LOG_PATTERN,uid,String.valueOf(gdprType)));

        return IbuGDPRUtils.hashString(uid,convertGdprType(gdprType),origVal);
    }

    /**
     * 转换GDPR类型枚举
     * @param gdprType GDPR类型
     * */
    private static com.ctrip.ibu.platform.gdpr.bean.GDPRType convertGdprType(GDPRType gdprType){
        if (gdprType == null){
            return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_ext;
        }

        switch (gdprType){
            case GDPR_USER_IP:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_ip;
            case GDPR_USER_TEL:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_tel;
            case GDPR_USER_IDFA:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_idfa;
            case GDPR_USER_IMEI:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_imei;
            case GDPR_USER_NAME:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_name;
            case GDPR_USER_EMAIL:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_email;
            case GDPR_USER_CARDNO:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_cardno;
            case GDPR_CONTACT_NAME:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_contact_name;
            case GDPR_USER_ADDRESS:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_address;
            case GDPR_CONTACT_EMAIL:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_contact_email;
            case GDPR_USER_DEVICEID:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_deviceid;
            case GDPR_USER_IDCARDNO:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_idcardno;
            case GDPR_USER_LATITUDE:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_latitude;
            case GDPR_USER_LONGITUDE:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_longitude;
            case GDPR_USER_CLIENTCODE:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_clientcode;
            case GDPR_USER_DEVICETOKEN:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_devicetoken;
            case GDPR_USER_MOBILEPHONE:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_user_mobilephone;
            case GDPR_TRAVELER_IDCARDNO:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_traveler_idcardno;
            case GDPR_TRAVELER_LASTNAME:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_traveler_lastname;
            case GDPR_TRAVELER_FIRSTNAME:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_traveler_firstname;
            case GDPR_CONTACT_MOBILEPHONE:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_contact_mobilephone;
            case GDPR_TRAVELER_MIDDLENAME:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_traveler_middlename;
            case GDPR_USER_FOREIGNMOBILEPHONE:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_contact_foreignmobilephone;
            case GDPR_CONTACT_FOREIGN_MOBILEPHONE:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_contact_mobilephone;
            default:
                return com.ctrip.ibu.platform.gdpr.bean.GDPRType.gdpr_ext;
        }
    }
}
