/**
 * Created by kyxie on 2017/8/30.
 */
define(function () {

    /**
     * 获取所有语言
     * */
    function getLanguageTypeList() {
        var lanList = [];
        lanList.push('unspecified');
        lanList.push('en_US');
        lanList.push('en_HK');
        lanList.push('zh_HK');
        lanList.push('zh_CN');
        return lanList;
    }

    /**
     * 获取邮件模板列表
     * */
    function getEmailTemplateList() {
        var tmplList = [];
        tmplList.push('InsuranceIssued');
        tmplList.push('InsuranceChanged');
        tmplList.push('InsuranceRefunded');
        tmplList.push('PaymentSuccess');
        tmplList.push('ReservationConfirmation');
        return tmplList;
    };

    return{
        //获取邮件模板列表
        getEmailTemplateList : getEmailTemplateList,

        //获取发送语言列表
        getLanguageList : getLanguageTypeList
    }
});