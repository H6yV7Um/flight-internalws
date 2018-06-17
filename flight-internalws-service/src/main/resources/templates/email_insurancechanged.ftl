<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sharkMessageResource.insuranceChanged}</title>
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
                            <tr><td style="font-size:30px;padding-bottom:20px;padding-top:20px;">${sharkMessageResource.insuranceChanged}</td></tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:12px;">
                                ${sharkMessageResource.respect} <strong>${orderDetail.contactInfo.name!}</strong>,
                                    <br>
                                <#if (c2cInsuranceDetailList?? && !(sgxInsuranceDetailList??) && !(jwsgxInsuranceDetailList??))>
                                ${sharkMessageResource.c2cInsuranceChangedNotify}
                                <#elseif (!(c2cInsuranceDetailList??) && sgxInsuranceDetailList?? && !(jwsgxInsuranceDetailList??))>
                                ${sharkMessageResource.sgxInsuranceChangedNotify}
                                <#elseif (!(c2cInsuranceDetailList??) && !(sgxInsuranceDetailList??) && jwsgxInsuranceDetailList??)>
                                ${sharkMessageResource.jwsgxInsuranceChangedNotify}
                                <#else>
                                ${sharkMessageResource.insuranceChangedNotify}
                                </#if>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom:20px;">
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
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insurancePolicy} <a style="color:#999999;">(${sharkMessageResource.clickToDownload})</a></td>
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
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insurancePolicy} <a style="color:#999999;">(${sharkMessageResource.clickToDownload})</a></td>
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
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.insurancePolicy} <a style="color:#999999;">(${sharkMessageResource.clickToDownload})</a></td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;">
                                                <#list jwsgxInsuranceDetailList as insuranceDetail>
                                                    <p style="padding:0;margin:0;"><a href="${insuranceDetail.policyDownloadUrl!""}" target="_blank" style="color:#1899f2;">${insuranceDetail.passengerName!""}</a></p>
                                                    <#if insuranceDetail.typeId == 'IBUJP'>
                                                        <#assign IBUJP = true/>
                                                    </#if>
                                                    <#if insuranceDetail.typeId ='IBUHK1' || insuranceDetail.typeId ='IBUHK2' || insuranceDetail.typeId ='IBUHK3' || insuranceDetail.typeId ='IBUHK4' || insuranceDetail.typeId ='IBUHK5' ||
                                                    insuranceDetail.typeId ='IBUHKN1' || insuranceDetail.typeId ='IBUHKN2' || insuranceDetail.typeId ='IBUHKN3' || insuranceDetail.typeId ='IBUHKN4' || insuranceDetail.typeId ='IBUHKN5'>
                                                        <#assign IBUHK1_5 = true/>
                                                    </#if>
                                                </#list>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="border-top:1px solid #ededed;"></td>
                                        </tr>
                                    </#if>
                                    <#if IBUJP?? && IBUJP>
                                        <tr>
                                            <td colspan="2" style="color:#999999;padding-top:10px;">${sharkMessageResource.insuranceReminder_IBUJP_Part1}
                                                <a href="https://pages.trip.com/m/pdf/claims-staa.pdf" target="_blank" style="color:#999999;">${sharkMessageResource.insuranceReminder_IBUJP_Part2}</a>${sharkMessageResource.insuranceReminder_IBUJP_Part3}</td>
                                        </tr>
                                    <#elseif IBUHK1_5?? && IBUHK1_5>
                                        <tr>
                                            <td colspan="2" style="color:#999999;padding-top:10px;">${sharkMessageResource.insuranceReminder}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="color:#999999;padding-top:10px;font-size: 12px;">${sharkMessageResource.insuranceReminder_IBUHK1_5} (
                                                <a href="http://www.ia.org.hk" target="_blank" style="color: #1899f2;" title="www.ia.org.hk">www.ia.org.hk</a>)</td>
                                        </tr>
                                    <#else >
                                        <tr>
                                            <td colspan="2" style="color:#999999;padding-top:10px;">${sharkMessageResource.insuranceReminder}</td>
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