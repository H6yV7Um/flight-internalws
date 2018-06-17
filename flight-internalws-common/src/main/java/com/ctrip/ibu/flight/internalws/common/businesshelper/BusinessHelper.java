package com.ctrip.ibu.flight.internalws.common.businesshelper;

import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.cargo.configuration.service.Cargo;
import com.ctrip.ibu.flight.internalws.common.utils.LanguageUtil;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.models.common.CorpGroup;
import com.ctrip.ibu.flight.internalws.models.common.Market;
import com.ctrip.ibu.flight.internalws.models.common.Site;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.constant.EmailConst;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * 业务相关的帮助类（其他公用的方法请写到相应的类中）
 * Created by kyxie on 2017/7/5.
 */
public final class BusinessHelper {

    //Utility classes should not have public constructors
    //Utility classes, which are collections of static members, are not meant to be instantiated
    private BusinessHelper() throws IllegalAccessException{
        throw new IllegalAccessException("BusinessHelper class should not be init");
    }

    private final static String APP_DOWNLOAD_URL_PATTERN = "https://www.trip.com/m/downapp?utm_medium=wap&utm_source=flight&utm_campaign=iflightmain&utm_content=${lang}_${aid}_${sid}";

    //各个语言使用能正确展示语言的字符集
    private static final String FONTFAMILY_DEFAULT = "Arial";

    private static final String FONTFAMILY_TH_TH = "Angsana New";

    private static final String FONTFAMILY_KO_KR = "Malgun Gothic";

    private static final String FONTFAMILY_ZH_HK = "SimSun";

    private static final Map<String,FlightWay> FLIGHT_WAY_MAP = new HashMap<>();

    //测试账号
    private static List<String> testUidList = new ArrayList<>();

    static {
        testUidList.add(BusinessConst.TESTACCOUNT_6W);

        FLIGHT_WAY_MAP.put("S",FlightWay.OW);
        FLIGHT_WAY_MAP.put("R",FlightWay.RT);
        FLIGHT_WAY_MAP.put("D",FlightWay.RT);
        FLIGHT_WAY_MAP.put("M",FlightWay.MT);
    }

    /**
     * 通过判断ServerFrom判断订单是否来自SC
     * @param serverFrom 订单来源
     * @return 是否来自SC预定
     * */
    public static boolean isOrderFromSC(String serverFrom){
        return serverFrom != null && !serverFrom.isEmpty() && serverFrom.toLowerCase().indexOf("/sc",0) >=1;
    }

    /**
     * 从ServerFrom中判断订单来源
     * */
    public static CorpGroup getCorpGroup(String serverFrom){
        CorpGroup group = CorpGroup.Ctrip;
        if (serverFrom != null && !serverFrom.isEmpty() && serverFrom.toLowerCase().indexOf(BusinessConst.SCSIGN_INSERVERFROM.toLowerCase(), 0) != -1) {
            group = CorpGroup.SC;
        }
        return group;
    }

    /**
     * 从ServerFrom中获取商标
     */
    public static Trademark getTrademark(String serverFrom) {
        Trademark trademark = Trademark.Ctrip;

        if (serverFrom != null && !serverFrom.isEmpty() && serverFrom.toLowerCase().indexOf(BusinessConst.TRIPSIGN_INSERVERFROM.toLowerCase(), 0) == 0) {
            trademark = Trademark.Trip;
        }

        return trademark;
    }

    /**
     * 获取最终的邮件发送语言
     * @param languageType 指定的发送语言
     * @param orderDetail 订单详情
     * @param specifiedUid 指定的UID（后门，部分UID按照传递语言赋值，方便测试）
     * */
    public static LanguageType getEmailSendLanguage(LanguageType languageType, OrderDetailModel orderDetail, String specifiedUid, Boolean isCustomEmail) {

        LanguageType sendEmailLanguage = LanguageType.unspecified;

        if (!isCustomEmail){

            if (orderDetail != null){
                //测试后门
                if (testUidList.contains((specifiedUid))){
                    if (languageType == LanguageType.unspecified){
                        sendEmailLanguage = getLanFromOrder(orderDetail.getTrademark(),orderDetail.getLocaleId(),orderDetail.getServerFrom());
                    } else {
                        sendEmailLanguage = languageType;
                    }
                } else {
                    if (Trademark.Trip.equals(orderDetail.getTrademark()) || isOfflineOrder(orderDetail.getServerFrom())){
                        sendEmailLanguage = getLanFromOrder(orderDetail.getTrademark(),orderDetail.getLocaleId(),orderDetail.getServerFrom());
                    } else {
                        if (languageType == LanguageType.unspecified){
                            sendEmailLanguage = getLanFromOrder(orderDetail.getTrademark(),orderDetail.getLocaleId(),orderDetail.getServerFrom());
                        } else {
                            sendEmailLanguage = languageType;
                        }
                    }
                }
            }
        }

        if (sendEmailLanguage == LanguageType.unspecified) {
            sendEmailLanguage = LanguageType.en_US;
        }

        //非SC订单不支持发送中文
        if (orderDetail!=null && orderDetail.getCorpGroup() != CorpGroup.SC && sendEmailLanguage == LanguageType.zh_CN){
            sendEmailLanguage = LanguageType.zh_HK;
        }

        return sendEmailLanguage ;
    }

    /**
     * 从订单获取语言
     * @param orderTrademark 订单商标
     * @param localeId 订单LocaleID
     * @param serverFrom 订单ServerFrom
     * */
    private static LanguageType getLanFromOrder(Trademark orderTrademark,Integer localeId,String serverFrom){
        if (Trademark.Trip.equals(orderTrademark) || isOfflineOrder(serverFrom)){
            return LanguageUtil.getLanguageTypeByLocaleId(localeId);
        } else {
            return LanguageUtil.getLanguageTypeByServerFrom(serverFrom);
        }
    }

    /**
     * 是否Offline订单
     *
     * @param serverFrom 订单ServerFrom
     */
    private static boolean isOfflineOrder(String serverFrom) {
        return BusinessConst.OFFLINEORDERSERVERFROM1.equalsIgnoreCase(serverFrom) || BusinessConst.OFFLINEORDERSERVERFROM2.equalsIgnoreCase(serverFrom)
                || BusinessConst.OFFLINEORDERSERVERFROM3.equalsIgnoreCase(serverFrom);
    }

    /**
     * 获取域名
     * */
    public static String getDomain(Site site, Trademark trademark) {
        return Cargo.getDomain(String.valueOf(trademark), String.valueOf(site), Foundation.server().getSubEnv());
    }


    /**
     * 获取机票首页地址
     *
     * @param serverFrom ServerFrom
     */
    public static String getFlightFirstPageUrl(String serverFrom) {

        return BusinessConst.BusinessCommonConst.FLIGHT_FIRST_PAGE_URL_PATTERN.replace("${domain}",
                BusinessHelper.getDomain(BusinessHelper.getSiteByServerFrom(serverFrom),BusinessHelper.getTrademark(serverFrom)));

    }

    /**
     * 通过ServerFrom获取Site
     */
    public static Site getSiteByServerFrom(String serverFrom){
        Site site = Site.EN;

        if (serverFrom != null && !serverFrom.isEmpty()){
            serverFrom = serverFrom.toLowerCase();

            if (serverFrom.contains("jp.") || serverFrom.contains("/jp"))
                site = Site.JP; //"jp";

            if (serverFrom.contains("co.kr") || serverFrom.contains("/kr"))
                site = Site.KR; //"kr";

            if (serverFrom.contains("de.") || serverFrom.contains("/de"))
                site = Site.DE; //"de";

            if (serverFrom.contains("es.") || serverFrom.contains("/es"))
                site = Site.ES; //"es";

            if (serverFrom.contains("fr.") || serverFrom.contains("/fr"))
                site = Site.FR; //"fr";

            if (serverFrom.contains("ru.") || serverFrom.contains("/ru"))
                site = Site.RU; //"ru";

            if (serverFrom.contains("vn.") || serverFrom.contains("/vn"))
                site = Site.VN; //"vn";

            if (serverFrom.contains(".sg") || serverFrom.contains("/sg"))
                site = Site.SG; //"sg";

            if (serverFrom.contains(".my") || serverFrom.contains("/my"))
                site = Site.MY; //"my";

            if (serverFrom.contains(".co.th") || serverFrom.contains("/th"))
                site = Site.TH; //"th";

            if (serverFrom.contains("co.id") || serverFrom.contains("/id"))
                site = Site.ID; //"id";

            if (serverFrom.contains("/tw")){
                site = Site.TW;
            }

            if (serverFrom.contains("/it")){
                site = Site.IT;
            }

            if (serverFrom.contains("/au")){
                site = Site.AU;
            }

            //香港英文站用默认值english，香港中文站，设定为繁体
            if ((serverFrom.contains(".hk") && !serverFrom.contains("/en")) || serverFrom.contains("/hk"))
                site = Site.HK; //"hk";

            if (serverFrom.contains("zh.") || serverFrom.contains("/cn")) {
                //简体中文邮件
                site = Site.CN;
            }
        }
        return site;
    }

    /**
     * 根据ServerFrom获取市场
     * @param serverFrom 订单来源
     * */
    public static Market getMarketByServerFrom(String serverFrom){
        Market market = Market.US;

        if (serverFrom != null && !serverFrom.isEmpty()){
            serverFrom = serverFrom.toLowerCase();

            if (serverFrom.contains("jp.") || serverFrom.contains("/jp"))
                market = Market.JP;

            if (serverFrom.contains("co.kr") || serverFrom.contains("/kr"))
                market = Market.KR;

            if (serverFrom.contains("de.") || serverFrom.contains("/de"))
                market = Market.DE;

            if (serverFrom.contains("es.") || serverFrom.contains("/es"))
                market = Market.ES;

            if (serverFrom.contains("fr.") || serverFrom.contains("/fr"))
                market = Market.FR;

            if (serverFrom.contains("ru.") || serverFrom.contains("/ru"))
                market = Market.RU;

            if (serverFrom.contains("vn.") || serverFrom.contains("/vn"))
                market = Market.VN;

            if (serverFrom.contains(".sg") || serverFrom.contains("/sg"))
                market = Market.SG;

            if (serverFrom.contains(".my") || serverFrom.contains("/my"))
                market = Market.MY;

            if (serverFrom.contains(".co.th") || serverFrom.contains("/th"))
                market = Market.TH;

            if (serverFrom.contains("co.id") || serverFrom.contains("/id"))
                market = Market.ID;

            if (serverFrom.contains("/tw")){
                market = Market.TW;
            }

            if (serverFrom.contains("/it")){
                market = Market.IT;
            }

            if (serverFrom.contains("/au")){
                market = Market.AU;
            }

            if (serverFrom.contains(".hk") || serverFrom.contains("/hk"))
                market = Market.HK;

            if (serverFrom.contains("zh.") || serverFrom.contains("/cn")) {
                market = Market.CN;
            }
        }
        return market;
    }

    /**
     * 根据SID获取市场
     * @param sid SID(SkyScanner特有)
     * */
    public static Market getMarketBySid(Integer sid){
        if (sid == null){
            return Market.UK;
        }
        switch (sid){
            case 1239491:
                return Market.AE;
            case 1225346:
                return Market.AT;
            case 957204:
                return Market.AU;
            case 1024902:
                return Market.BR;
            case 957197:
                return Market.CA;
            case 1225334:
                return Market.CH;
            case 957212:
                return Market.CN;
            case 1225347:
                return Market.CO;
            case 1239495:
                return Market.CZ;
            case 957190:
                return Market.DE;
            case 957188:
                return Market.ES;
            case 957187:
                return Market.FR;
            case 1239490:
                return Market.GR;
            case 957211:
                return Market.HK;
            case 1024913:
                return Market.ID;
            case 957182:
                return Market.IE;
            case 1239493:
                return Market.IL;
            case 957201:
                return Market.IN;
            case 957189:
                return Market.IT;
            case 957215:
                return Market.JP;
            case 957214:
                return Market.KR;
            case 957206:
                return Market.MO;
            case 1225342:
                return Market.MX;
            case 1024898:
                return Market.MY;
            case 957186:
                return Market.NL;
            case 957203:
                return Market.NZ;
            case 1024901:
                return Market.PH;
            case 957181:
                return Market.PL;
            case 1239496:
                return Market.PT;
            case 957202:
                return Market.RU;
            case 1225340:
                return Market.SA;
            case 1239494:
                return Market.SE;
            case 957196:
                return Market.SG;
            case 957199:
                return Market.TH;
            case 957200:
                return Market.TR;
            case 957210:
                return Market.TW;
            case 957195:
                return Market.GB;
            case 957209:
                return Market.US;
            default:
                return Market.UK;
        }
    }

    /**
     * 根据语言获取其使用的字符集
     * @param lan 语言
     * */
    public static String getFontFamilyByLan(LanguageType lan){
        if (lan == null){
            return FONTFAMILY_DEFAULT;
        }

        switch (lan){
            case zh_TW:
            case zh_CN:
            case zh_HK:
            case ja_JP:
                return FONTFAMILY_ZH_HK;
            case ko_KR:
                return FONTFAMILY_KO_KR;
            case th_TH:
                return FONTFAMILY_TH_TH;
            default:
                return FONTFAMILY_DEFAULT;
        }
    }

    /**
     * 从订单信息获取联系人邮箱列表
     * @param orderDetail 订单详情
     * @return 联系人邮箱列表
     * */
    public static List<String> getContactEmailListFromOrder(OrderDetailModel orderDetail){
        if (orderDetail == null || orderDetail.getContactInfo() == null || StringUtils.isBlank(orderDetail.getContactInfo().getEmail())){
            return null;
        }

        List<String> recipientEmailList = new ArrayList<>();
        recipientEmailList.add(orderDetail.getContactInfo().getEmail());
        return recipientEmailList;
    }

    /**
     * 获取APP下载链接
     * */
    public static String getAppDownloadUrl(Locale emailLan, Integer aid, Integer sid){
        return APP_DOWNLOAD_URL_PATTERN.replace("${lang}",emailLan.getLanguage())
                .replace("${aid}",aid == null ? "" : aid.toString())
                .replace("${sid}",sid == null ? "" : sid.toString());
    }

    /**
     * 获取邮件预览URL
     * @param emailId EmailId
     * */
    public static String getEmailPreviewUrl(String emailId){
        return EmailConst.EMAIL_PREVIEW_URL.replace("${protocol}","http")
                .replace("${host}",Foundation.net().getHostAddress())
                .replace("${port}",Foundation.server().getEnv().isPRO() ? "8080" : "8080")
                .replace("${urlPattern}","test")
                .replace("${controller}","preview")
                .replace("${method}","emailpreview")
                .replace("${emailid}",emailId);
    }

    /**
     * 解析字符串航班类型
     * */
    public static FlightWay parseFlightWay(String flightWayStr){
        if (StringUtils.isBlank(flightWayStr)){
            return FlightWay.UNKNOWN;
        }

        FlightWay flightWay = FLIGHT_WAY_MAP.get(flightWayStr.toUpperCase());
        if (flightWay == null){
            flightWay = FlightWay.UNKNOWN;
        }
        return flightWay;
    }

    /**
     * 解析证件类型
     * */
    public static IdCardType parseIdType(String idTypeStr){
        if (StringUtils.isBlank(idTypeStr)){
            return IdCardType.BRC;
        }

        switch (idTypeStr){
            case "1":
                return IdCardType.ID;
            case "2":
                return IdCardType.PASSPORT;
            case "7":
                return IdCardType.HMP;
            case "8":
                return IdCardType.TP;
            case "23":
                return IdCardType.MTC;
            case "27":
                return IdCardType.BRC;
            case "28":
                return IdCardType.PRC;
            default:
                return IdCardType.OTHER;
        }
    }

    public static PassengerType parsePassengerType(String passengerTypeStr){
        if (StringUtils.isBlank(passengerTypeStr)){
            return PassengerType.UNKNOWN;
        }

        switch (passengerTypeStr){
            case "CHD":
                return PassengerType.CHD;
            case "INF":
                return PassengerType.INF;
            default:
                return PassengerType.ADT;
        }
    }

    /**
     * 生成订单详情URL
     * @param orderId 订单号
     * @param flightClass 国际国内
     * @param accessToken accessToken
     * @param serverFrom 订单来源
     * */
    public static String generateOrderDetailUrl(Long orderId,FlightClass flightClass,String accessToken,String serverFrom){

        if (StringUtils.isBlank(serverFrom) || orderId == null){
            return "";
        }

        //域名
        String domain = BusinessHelper.getDomain(BusinessHelper.getSiteByServerFrom(serverFrom), BusinessHelper.getTrademark(serverFrom));

        return BusinessConst.ORDERDETAILURLPATTERN
                .replace("${protocol}","https")
                .replace("${domain}",domain)
                .replace("${appname}",(flightClass == null || flightClass == FlightClass.International) ? "flights" : "chinaflights")
                .replace("${orderid}",String.valueOf(orderId))
                .replace("${accesstoken}",accessToken)
                .replace("&","&amp;");
    }
}
