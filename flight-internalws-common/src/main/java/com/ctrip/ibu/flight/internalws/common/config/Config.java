package com.ctrip.ibu.flight.internalws.common.config;

import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.internalws.models.application.ApplicationInfo;
import com.ctrip.ibu.flight.internalws.models.application.SwitchInfo;
import com.ctrip.ibu.flight.internalws.models.common.Trademark;
import com.ctrip.ibu.flight.internalws.models.config.ApplicationBasicInfo;
import com.ctrip.ibu.flight.internalws.models.config.ElkLogConfigInfo;
import com.ctrip.ibu.flight.internalws.models.config.EmailConfigInfo;
import com.ctrip.ibu.flight.internalws.models.constant.QConfigConst;
import com.ctrip.ibu.flight.internalws.models.message.EmailSenderModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import qunar.tc.qconfig.client.spring.QConfig;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.asList;

/**
 * QConfig核心配置文件，提供配置文件的热更新及缓存
 * Caution:如果将该Bean注入其他Bean中，需要注意调用Config的方法时获取到的配置总是最新的
 * Created by kyxie on 2017/7/10.
 */
@Component("config")
public class Config {

    //region --配置缓存--

    //Key-Value文件注册表
    private Map<String,Map<String,String>> configRegisterCache = new ConcurrentHashMap<>();

    //普通文本文件注册表
    private Map<String,String> normalFileRegisterCache = new ConcurrentHashMap<>();

    //复杂文件注册表(Json,Xml文件等反序列化的实体)
    private Map<String,Object> complexObjRegisterCache = new ConcurrentHashMap<>();

    //region --本地注册缓存更新方法区--
    /**
     * 更新配置文件缓存
     * @param configFileName 配置文件名
     * @param changedConfig 最新的配置文件内容
     * */
    private void updatePropertiesConfigCache(String configFileName,Map<String,String> changedConfig){
        if (configFileName != null && !configFileName.isEmpty()){
            if (this.configRegisterCache.containsKey(configFileName)){
                this.configRegisterCache.remove(configFileName);
            }
            if (changedConfig != null){
                this.configRegisterCache.put(configFileName,changedConfig);
            }
        }
    }

    /**
     * 更新普通文本文件缓存
     * @param configFileName 配置文件名
     * @param changedConfig 最新的文本文件内容
     * */
    private void updateNormalConfigCache(String configFileName,String changedConfig){
        if (configFileName != null && !configFileName.isEmpty()){
            if (this.normalFileRegisterCache.containsKey(configFileName)){
                this.normalFileRegisterCache.remove(configFileName);
            }
            if (changedConfig != null){
                this.normalFileRegisterCache.put(configFileName,changedConfig);
            }
        }
    }

    /**
     * 更新复杂类型文件缓存
     * @param configFileName 配置文件名
     * @param changedConfig 最新的复杂类型对象
     * */
    private <T extends Object> void updateComplexConfigCache(String configFileName,T changedConfig){
        if (configFileName != null && !configFileName.isEmpty()){
            if (this.complexObjRegisterCache.containsKey(configFileName)){
                this.complexObjRegisterCache.remove(configFileName);
            }
            if (changedConfig != null){
                this.complexObjRegisterCache.put(configFileName,changedConfig);
            }
        }
    }
    //endregion

    //endregion

    //region --对外接口--

    /**
     * 邮件测试收件人列表
     * */
    public List<String> getEmailSendTestRecipientList(){
        return asList(configProfileProperties.get(QConfigConst.EMAILSENDTESTRECIPIENTLIST).split(",")) ;
    }

    /**
     * 邮件测试模版列表
     * */
    public List<String> getEmailSendTestTemplateTypeList(){
        return asList(configProfileProperties.get(QConfigConst.EMAILSENDTESTTEMPLATETYPELIST).split(","));
    }

    /**
     * 获取应用信息
     * */
    public ApplicationInfo getApplicationInfo(){
        ApplicationInfo applicationInfo = new ApplicationInfo();

        applicationInfo.setApplicationBasicInfo(getApplicationBasicInfo());
        applicationInfo.setElkLogConfigInfo(getElkLogConfigInfo());
        applicationInfo.setEmailConfigInfo(getEmailConfigInfo());
        applicationInfo.setSwitchConfigInfo(getSwitchConfigInfo());

        return applicationInfo;
    }

    /**
     * 获取应用的基础信息
     * */
    public ApplicationBasicInfo getApplicationBasicInfo(){
        ApplicationBasicInfo applicationBasicInfo = new ApplicationBasicInfo();
        applicationBasicInfo.setAppId(Foundation.app().getAppId());
        applicationBasicInfo.setJdkVersion(Foundation.app().getJdkVersion());
        applicationBasicInfo.setCtripCopyrightNoticePattern(configProfileProperties.get(QConfigConst.CTRIPCOPYRIGHTNOTICEPATTERN));
        return applicationBasicInfo;
    }

    /**
     * 获取Elk相关配置
     * */
    public ElkLogConfigInfo getElkLogConfigInfo(){
        ElkLogConfigInfo elkLogConfigInfo = new ElkLogConfigInfo();
        elkLogConfigInfo.setElkScenrio(Foundation.app().getProperty("elkScenrio","ibu-flight-internalws-java"));
        return elkLogConfigInfo;
    }

    /**
     * 获取Email配置信息
     * */
    public EmailConfigInfo getEmailConfigInfo(){
        EmailConfigInfo emailConfigInfo = new EmailConfigInfo();

        //基础信息
        emailConfigInfo.setEmailSendCode(Foundation.app().getProperty("emailSendCode","17090002"));
        emailConfigInfo.setEmailTemplateId(Foundation.app().getProperty("emailTemplateId","17090002"));
        emailConfigInfo.setEmailCharset(Foundation.app().getProperty("emailCharset","UTF-8"));

        //缓存模板文件名
        String cachedTmplNameListStr = Foundation.app().getProperty("cachedTemplateNameSet","");
        if (cachedTmplNameListStr != null && !cachedTmplNameListStr.isEmpty()){
            String[] cachedTmplNameArr = cachedTmplNameListStr.split(",");
            if (cachedTmplNameArr != null && cachedTmplNameArr.length >= 1){
                List<String> tmplNameList = new ArrayList<>();
                for (String tmplName : cachedTmplNameArr){
                    tmplNameList.add(tmplName);
                }
                emailConfigInfo.setCachedTemplatesNameList(tmplNameList);
            }
        }

        //缓存多语言文件名
        String cachedMessageResourceNameListStr = Foundation.app().getProperty("cachedMessageResourceNameSet","");
        if (cachedMessageResourceNameListStr != null && !cachedMessageResourceNameListStr.isEmpty()){
            String[] cachedMessageResourceNameArr = cachedMessageResourceNameListStr.split(",");
            if (cachedMessageResourceNameArr != null && cachedMessageResourceNameArr.length >= 1){
                List<String> messageResourceNameList = new ArrayList<>();
                for (String messageResourceName : cachedMessageResourceNameArr){
                    messageResourceNameList.add(messageResourceName);
                }
                emailConfigInfo.setCachedMessageResourcesNameList(messageResourceNameList);
            }
        }

        return emailConfigInfo;
    }

    /**
     * 获取EmailSender
     *
     * @param locale    Locale
     * @param trademark 商标
     */
    public EmailSenderModel getEmailSenderInfo(Locale locale, Trademark trademark) {
        EmailSenderModel senderModel = new EmailSenderModel();

        String senderKey = String.format("%s_%s_%s", QConfigConst.EMAILSENDER_PREFIX, String.format("%s_%s", locale.getLanguage(), locale.getCountry()), trademark.toString()).toLowerCase();
        String senderNameKey = String.format("%s_%s_%s", QConfigConst.EMAILSENDERNAME_PREFIX, String.format("%s_%s", locale.getLanguage(), locale.getCountry()), trademark.toString()).toLowerCase();

        String sender = configProfileProperties.get(senderKey);
        String senderName = configProfileProperties.get(senderNameKey);
        if ((sender == null || sender.isEmpty()) && (senderName == null || senderName.isEmpty())) {
            senderModel = getDefaultEmailSender(trademark);
        } else {
            senderModel.setSender(sender);
            senderModel.setSenderName(senderName);
        }

        return senderModel;
    }

    /**
     * 获取默认Sender
     */
    private EmailSenderModel getDefaultEmailSender(Trademark trademark) {
        String senderKey = String.format("%s_%s_%s", QConfigConst.EMAILSENDER_PREFIX, "en_US", String.valueOf(trademark)).toLowerCase();
        String senderNameKey = String.format("%s_%s_%s", QConfigConst.EMAILSENDERNAME_PREFIX, "en_US", String.valueOf(trademark)).toLowerCase();

        EmailSenderModel senderModel = new EmailSenderModel();
        senderModel.setSender(configProfileProperties.get(senderKey));
        senderModel.setSenderName(configProfileProperties.get(senderNameKey));
        return senderModel;
    }

    /**
     * 获取开关列表
     * */
    public List<SwitchInfo> getSwitchConfigInfo(){
        List<SwitchInfo> switchInfos = new ArrayList<>();

        if (this.switchConfig != null && !this.switchConfig.isEmpty()){
            for (Map.Entry<String,String> entry : switchConfig.entrySet()){
                SwitchInfo switchInfo = new SwitchInfo();
                switchInfo.setSwitchKey(entry.getKey());
                String switchValue = entry.getValue();
                if ("1".equals(switchValue) || "TRUE".equalsIgnoreCase(switchValue) || "ON".equalsIgnoreCase(switchValue)){
                    switchInfo.setSwitchValue(true);
                }else {
                    switchInfo.setSwitchValue(false);
                }
                switchInfos.add(switchInfo);
            }
        }
        return switchInfos;
    }

    /**
     * 获取单个开关的值
     * @param switchKey 开关名，不区分大小写，1，true,on为开启，其他值关闭
     * @return true：开关开启，false：开关关闭
     * */
    public boolean getSwitch(String switchKey){
        boolean switchVal = false;
        if (switchKey != null && !switchKey.isEmpty()){
            String switchOrigVal = this.switchConfig.get(switchKey);
            if (StringUtils.isNotBlank(switchOrigVal)){
                if ("1".equals(switchOrigVal) || "TRUE".equalsIgnoreCase(switchOrigVal) || "ON".equalsIgnoreCase(switchOrigVal)){
                    switchVal = true;
                }
            }
        }

        return switchVal;
    }

    /**
     * 获取订单详情URL Pattern
     * @param locale locale
     * */
    public String getOrderDetailUrlPattern(Locale locale){
        if (locale == null) {
            return null;
        }
        String qconfigKey = String.format(QConfigConst.ORDERDETAILURLPATTERNKEY, locale.getLanguage(), locale.getCountry()).toLowerCase();
        String urlPattern = configProfileProperties.get(qconfigKey);

        //不存在默认使用EN_US
        if (urlPattern == null || urlPattern.isEmpty()) {
            qconfigKey = String.format(QConfigConst.ORDERDETAILURLPATTERNKEY,"en","US").toLowerCase();
            urlPattern = configProfileProperties.get(qconfigKey);
        }

        return urlPattern;
    }
    public String getEmailJumpUrlPattern(Locale locale,String qConfigConstKey,Trademark trademark){
        if (locale == null) {
            return null;
        }
        String qconfigKey = String.format(qConfigConstKey, locale.getLanguage(), locale.getCountry(),String.valueOf(trademark)).toLowerCase();
        String urlPattern = configProfileProperties.get(qconfigKey);

        //不存在默认使用EN_US Ctrip
        if (urlPattern == null || urlPattern.isEmpty()) {
            qconfigKey = String.format(QConfigConst.ORDERDETAILURLPATTERNKEY,"en","US","ctrip").toLowerCase();
            urlPattern = configProfileProperties.get(qconfigKey);
        }

        return urlPattern;
    }


    /**
     * 获取Key-Value格式的Properties文件
     * @param configFileName 配置文件名
     * */
    public Map<String,String> getPropertiesConfig(String configFileName){
        if (configFileName != null && !configFileName.isEmpty()){
            if (this.configRegisterCache.containsKey(configFileName)){
                return this.configRegisterCache.get(configFileName);
            }
        }

        return null;
    }

    /**
     * 获取普通文件
     * @param configFileName 配置文件名
     * */
    public String getNormalConfig(String configFileName){
        if (configFileName != null && !configFileName.isEmpty()){
            if (this.normalFileRegisterCache.containsKey(configFileName)){
                return this.normalFileRegisterCache.get(configFileName);
            }
        }

        return null;
    }

    /**
     * 获取复杂类型的值
     * @param configFileName 配置文件名
     * @param clazz 类类型
     * */
    public <T> T getComplexObjConfig(String configFileName,Class<T> clazz){
        if (configFileName != null && !configFileName.isEmpty()){
            if (this.complexObjRegisterCache.containsKey(configFileName)){
                try {
                    return (T) this.complexObjRegisterCache.get(configFileName);
                }catch (Exception e){
                    return null;
                }
            }
        }

        return null;
    }
    //endregion

    //region --监听器--

    //监听器
    private Set<EventListener> listeners = new HashSet<>();

    /**
     * 执行properties文件改变监听器
     * */
    private void execPropertiesFileChangedListener(String fileName ,Map<String,String> changedConfig){
        if (!this.listeners.isEmpty()){
            for (EventListener listener : this.listeners){
                if (listener instanceof PropertiesFileChangedListener){

                    Map<String,Map<String,String>> fileConfig = new HashMap<>();
                    fileConfig.put(fileName,changedConfig);

                    //执行监听器
                    ((PropertiesFileChangedListener)listener).onConfigFileChanged(new PropertiesFileChangedEvent(this,fileConfig));
                }
            }
        }
    }


    /**
     * 执行普通文件改变监听器
     * */
    private void execNormalFileChangedListener(String fileName,String changedFileContent){
        if (!this.listeners.isEmpty()){
            for (EventListener listener : this.listeners){
                if (listener instanceof NormalFileChangedListener){

                    Map<String,String> fileContent = new HashMap<>();
                    fileContent.put(fileName,changedFileContent);

                    //执行监听器
                    ((NormalFileChangedListener)listener).onFileContentChanged(new NormalFileChangedEvent(this,fileContent));
                }
            }
        }
    }


    /**
     * 新增监听器
     * @param listener 监听器
     * */
    public void addEventListener(EventListener listener){
        this.listeners.add(listener);
    }

    //endregion

    //region --系统全局配置--
    @QConfig(QConfigConst.CONFIGFILENAME_CONFIGPROFILEPROP)
    private Map<String,String> configProfileProperties;

    @QConfig(QConfigConst.CONFIGFILENAME_CONFIGPROFILEPROP)
    public void onConfigProfilePropertiesChanged(Map<String,String> changedConf){
        updatePropertiesConfigCache(QConfigConst.CONFIGFILENAME_CONFIGPROFILEPROP,changedConf);
        execPropertiesFileChangedListener(QConfigConst.CONFIGFILENAME_CONFIGPROFILEPROP,changedConf);
    }
    //endregion

    //region --开关--
    @QConfig(QConfigConst.CONFIGFILENAME_SWITCH)
    private Map<String,String> switchConfig;

    @QConfig(QConfigConst.CONFIGFILENAME_SWITCH)
    public void onSwtichConfigChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.CONFIGFILENAME_SWITCH,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.CONFIGFILENAME_SWITCH,changedConfig);
    }
    //endregion

    //region --多语言--
    //美国英文
    @QConfig(QConfigConst.MESSAGERESOURCE_EN_US)
    public void onMessageResourceEnUsChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_EN_US,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_EN_US,changedConfig);
    }

    //日文
    @QConfig(QConfigConst.MESSAGERESOURCE_JA_JP)
    public void onMessageResourceJaJpChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_JA_JP,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_JA_JP,changedConfig);
    }

    //韩文
    @QConfig(QConfigConst.MESSAGERESOURCE_KO_KR)
    public void onMessageResourceKoKrChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_KO_KR,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_KO_KR,changedConfig);
    }

    //简体中文
    @QConfig(QConfigConst.MESSAGERESOURCE_ZH_CN)
    public void onMessageResourceZhCnChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_ZH_CN,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_ZH_CN,changedConfig);
    }

    //繁体中文
    @QConfig(QConfigConst.MESSAGERESOURCE_ZH_HK)
    public void onMessageResourceZhHkChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_ZH_HK,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_ZH_HK,changedConfig);
    }

    //德语
    @QConfig(QConfigConst.MESSAGERESOURCE_DE_DE)
    public void onMessageResourceDeDeChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_DE_DE,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_DE_DE,changedConfig);
    }

    //澳大利亚英文
    @QConfig(QConfigConst.MESSAGERESOURCE_EN_AU)
    public void onMessageResourceEnAuChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_EN_AU,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_EN_AU,changedConfig);
    }

    //西班牙问文
    @QConfig(QConfigConst.MESSAGERESOURCE_ES_ES)
    public void onMessageResourceEsEsChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_ES_ES,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_ES_ES,changedConfig);
    }

    //法文
    @QConfig(QConfigConst.MESSAGERESOURCE_FR_FR)
    public void onMessageResourceFrFrChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_FR_FR,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_FR_FR,changedConfig);
    }

    //印尼语
    @QConfig(QConfigConst.MESSAGERESOURCE_ID_ID)
    public void onMessageResourceIdIdChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_ID_ID,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_ID_ID,changedConfig);
    }

    //马来语
    @QConfig(QConfigConst.MESSAGERESOURCE_MS_MY)
    public void onMessageResourceMsMyChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_MS_MY,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_MS_MY,changedConfig);
    }

    //俄语
    @QConfig(QConfigConst.MESSAGERESOURCE_RU_RU)
    public void onMessageResourceRuRuChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_RU_RU,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_RU_RU,changedConfig);
    }

    //泰语
    @QConfig(QConfigConst.MESSAGERESOURCE_TH_TH)
    public void onMessageResourceThThChanged(Map<String,String> changedConfig){
        updatePropertiesConfigCache(QConfigConst.MESSAGERESOURCE_TH_TH,changedConfig);
        execPropertiesFileChangedListener(QConfigConst.MESSAGERESOURCE_TH_TH,changedConfig);
    }
    //endregion

    //region --邮件模板--
    //支付成功邮件
    @QConfig(QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL)
    public void onPaymentSuccessTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL,newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL,newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL_SC)
    public void onScPaymentSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_PAYMENTSUCCESSEMAIL_SC, newestFileContent);
    }

    //出保邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL)
    public void onInsuranceIssuedTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL,newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL,newestFileContent);
    }

    //出保邮件模板-V1
    @QConfig(QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL_V1)
    public void onInsuranceIssuedV1TemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL_V1,newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_INSURANCEISSUEDEMAIL_V1,newestFileContent);
    }

    //退保邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_INSURANCEREFUNDEDEMAIL)
    public void onInsuranceRefundedTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_INSURANCEREFUNDEDEMAIL,newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_INSURANCEREFUNDEDEMAIL,newestFileContent);
    }

    //改保邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL)
    public void onInsuranceChangedTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL,newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL,newestFileContent);
    }

    //改保邮件模板-V1
    @QConfig(QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL_V1)
    public void onInsuranceChangedV1TemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL_V1,newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_INSURANCECHANGEDEMAIL_V1,newestFileContent);
    }

    //改签成功邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL)
    public void onNewFlightSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL, newestFileContent);
    }

    //SC改签成功邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL_SC)
    public void onScNewFlightSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTSUCCESSEMAIL_SC, newestFileContent);
    }

    //改签咨询单邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED)
    public void onNewflightconsultingverifiedTemplate(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED_SC)
    public void onScNewflightconsultingverifiedTemplate(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_EMAIL_NEWFLIGHTCONSULTINGVERIFIED_SC, newestFileContent);
    }

    //行李额
    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE)
    public void onXProductBaggageTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE_SC)
    public void onScXProductBaggageTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGE_SC, newestFileContent);
    }

    //餐食
    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS)
    public void onXProductMealsTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS_SC)
    public void onScXProductMealsTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTMEALS_SC, newestFileContent);
    }

    //姓名更改
    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY)
    public void onXProductNameModifyTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY_SC)
    public void onScXProductNameModifyTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTNAMEMODIFY_SC, newestFileContent);
    }

    //证件修改
    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY)
    public void onXProductPaperModifyTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY_SC)
    public void onScXProductPaperModifyTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTPAPERMODIFY_SC, newestFileContent);
    }

    //值机选座
    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT)
    public void onXProductCheckSeatTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT_SC)
    public void onScXProductCheckSeatTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTCHECKSEAT_SC, newestFileContent);
    }

    //特殊服务
    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST)
    public void onXProductSpecialRequestTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST_SC)
    public void onScXProductSpecialRequestTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTSPECIALREQUEST_SC, newestFileContent);
    }

    //退票咨询单
    @QConfig(QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL)
    public void onRefundAskDetailTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL_SC)
    public void onSCRefundAskDetailTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_REFUNDASKDETAIL_SC, newestFileContent);
    }

    //退票完成
    @QConfig(QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS)
    public void onRefundSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS_SC)
    public void onSCRefundSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_REFUNDSUCCESS_SC, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGESUCCESS)
    public void onXProductBaggageSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGESUCCESS, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGESUCCESS, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGEFAILED)
    public void onXProductBaggageFailedTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGEFAILED, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_XPRODUCTBAGGAGEFAILED, newestFileContent);
    }
    //订单取消
    @QConfig(QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL)
    public void onOrderCanceledTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL_SC)
    public void onSCOrderCanceledTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_PAYMENTCANCELEDEMAIL_SC, newestFileContent);
    }

    //支付失败
    @QConfig(QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL)
    public void onPaymentFailedTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL_SC)
    public void onSCPaymentFailedTemplateChanged(String newestFileContent){
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_PAYMENTFAILEDMAIL_SC, newestFileContent);
    }

    //改签取消邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL)
    public void onNewFlightCanceledTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL, newestFileContent);
    }

    //SC改签取消邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL_SC)
    public void onScNewFlightCanceledTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTCANCELEDEMAIL_SC, newestFileContent);
    }

    //改签支付成功模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL)
    public void onNewFlightPaymentSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL, newestFileContent);
    }

    //SC改签支付成功邮件模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL_SC)
    public void onScNewFlightPaymentSuccessTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTPAYMENTSUCCESSEMAIL_SC, newestFileContent);
    }

    //改签待支付模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL)
    public void onNewFlightTobePaidTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL, newestFileContent);
    }

    //SC改签待支付模板
    @QConfig(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL_SC)
    public void onScNewFlightTobePaidTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_NEWFLIGHTTOBEPAIDEMAIL_SC, newestFileContent);
    }
    //报销凭证
    @QConfig(QConfigConst.TEMPLATEFILENAME_ERECIPIENT)
    public void onERecipientTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ERECIPIENT, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ERECIPIENT, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_ERECIPIENT_SC)
    public void onSCERecipientTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ERECIPIENT_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ERECIPIENT_SC, newestFileContent);
    }

    //报销凭证附件
    @QConfig(QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT)
    public void onERecipientAttachmentTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT_SC)
    public void onSCERecipientAttachmentTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ERECIPIENTATTACHMENT_SC, newestFileContent);
    }

    //行程单
    @QConfig(QConfigConst.TEMPLATEFILENAME_ITINERARY)
    public void onItineraryDetailTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ITINERARY, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ITINERARY, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_ITINERARY_SC)
    public void onSCItineraryDetailTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ITINERARY_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ITINERARY_SC, newestFileContent);
    }

    //行程单附件
    @QConfig(QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT)
    public void onItineraryDetailAttachmentTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT_SC)
    public void onSCItineraryDetailAttachmentTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_ITINERARYATTACHMENT_SC, newestFileContent);
    }

    //确认邮件
    @QConfig(QConfigConst.TEMPLATEFILENAME_RESERVATIONCONFIRMATION)
    public void onReservationConfirmationTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_RESERVATIONCONFIRMATION, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_RESERVATIONCONFIRMATION, newestFileContent);
    }

    @QConfig(QConfigConst.TEMPLATEFILENAME_RESERVATIONCONFIRMATION_SC)
    public void onReservationConfirmationAttachmentTemplateChanged(String newestFileContent) {
        updateNormalConfigCache(QConfigConst.TEMPLATEFILENAME_RESERVATIONCONFIRMATION_SC, newestFileContent);
        execNormalFileChangedListener(QConfigConst.TEMPLATEFILENAME_RESERVATIONCONFIRMATION_SC, newestFileContent);
    }

    //endregion

    //region --Json复杂类型--

    //endregion
}
