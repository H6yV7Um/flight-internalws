<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sharkMessageResource.newFlightCanceled}</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<style type="text/css">
    td[class="blue"]:hover {
        cursor: pointer;
        background-color: #3498db;
    }

    td[class="orange"]:hover {
        cursor: pointer;
        background-color: #ffc600;
    }
</style>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
        ${emailHeader}
            <table width="600" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:0 20px;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">

                            <tr><td style="font-family:Arial,sans-serif;font-size:30px;padding-bottom:15px;padding-top:15px;color:#55af32;">${sharkMessageResource.newFlightCanceled}</td></tr>
                            <tr>
                                <td style="font-size:14px;padding-bottom:20px;">
                                ${sharkMessageResource.dearCustomer},
                                    <br>${sharkMessageResource.rescheduledCanceledDesc}
                                </td>
                            </tr>
                        <#assign payCurrencyType=orderDetail.paymentInfo.currencyType>
                        <#if newFlightInfo??>
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                        <tr>
                                            <td colspan="3" style="font-size:16px;font-weight:700;padding-bottom:10px;border-bottom:1px solid #ededed;">${sharkMessageResource.rescheduledTitle}</td>
                                        </tr>
                                        <tr>
                                            <td width="35%" valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.orderId}</td>
                                            <td style="border-bottom:1px solid #ededed;padding:10px;"><a href="${orderDetail.orderDetailUrl}" target="_blank" style="color:#1171b7;text-decoration:underline;">${orderDetail.orderId?string["0"]}</a></td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.rescheduledApplicationNumber}</td>
                                            <td colspan="2" style="border-bottom:1px solid #ededed;padding:10px;">${newFlightInfo.rebookingApplicationId}</td>
                                        </tr>
                                        <#assign newRebookingFlightList=newFlightInfo.rebookingNewFlightItemList>
                                        <#if (newRebookingFlightList?? && newRebookingFlightList?size gt 0)>
                                            <#list  newRebookingFlightList as rebookingNewFlightInfo>
                                                <tr>
                                                    <#if  newFlightInfo.rebookingNewFlightItemList?size gt 1>
                                                        <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.newItinerary}${rebookingNewFlightInfo_index + 1}</td>
                                                    <#else >
                                                        <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.newItinerary}</td>
                                                    </#if>
                                                    <td colspan="2" style="border-bottom:1px solid #ededed;padding:10px 10px 2px;">
                                                        <p style="margin:0;padding:0 0 8px;">${rebookingNewFlightInfo.dCity.name!} - ${rebookingNewFlightInfo.aCity.name!}</p>
                                                        <#if (rebookingNewFlightInfo.columnList ?? && rebookingNewFlightInfo.columnList?size gte 1)>
                                                            <#list rebookingNewFlightInfo.columnList as  columnInfo>
                                                                <p style="margin:0;padding:0 0 8px;">${columnInfo.flightNo}
                                                                    (${(columnInfo.className?? && columnInfo.className?length gt 1)?string(columnInfo.className,columnInfo.class)})</p>
                                                            </#list>
                                                            <#assign firstColumn=rebookingNewFlightInfo.columnList[0] lastColumn=rebookingNewFlightInfo.columnList[rebookingNewFlightInfo.columnList?size-1]>
                                                            <#assign departDesc=((firstColumn.dPort??)?string(firstColumn.dPort.name!,'') + ' ' + (firstColumn.dTerminal??)?string(firstColumn.dTerminal.name!,''))>
                                                            <#assign arrivalDesc=((lastColumn.aPort??)?string(lastColumn.aPort.name!,'') + ' ' + (lastColumn.aTerminal??)?string(lastColumn.aTerminal.name!,''))>
                                                        </#if>
                                                        <p style="margin:0;padding:0 0 8px;">${sharkMessageResource.depart}:<span></span>${rebookingNewFlightInfo.dDate?datetime},${departDesc!}</p>
                                                        <p style="margin:0;padding:0 0 8px;">${sharkMessageResource.arrival}:<span></span>${rebookingNewFlightInfo.aDate?datetime}, ${arrivalDesc!}</p>
                                                    </td>
                                                </tr>
                                            </#list>
                                        </#if>
                                        <tr>
                                            <td valign="top" style="font-weight:700;border-bottom:1px solid #ededed;padding:10px;">${sharkMessageResource.scheduledPassenger}</td>
                                            <td colspan="2" style="border-bottom:1px solid #ededed;padding:10px 10px 0;">
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;">
                                                    <#if (newFlightInfo.rebookingPassengerItemList?? && newFlightInfo.rebookingPassengerItemList?size >= 1)>
                                                        <#list  newFlightInfo.rebookingPassengerItemList as passenger>
                                                            <tr>
                                                                <td style="padding-bottom:10px;">${passenger.name}</td>
                                                            </tr>
                                                        </#list>
                                                    </#if>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <#assign rescheduledPaymentInfo = newFlightInfo.rebookingPayDetail rescheduledPassengerCount = newFlightInfo.rebookingPassengerItemList?size>
                                    <#if rescheduledPaymentInfo?? && newFlightInfo.unconfirmedStatus?string("true","false") = "false">
                                        <table bgcolor="#f8f8f8" width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                            <tr>
                                                <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                    <td align="right" style="padding:10px;font-weight:700;border-bottom: 1px solid #ededed;">
                                                    ${sharkMessageResource.totalRebookFee}: ${rescheduledPaymentInfo.currency} ${rescheduledPaymentInfo.totalRebookFee?string("0")}
                                                    </td>
                                                <#else >
                                                    <td align="right" style="padding:10px;font-weight:700;border-bottom: 1px solid #ededed;">
                                                    ${sharkMessageResource.totalRebookFee}: ${rescheduledPaymentInfo.currency} ${rescheduledPaymentInfo.totalRebookFee}
                                                    </td>
                                                </#if>
                                            </tr>
                                            <tr>
                                                <td style="border-bottom: 1px solid #ededed;padding:10px;">${sharkMessageResource.rescheduleRefundDesc}</td>
                                            </tr>
                                        </table>
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                        </table>
                    </td>
                </tr>
            </table>
        ${emailFooter}
        </td>
    </tr>
</table>
</body>
</html>