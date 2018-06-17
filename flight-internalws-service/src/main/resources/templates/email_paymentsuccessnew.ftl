<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${getResource("paymentSuccess")}</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
${emailHeader}
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <table width="600" border="0" cellpadding="0" cellspacing="0" style="font-family:Arial,sans-serif;font-size:12px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:5px 20px 30px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr><td style="font-family:Arial,sans-serif;font-size:32px;padding-bottom:20px;padding-top:20px;text-decoration:none;">${getResource("paymentSuccess")}</td></tr>
                            <tr>
                                <td style="font-size:12px;padding-bottom:25px;font-family:Arial,sans-serif;">
                                    <strong style="font-size:14px;">${getResource("dearCustomer")}</strong>,
                                    <p style="padding-top:15px;margin:0;line-height: 1.2;">
                                        <#if orderDetail.trademark == "Trip">
                                            ${getResource("paymentSuccessDesc")?replace('${"$"}{trademark}','Trip.com')}
                                        <#elseif orderDetail.trademark == "Ctrip">
                                            ${getResource("paymentSuccessDesc")?replace('${"$"}{trademark}','Trip.com')}
                                        </#if>
                                    </p>
                                    <!--拆单情况-->
                                    <#if mergeOrderDetail.basicInfo.orderCount gt 1>
                                        <#if mergeOrderDetail.basicInfo.latestDraftTime??>
                                            <!--正常出票-->
                                            <#if mergeOrderDetail.basicInfo.latestDraftTime.protectReference == 1>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("orderSeparateIssuedReminder")?replace('${"$"}{orderCount}',mergeOrderDetail.basicInfo.orderCount)}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("orderSeparateLatestDraftTime1")?replace('${"$"}{minute}',mergeOrderDetail.basicInfo.latestDraftTime.minute)?replace('${"$"}{hour}',mergeOrderDetail.basicInfo.latestDraftTime.hour)}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("ticketSeparateIssuedNoticReminder")}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("protectTravel")}</p>
                                            <!--晚出票-->
                                            <#elseif mergeOrderDetail.basicInfo.latestDraftTime.protectReference == 2>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("protectTravel")}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("orderSeparateIssuedReminder")?replace('${"$"}{orderCount}',mergeOrderDetail.basicInfo.orderCount)}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("orderSeparateLatestDraftTime2")?replace('${"$"}{minute}',mergeOrderDetail.basicInfo.latestDraftTime.minute)?replace('${"$"}{hour}',mergeOrderDetail.basicInfo.latestDraftTime.hour)}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("ticketSeparateIssuedNoticReminder")}</p>
                                            </#if>
                                        <#else>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc2")}</p>
                                        </#if>
                                    <!--拆单情况-->
                                    <#else>
                                        <#if mergeOrderDetail.basicInfo.latestDraftTime??>
                                            <!--正常出票-->
                                            <#if mergeOrderDetail.basicInfo.latestDraftTime.protectReference == 1>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("lastestDraftTimePattern")?replace('${"$"}{minute}',mergeOrderDetail.basicInfo.latestDraftTime.minute)?replace('${"$"}{hour}',mergeOrderDetail.basicInfo.latestDraftTime.hour)}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc2")}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("protectTravel")}</p>
                                            <!--晚出票-->
                                            <#elseif mergeOrderDetail.basicInfo.latestDraftTime.protectReference == 2>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("protectTravel")}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("lastestDraftTimePattern2")?replace('${"$"}{minute}',mergeOrderDetail.basicInfo.latestDraftTime.minute)?replace('${"$"}{hour}',mergeOrderDetail.basicInfo.latestDraftTime.hour)}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc2")}</p>
                                            </#if>
                                        <#else>
                                        </#if>
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <td style="border-top:1px solid #dddddd;height:25px;"></td>
                            </tr>
                            <tr>
                                <td style="font-size:16px;font-weight:700;padding-bottom:10px;font-family:Arial,sans-serif;">
                                ${getResource("paymentInformationTitle")}
                                </td>
                            </tr>
                            <tr>
                                <td style="border:1px solid #dddddd;font-family:Arial,sans-serif;font-size:12px;">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td style="font-family:Arial,sans-serif;font-weight:700;background: #F1F7FF;padding: 15px 0 15px 15px;">${getResource("totalPayment")}</td>
                                            <td style="font-family:Arial,sans-serif;font-weight:700;background: #F1F7FF;">
                                                <span style="font-size:15px;font-weight:700;">${mergeOrderDetail.paymentInfo.paymentCurrency}</span> <strong style="font-size:14px;">${mergeOrderDetail.paymentInfo.totalPrice}</strong>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="width:40.5%;font-family:Arial,sans-serif;font-weight:700;padding-bottom:15px;padding-left: 15px;padding-top: 15px;">
                                                <#if mergeOrderDetail.basicInfo.flightWay == "OW">
                                                    ${getResource("oneWay")}
                                                <#elseif mergeOrderDetail.basicInfo.flightWay == "RT">
                                                    ${getResource("roundTrip")}
                                                <#elseif mergeOrderDetail.basicInfo.flightWay == "MT">
                                                    ${getResource("multiTrip")}
                                                </#if>
                                            </td>
                                            <td style="font-family:Arial,sans-serif;padding-bottom:15px;padding-top: 15px;">
                                                <#list mergeOrderDetail.basicInfo.routeSummaryList as routeSummary>
                                                    ${routeSummary}<br/>
                                                </#list>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-family:Arial,sans-serif;font-weight:700;padding-bottom:15px;padding-left: 15px;">${getResource("passenger")}</td>
                                            <td style="font-family:Arial,sans-serif;padding-bottom:7px;">
                                                <#list mergeOrderDetail.passengerGroupList as passengerGroup>
                                                    <#list passengerGroup.passengerOrderInfoList as passenger>
                                                    <p style="margin: 0;padding-bottom: 8px;">
                                                        <strong>${passenger.passengerName}</strong>
                                                        <#if passenger.passengerType == "ADT">
                                                        <span style="color: #999999;">(${getResource("adultTickets")});</span>
                                                        <#elseif passenger.passengerType == "CHD">
                                                        <span style="color: #999999;">(${getResource("childTickets")});</span>
                                                        <#elseif passenger.passengerType == "INF">
                                                        <span style="color: #999999;">(${getResource("infantTickets")});</span>
                                                        </#if>
                                                    </p>
                                                    </#list>
                                                </#list>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-family:Arial,sans-serif;padding-top: 15px;padding-bottom: 25px;">${getResource("appPromotionForPaymentSuccess")?replace('${"$"}{appDownloadUri}',appDownloadUri)}<td>
                            </tr>
                            <tr>
                                <td style="border-top:1px solid #dddddd;height:25px;"></td>
                            </tr>
                            <tr>
                                <td style="font-size:16px;font-weight:700;padding-bottom:15px;font-family:Arial,sans-serif;">
                                ${getResource("bookingDetail")}
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:12px;padding-bottom:15px;font-family:Arial,sans-serif;">
                                ${getResource("ticketSeparateIssuedReminder")}
                                </td>
                            </tr>
                            <#list mergeOrderDetail.passengerGroupList as passengerGroup>
                             <tr>
                                 <td style="font-size:14px;font-weight:700;padding-bottom:5px;font-family:Arial,sans-serif;">
                                     <#if passengerGroup.passengerType == "ADT">
                                         ${getResource("adultTickets")}
                                     <#elseif passengerGroup.passengerType == "CHD">
                                         ${getResource("childTickets")}
                                     <#elseif passengerGroup.passengerType == "INF">
                                         ${getResource("infantTickets")}
                                     </#if>
                                     x ${passengerGroup.passengerOrderInfoList?size}
                                 </td>
                             </tr>
                            <tr>
                                <td style="border:1px solid #dddddd;font-size:12px;padding:15px;font-family:Arial,sans-serif;">
                                    <#list passengerGroup.passengerOrderInfoList as passengerOrder>
                                        <#list passengerOrder.orderInfoList as orderInfo>
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                            <tr>
                                                <td style="font-family:Arial,sans-serif;font-weight:700;padding-bottom:15px;">${getResource("orderid")}</td>
                                                <td style="font-family:Arial,sans-serif;padding-bottom:15px;">
                                                    <a href="${orderInfo.orderDetailUrl}" target="_blank" style="color: #2681FF;">${orderInfo.orderId?c}</a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td valign="top" style="font-family:Arial,sans-serif;font-weight:700;padding-bottom:15px;">${getResource("flightInfo")}</td>
                                                <td style="font-family:Arial,sans-serif;padding-bottom:15px;">
                                                    <#list orderInfo.flightInfoList as flightRouteInfo>
                                                    <#if orderInfo.flightWay == "RT">
                                                        <#if flightRouteInfo_index == 0>
                                                    <p style="margin: 0;padding-bottom: 8px;color:#999999;">${getResource("departRoute")}</p>
                                                        <#else>
                                                    <p style="margin: 0;padding-bottom: 8px;padding-top:15px;color:#999999;">${getResource("returnRoute")}</p>
                                                        </#if>
                                                    <#elseif orderInfo.flightWay == "MT">
                                                    <p style="margin: 0;padding-bottom: 8px;padding-top:15px;color:#999999;">${getResource("flightInfo")} ${flightRouteInfo_index + 1}</p>
                                                    </#if>
                                                    <p style="margin: 0;padding-bottom: 8px;"><strong>${flightRouteInfo.flightRouteSummary}</strong></p>
                                                    <p style="margin: 0;padding-bottom: 8px;">${getResource("depart")} : ${flightRouteInfo.dDate?datetime}</p>
                                                    <p style="margin: 0;">${getResource("arrival")} : ${flightRouteInfo.aDate?datetime}</p>
                                                    </#list>
                                                </td>
                                            </tr>
                                            <#list orderInfo.paymentItemDetailList as paymentItemDetail>
                                            <tr>
                                                <td style="font-family:Arial,sans-serif;padding-bottom:8px;">
                                                    <#if paymentItemDetail.priceType == 1>
                                                    <!--机票费用-->
                                                        ${getResource("flightFare")}
                                                    <#elseif paymentItemDetail.priceType == 2>
                                                    <!--税费-->
                                                        ${getResource("taxFee")}
                                                    <#elseif paymentItemDetail.priceType == 3>
                                                    <!--燃油费-->
                                                        ${getResource("oilFee")}
                                                    <#elseif paymentItemDetail.priceType == 4>
                                                    <!--增值行李额-->
                                                        ${getResource("valueAddedBaggage")}
                                                    <#elseif paymentItemDetail.priceType == 5>
                                                    <!--保险费用-->
                                                        ${getResource("insuranceFee")}
                                                    <#elseif paymentItemDetail.priceType == 6>
                                                    <!--酒店优惠券-->
                                                        ${getResource("hotelCouponFee")}
                                                    <#elseif paymentItemDetail.priceType == 7>
                                                    <!--优惠券-->
                                                        ${getResource("couponFee")}
                                                    </#if>
                                                </td>
                                                <td style="font-family:Arial,sans-serif;padding-bottom:8px;">
                                                    <span style="font-size:10px;">${orderInfo.currency}</span>
                                                    <#if paymentItemDetail.priceType == 7>
                                                    <!--优惠券减金额-->
                                                    -
                                                    </#if>
                                                    ${paymentItemDetail.amount}
                                                    <#if (paymentItemDetail.copies?? && paymentItemDetail.copies > 0)>
                                                        * ${paymentItemDetail.copies}
                                                    </#if>
                                                </td>
                                            </tr>
                                            </#list>
                                            <tr>
                                                <td style="font-family:Arial,sans-serif;font-weight:700;width: 40%;">${getResource("totalPrice")}</td>
                                                <td style="font-family:Arial,sans-serif;">
                                                    <span style="font-size:10px;font-weight:700;">${orderInfo.currency}</span> <strong style="font-size:14px;">${orderInfo.totalPayment}</strong>
                                                </td>
                                            </tr>
                                        </table>
                                        <#if orderInfo_index != (passengerOrder.orderInfoList?size - 1)>
                                        <p style="margin-top:0;border-bottom:1px dashed #dddddd;height: 15px;padding: 0;"></p>
                                        </#if>
                                        </#list>
                                    </#list>
                                </td>
                            </tr>
                            </#list>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
${emailFooter}
</body>
</html>