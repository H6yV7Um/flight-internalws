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
    <title>${getResource("ibujpinsuranceIssued")}</title>
<#else>
    <title>${getResource("insuranceIssued")}</title>
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
                            <tr><td style="font-size:30px;padding-bottom:20px;padding-top:20px;">${getResource("ibujpinsuranceIssued")}</td></tr>
                        <#else>
                            <tr><td style="font-size:30px;padding-bottom:20px;padding-top:20px;">${getResource("insuranceIssued")}</td></tr>
                        </#if>
                            <tr>
                                <td style="font-size:14px;padding-bottom:12px;">
                                <#if IBUJP?? && IBUJP>
                                    <strong>${orderDetail.contactInfo.name!}</strong> ${getResource("ibujprespect")} ,
                                    <br>
                                <#else>
                                ${getResource("respect")} <strong>${orderDetail.contactInfo.name!}</strong>,
                                    <br>
                                </#if>
                                <#switch insType>
                                    <#case 'c2c'>
                                    ${getResource("c2cInsuranceIssuedNotify")}
                                        <#break>
                                    <#case 'sgx'>
                                    ${getResource("sgxInsuranceIssuedNotify")}
                                        <#break>
                                    <#case 'jwsgx'>
                                        <#if IBUJP?? && IBUJP>
                                        ${getResource("ibujpjwsgxInsuranceIssuedNotify")}
                                        <#else>
                                        ${getResource("jwsgxInsuranceIssuedNotify")}
                                        </#if>
                                        <#break>
                                    <#default>
                                    ${getResource("insuranceIssuedNotify")}
                                </#switch>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom:20px;">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                    <#--航意险-->
                                    <#if (c2cInsuranceDetailList?? && c2cInsuranceDetailList?size>=1)>
                                        <tr>
                                            <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insuranceName")}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${getResource("c2cInsurance")}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insuranceNo")}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                <#list c2cInsuranceDetailList as insuranceDetail>
                                                    <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                </#list>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insurancePolicy")} <a style="color:#999999;">(${getResource("clickToDownload")})</a></td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                <#list c2cInsuranceDetailList as insuranceDetail>
                                                    <p style="padding:0;margin:0;"><a href="${insuranceDetail.policyDownloadUrl!""}" target="_blank" style="color:#1899f2;">${insuranceDetail.passengerName!""}</a></p>
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
                                            <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insuranceName")}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${getResource("sgxInsurance")}</td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insuranceNo")}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                <#list sgxInsuranceDetailList as insuranceDetail>
                                                    <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                </#list>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insurancePolicy")} <a style="color:#999999;">(${getResource("clickToDownload")})</a></td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                <#list sgxInsuranceDetailList as insuranceDetail>
                                                    <p style="padding:0;margin:0;"><a href="${insuranceDetail.policyDownloadUrl!""}" target="_blank" style="color:#1899f2;">${insuranceDetail.passengerName!""}</a></p>
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
                                            <tr>
                                                <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("ibujpinsuranceName")}</td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${getResource("ibujpjwsgxInsurance")}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("ibujpinsuranceNo")}</td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                    <#list jwsgxInsuranceDetailList as insuranceDetail>
                                                        <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                    </#list>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("ibujpinsurancePolicy")} <a style="color:#999999;">(${getResource("ibujpclickToDownload")})</a></td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                    <#list jwsgxInsuranceDetailList as insuranceDetail>
                                                        <p style="padding:0;margin:0;"><a href="${insuranceDetail.policyDownloadUrl!""}" target="_blank" style="color:#1899f2;">${insuranceDetail.passengerName!""}</a></p>
                                                    </#list>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="border-top:1px solid #ededed;"></td>
                                            </tr>
                                        <#else>
                                            <tr>
                                                <td valign="top" width="35%" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insuranceName")}</td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;font-weight:700;">${getResource("jwsgxInsurance")}</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insuranceNo")}</td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                    <#list jwsgxInsuranceDetailList as insuranceDetail>
                                                        <p style="padding:0;margin:0;">${insuranceDetail.insuranceNo}</p>
                                                    </#list>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${getResource("insurancePolicy")} <a style="color:#999999;">(${getResource("clickToDownload")})</a></td>
                                                <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                    <#list jwsgxInsuranceDetailList as insuranceDetail>
                                                        <p style="padding:0;margin:0;"><a href="${insuranceDetail.policyDownloadUrl!""}" target="_blank" style="color:#1899f2;">${insuranceDetail.passengerName!""}</a></p>
                                                    </#list>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="border-top:1px solid #ededed;"></td>
                                            </tr>
                                        </#if>
                                    </#if>
                                    <#if IBUJP?? && IBUJP>
                                        <tr>
                                            <td colspan="2" style="color:#999999;padding-top:10px;">${getResource("insuranceReminder_IBUJP_Part1")}
                                                <a href="https://pages.trip.com/m/pdf/claims-staa.pdf" target="_blank" style="color:#999999;">${getResource("insuranceReminder_IBUJP_Part2")}</a>${getResource("insuranceReminder_IBUJP_Part3")}</td>
                                        </tr>
                                    <#elseif IBUHK1_5?? && IBUHK1_5>
                                        <tr>
                                            <#if orderDetail.trademark == "Trip">
                                                <td colspan="2" style="color:#999999;padding-top:10px;">${getResource("insuranceReminder")?replace('${"$"}{trademark}','Trip.com')}</td>
                                            <#elseif orderDetail.trademark == "Ctrip">
                                                <td colspan="2" style="color:#999999;padding-top:10px;">${getResource("insuranceReminder")?replace('${"$"}{trademark}','Ctrip')}</td>
                                            </#if>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="color:#999999;padding-top:10px;font-size: 12px;">${getResource("insuranceReminder_IBUHK1_5")} (
                                                <a href="http://www.ia.org.hk" target="_blank" style="color: #1899f2;" title="www.ia.org.hk">www.ia.org.hk</a>)</td>
                                        </tr>
                                    <#else >
                                        <tr>
                                            <#if orderDetail.trademark == "Trip">
                                                <td colspan="2" style="color:#999999;padding-top:10px;">${getResource("insuranceReminder")?replace('${"$"}{trademark}','Trip.com')}</td>
                                            <#elseif orderDetail.trademark == "Ctrip">
                                                <td colspan="2" style="color:#999999;padding-top:10px;">${getResource("insuranceReminder")?replace('${"$"}{trademark}','Ctrip')}</td>
                                            </#if>
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