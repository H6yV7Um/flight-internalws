<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sharkMessageResource.newFlightSuccess}</title>
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

                            <tr><td style="font-family:Arial,sans-serif;font-size:30px;padding-bottom:15px;padding-top:15px;color:#55af32;">${sharkMessageResource.newFlightSuccess}</td></tr>
                            <tr>
                                <td style="font-family:Arial,sans-serif;font-size:14px;padding-bottom:20px;">
                                ${sharkMessageResource.dearCustomer},
                                    <br>${sharkMessageResource.newFlightSuccessDesc}
                                </td>
                            </tr>
                        <#assign payCurrencyType = orderDetail.paymentInfo.currencyType>
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
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td colspan="2" style="font-family:Arial,sans-serif;font-size:14px;padding-top:10px;padding-bottom:10px;">${sharkMessageResource.ticketPolicyRemainDesc}</td>
                                        </tr>
                                    </table>
                                    <#assign newFlightPaymentInfo = newFlightInfo.rebookingPayDetail rescheduledPassengerCount = newFlightInfo.rebookingPassengerItemList?size >
                                    <#if newFlightPaymentInfo??>
                                        <#assign paymentCurrencyType = newFlightPaymentInfo.currency>
                                        <table bgcolor="#f8f8f8" width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                        <#if orderDetail.flightClass == "Domestic">
                                            <#if newFlightPaymentInfo.priceDifferential gt 0>
                                                <tr>
                                                    <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.priceDiff}</td>
                                                    <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.priceDifferential?string("0")}</td>
                                                    <#else >
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.priceDifferential}</td>
                                                    </#if>
                                                </tr>
                                            </#if>
                                            <#if newFlightPaymentInfo.taxDifferential gt 0>
                                                <tr>
                                                    <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.taxPriceDiff}</td>
                                                    <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.taxDifferential?string("0")}</td>
                                                    <#else >
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.taxDifferential}</td>
                                                    </#if>
                                                </tr>
                                            </#if>
                                            <#if newFlightPaymentInfo.dateChangeFee gt 0>
                                                <tr>
                                                    <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.dateChangeFee}</td>
                                                    <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.dateChangeFee?string("0")}</td>
                                                    <#else >
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.dateChangeFee}</td>
                                                    </#if>
                                                </tr>
                                            </#if>
                                        <#else >
                                            <#if newFlightPaymentInfo.priceDifferential gt 0>
                                                <tr>
                                                    <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.priceDiff}</td>
                                                    <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.priceDifferential?string("0")} * ${rescheduledPassengerCount}</td>
                                                    <#else >
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.priceDifferential} * ${rescheduledPassengerCount}</td>
                                                    </#if>
                                                </tr>
                                            </#if>
                                            <#if newFlightPaymentInfo.taxDifferential gt 0>
                                                <tr>
                                                    <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.taxPriceDiff}</td>
                                                    <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.taxDifferential?string("0")} * ${rescheduledPassengerCount}</td>
                                                    <#else >
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.taxDifferential} * ${rescheduledPassengerCount}</td>
                                                    </#if>
                                                </tr>
                                            </#if>
                                            <#if newFlightPaymentInfo.dateChangeFee gt 0>
                                                <tr>
                                                    <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.dateChangeFee}</td>
                                                    <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.dateChangeFee?string("0")} * ${rescheduledPassengerCount}</td>
                                                    <#else >
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${paymentCurrencyType} ${newFlightPaymentInfo.dateChangeFee} * ${rescheduledPassengerCount}</td>
                                                    </#if>
                                                </tr>
                                            </#if>
                                        </#if>
                                            <#if newFlightPaymentInfo.totalRebookFee gt 0>
                                                <tr>
                                                    <#if (payCurrencyType=="JPY" || payCurrencyType =="KRW")>
                                                        <td colspan="2" align="right" style="padding:10px 10px 5px;font-weight:700;">
                                                        ${sharkMessageResource.totalRebookFee}: ${paymentCurrencyType} ${newFlightPaymentInfo.totalRebookFee?string("0")}
                                                        </td>
                                                    <#else >
                                                        <td colspan="2" align="right" style="padding:10px 10px 5px;font-weight:700;">
                                                        ${sharkMessageResource.totalRebookFee}: ${paymentCurrencyType} ${newFlightPaymentInfo.totalRebookFee}
                                                        </td>
                                                    </#if>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" align="right" style="color:#959595;font-size:12px;padding-bottom:10px;padding-right:10px;">${orderDetail.payMethodStr}</td>
                                                </tr>
                                            </#if>
                                        </table>
                                    </#if>
                                    <!--判断是否需要展示X产品模块-->
                                    <#if ((orderDetail.insurance?? && (orderDetail.insurance.insuranceCount gt 0 || orderDetail.insurance.travelInsuranceCount gt 0))
                                    || (orderDetail.xProduct?? && (orderDetail.xProduct.airportLoungeList??  && orderDetail.xProduct.airportLoungeList?size gt 0)
                                    || (orderDetail.xProduct.xCouponList?? && orderDetail.xProduct.xCouponList?size gt 0)
                                    || (orderDetail.xProduct.passengerValueAddedBaggageList?? && orderDetail.xProduct.passengerValueAddedBaggageList?size gt 0)))>
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:14px;color:#212121;">
                                            <tr>
                                                <td colspan="2" style="font-size:16px;font-weight:700;padding-top:10px;padding-bottom:10px;border-bottom:1px solid #ededed;">${sharkMessageResource.valueAddedProduct}</td>
                                            </tr>
                                            <#if orderDetail.insurance??>
                                                <#if orderDetail.insurance.insuranceCount gt 0>
                                                    <tr>
                                                        <td style="padding:10px;padding-right:0;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.aviationAccidentInsurance} * ${orderDetail.insurance.insuranceCount}</td>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${sharkMessageResource.newFlightInsuranceAutoRemained}</td>
                                                    </tr>
                                                </#if>
                                                <#if orderDetail.insurance.travelInsuranceCount gt 0>
                                                    <tr>
                                                        <td style="padding:10px;padding-right:0;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.travelInsurance} * ${orderDetail.insurance.travelInsuranceCount}</td>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${sharkMessageResource.newFlightInsuranceAutoRemained}</td>
                                                    </tr>
                                                </#if>
                                            </#if>
                                            <#if orderDetail.xProduct??>
                                                <#if (orderDetail.xProduct.airportLoungeList?? && orderDetail.xProduct.airportLoungeList?size gt 0)>
                                                    <tr>
                                                        <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.loungeFee} * ${orderDetail.xProduct.airportLoungeList?size}</td>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${sharkMessageResource.newFlightLoungeAutoRemained}</td>
                                                    </tr>
                                                </#if>
                                                <#if (orderDetail.xProduct.xCouponList?? && orderDetail.xProduct.xCouponList?size gt 0)>
                                                    <tr>
                                                        <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.xHotelCouponFee} * ${orderDetail.xProduct.xCouponList?size}</td>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${sharkMessageResource.newFlightHotelCouponAutoRemained}</td>
                                                    </tr>
                                                </#if>
                                                <#if (orderDetail.xProduct.passengerValueAddedBaggageList?? && orderDetail.xProduct.passengerValueAddedBaggageList?size gt 0)>
                                                    <tr>
                                                        <td style="padding:10px;border-bottom:1px solid #ededed;font-weight:700;">${sharkMessageResource.valueAddedBaggageFee}</td>
                                                        <td align="right" style="padding:10px;border-bottom:1px solid #ededed;">${sharkMessageResource.newFlightValueAddedBaggageAutoRemained}</td>
                                                    </tr>
                                                </#if>
                                            </#if>
                                        </table>
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                            <!--关联订单-->
                        <#if (orderDetail.relatedOrderInfoList?? && orderDetail.relatedOrderInfoList?size gt 0)>
                            <tr>
                                <td style="padding-top:10px;padding-bottom:20px;">
                                ${sharkMessageResource.relationOrderDescPart_1} ${orderDetail.relatedOrderInfoList?size + 1} ${sharkMessageResource.relationOrderDescPart_2} (
                                    <#list  orderDetail.relatedOrderInfoList as order>
                                        <a href="${order.orderDetailUrl}" target="_blank" style="color:#1171b7;text-decoration:underline;">${order.orderId?string["0"]}</a>
                                    </#list>
                                    )${sharkMessageResource.relationOrderDescPart_3}
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