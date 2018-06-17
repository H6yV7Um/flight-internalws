<!DOCTYPE html>
<#assign insType=''>
<#if (c2cInsuranceDetailList?? && !(sgxInsuranceDetailList??) && !(jwsgxInsuranceDetailList??))>
    <#assign insType='c2c'>
<#elseif (!(c2cInsuranceDetailList??) && sgxInsuranceDetailList?? && !(jwsgxInsuranceDetailList??))>
    <#assign insType='sgx'>
<#elseif (!(c2cInsuranceDetailList??) && !(sgxInsuranceDetailList??) && jwsgxInsuranceDetailList??)>
    <#assign insType='jwsgx'>
</#if>
<#list jwsgxInsuranceDetailList as insuranceDetail>
    <#if insuranceDetail.typeId == 'IBUJP'>
        <#assign IBUJP = true/>
    </#if>
    <#if insuranceDetail.typeId ='IBUHK1' || insuranceDetail.typeId ='IBUHK2' || insuranceDetail.typeId ='IBUHK3' || insuranceDetail.typeId ='IBUHK4' || insuranceDetail.typeId ='IBUHK5' ||
    insuranceDetail.typeId ='IBUHKN1' || insuranceDetail.typeId ='IBUHKN2' || insuranceDetail.typeId ='IBUHKN3' || insuranceDetail.typeId ='IBUHKN4' || insuranceDetail.typeId ='IBUHKN5'>
        <#assign IBUHK1_5 = true/>
    </#if>
</#list>
<html>
<head>
    <meta charset="utf-8">
<#if IBUJP?? && IBUJP>
    <title>${sharkMessageResource.ibujpinsuranceRefunded}</title>
<#else>
    <title>${sharkMessageResource.insuranceRefunded}</title>
</#if>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<style type="text/css">
    td[class="blue"]:hover{cursor:pointer;background-color:#3498db;}
    td[class="orange"]:hover{cursor:pointer;background-color:#ffc600;}
</style>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <!--邮件头-->
            ${emailHeader}
            <table width="600" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:0 20px;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <#if IBUJP?? && IBUJP>
                            <tr><td style="font-size:30px;padding-bottom:20px;padding-top:20px;">${sharkMessageResource.ibujpinsuranceRefunded}</td></tr>
                        <#else>
                            <tr><td style="font-size:30px;padding-bottom:20px;padding-top:20px;">${sharkMessageResource.insuranceRefunded}</td></tr>
                        </#if>

                            <tr>
                                <td style="font-size:14px;padding-bottom:12px;">
                                <#if IBUJP?? && IBUJP>
                                    <strong>${orderDetail.contactInfo.name!}</strong> ${sharkMessageResource.ibujprespect} ,
                                    <br>
                                <#else>
                                ${sharkMessageResource.respect} <strong>${orderDetail.contactInfo.name!}</strong>,
                                    <br>
                                </#if>
                                <#switch insType>
                                    <#case 'c2c'>
                                    ${sharkMessageResource.c2cRefundedNotify}
                                        <#break>
                                    <#case 'sgx'>
                                    ${sharkMessageResource.sgxInsuranceRefundedNotify}
                                        <#break>
                                    <#case 'jwsgx'>
                                        <#if IBUJP?? && IBUJP>
                                        ${sharkMessageResource.ibujpjwsgxInsuranceRefundedNotify}
                                        <#else>
                                        ${sharkMessageResource.jwsgxInsuranceRefundedNotify}
                                        </#if>
                                        <#break>
                                    <#default>
                                    ${sharkMessageResource.insuranceRefundedNotify}
                                </#switch>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom:10px;">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                    <#--航意险-->
                                    <#if (c2cInsuranceDetailList?? && c2cInsuranceDetailList?size>=1)>
                                        <tr>
                                            <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insuranceName}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${sharkMessageResource.c2cInsurance}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insuranceNo}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                <#list c2cInsuranceDetailList as insuranceDetail>
                                                    <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                </#list>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="border-top:1px solid #ededed;"></td>
                                        </tr>
                                    </#if>
                                    <#--旅行险-->
                                    <#if (sgxInsuranceDetailList?? && sgxInsuranceDetailList?size>=1)>
                                        <tr>
                                            <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insuranceName}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${sharkMessageResource.sgxInsurance}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insuranceNo}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                <#list sgxInsuranceDetailList as insuranceDetail>
                                                    <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                </#list>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="border-top:1px solid #ededed;"></td>
                                        </tr>
                                    </#if>
                                    <#--境外旅行险-->
                                    <#if (jwsgxInsuranceDetailList?? && jwsgxInsuranceDetailList?size>=1)>
                                        <#if IBUJP?? && IBUJP>
                                            <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.ibujpinsuranceName}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${sharkMessageResource.ibujpjwsgxInsurance}</td>
                                            <tr>
                                                <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.ibujpinsuranceNo}</td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                    <#list jwsgxInsuranceDetailList as insuranceDetail>
                                                        <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                    </#list>
                                                </td>
                                            </tr>
                                        <#else>
                                            <tr>
                                                <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insuranceName}</td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${sharkMessageResource.jwsgxInsurance}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insuranceNo}</td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                    <#list jwsgxInsuranceDetailList as insuranceDetail>
                                                        <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                    </#list>
                                                </td>
                                            </tr>
                                        </#if>
                                        <tr>
                                            <td colspan="2" style="border-top:1px solid #ededed;"></td>
                                        </tr>
                                    </#if>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--邮件尾-->
            ${emailFooter}
        </td>
    </tr>
</table>
</body>
</html>