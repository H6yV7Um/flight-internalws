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
                                    <strong style="font-size:14px;">${getResource("dearCustomer")}</strong>,<br/><br/>
                                <#if orderDetail.trademark == "Trip">
                                    <p style="padding:0;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc")?replace('${"$"}{trademark}','Trip.com')}</p>
                                <#elseif orderDetail.trademark == "Ctrip">
                                    <p style="padding:0;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc")?replace('${"$"}{trademark}','Ctrip')}</p>
                                </#if>
                                <#if orderDetail.lastestDraftTime??>
                                    <#if orderDetail.lastestDraftTime.protectReference == 1>
                                        <#if orderDetail.trademark == "Trip">
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("lastestDraftTimePattern")?replace('${"$"}{minute}',orderDetail.lastestDraftTime.minute)?replace('${"$"}{hour}',orderDetail.lastestDraftTime.hour)}${getResource("paymentSuccessDesc2")}${getResource("protectTravel")?replace('${"$"}{trademark}','Trip.com')}</p>
                                        <#elseif orderDetail.trademark == "Ctrip">
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("lastestDraftTimePattern")?replace('${"$"}{minute}',orderDetail.lastestDraftTime.minute)?replace('${"$"}{hour}',orderDetail.lastestDraftTime.hour)}${getResource("paymentSuccessDesc2")}${getResource("protectTravel")?replace('${"$"}{trademark}','Ctrip')}</p>
                                        </#if>
                                    <#elseif orderDetail.lastestDraftTime.protectReference == 2>
                                        <#if orderDetail.trademark == "Trip">
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("protectTravel")?replace('${"$"}{trademark}','Trip.com')}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc2")}</p>
                                        <#elseif orderDetail.trademark == "Ctrip">
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("protectTravel")?replace('${"$"}{trademark}','Ctrip')}</p>
                                            <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc2")}</p>
                                        </#if>
                                    </#if>
                                <#else>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("protectTravel")}</p>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("paymentSuccessDesc2")}</p>
                                </#if>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("appPromotionForPaymentSuccess")?replace('${"$"}{appDownloadUri}',appDownloadUri)}</p>
                                </td>
                            </tr>
                            <tr>
                                <td style="border-top:1px solid #dddddd;height:25px;"></td>
                            </tr>
                        <#assign paymentInfo=orderDetail.paymentInfo payCurrencyType=orderDetail.paymentInfo.currencyType passengerCount=orderDetail.passengerList?size>
                            <tr>
                                <td style="font-size:16px;font-weight:700;padding-bottom:10px;font-family:Arial,sans-serif;">
                                ${getResource("paymentInformationTitle")}
                                </td>
                            </tr>
                            <tr>
                                <td style="border:1px solid #dddddd;font-family:Arial,sans-serif;font-size:12px;">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td colspan="2" style="background: #F1F7FF;padding: 15px;font-size:12px;">
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;font-weight:700;padding-bottom:8px;width: 40%;font-size:12px;">${getResource("totalPayment")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                        <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                            <span style="font-size:10px;font-weight:700;">${payCurrencyType}</span>
                                                            <strong style="font-size:14px;">${paymentInfo.payTotalPrice?string("0")}</strong>
                                                        <#else>
                                                            <span style="font-size:10px;font-weight:700;">${payCurrencyType}</span>
                                                            <strong style="font-size:14px;">${paymentInfo.payTotalPrice}</strong>
                                                        </#if>
                                                        </td>
                                                    </tr>
                                                <#--机票费用-->
                                                <#if paymentInfo.payFlightPrice gt 0>
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("flightFare")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payFlightPrice?string("0")} * ${passengerCount}
                                                            <#else>
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payFlightPrice} * ${passengerCount}
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                                <#--税费-->
                                                <#if paymentInfo.payTax gt 0 || paymentInfo.payOil gt 0>
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("taxFee")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${(paymentInfo.payTax + paymentInfo.payOil)?string("0")} × ${passengerCount}
                                                            <#else>
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${(paymentInfo.payTax + paymentInfo.payOil)} × ${passengerCount}
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                                <#--配送费-->
                                                <#if paymentInfo.payDeliverFee gt 0>
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("deliverFee")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payDeliverFee?string("0")} × ${passengerCount}
                                                            <#else>
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payDeliverFee} × ${passengerCount}
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                                <#--航意险及旅行险-->
                                                <#if orderDetail.insurance??>
                                                <#--航意险-->
                                                    <#if orderDetail.insurance.insuranceCount gt 0>
                                                        <tr>
                                                            <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("aviationAccidentInsurance")}</td>
                                                            <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                                <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                    <span style="font-size:10px;">${payCurrencyType}</span> ${orderDetail.insurance.insUnitPrice?string("0")} × ${orderDetail.insurance.insuranceCount}
                                                                <#else>
                                                                    <span style="font-size:10px;">${payCurrencyType}</span> ${orderDetail.insurance.insUnitPrice} × ${orderDetail.insurance.insuranceCount}
                                                                </#if>
                                                            </td>
                                                        </tr>
                                                    </#if>
                                                </#if>
                                                <#--旅行险-->
                                                <#if orderDetail.insurance.travelInsuranceCount gt 0>
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("travelInsurance")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${orderDetail.insurance.travelInsUnitPrice?string("0")} × ${orderDetail.insurance.travelInsuranceCount}
                                                            <#else>
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${orderDetail.insurance.travelInsUnitPrice} × ${orderDetail.insurance.travelInsuranceCount}
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                                <#--增值行李额-->
                                                <#if paymentInfo.payValueAddedBaggageFee gt 0>
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("valueAddedBaggage")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payValueAddedBaggageFee?string("0")}
                                                            <#else>
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payValueAddedBaggageFee}
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                                <#--休息室，X产品酒店优惠券-->
                                                <#if orderDetail.xProduct??>
                                                    <#if orderDetail.xProduct.airportLoungeList?? && orderDetail.xProduct.airportLoungeList?size gt 0>
                                                        <#assign loungeCnt=orderDetail.xProduct.airportLoungeList?size loungeUnitPrice=orderDetail.xProduct.airportLoungeList[0].costPrice>
                                                        <tr>
                                                            <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("lounge")}</td>
                                                            <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                                <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                    <span style="font-size:10px;">${payCurrencyType}</span> ${loungeUnitPrice?string("0")}
                                                                <#else>
                                                                    <span style="font-size:10px;">${payCurrencyType}</span> ${loungeUnitPrice}
                                                                </#if>
                                                            </td>
                                                        </tr>
                                                    </#if>
                                                    <#if orderDetail.xProduct.xCouponList?? && orderDetail.xProduct.xCouponList?size gt 0>
                                                        <#assign couponCnt = orderDetail.xProduct.xCouponList?size couponUnitPrice=orderDetail.xProduct.xCouponList[0].salePrice>
                                                        <tr>
                                                            <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("couponFee")}</td>
                                                            <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                                <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                    <span style="font-size:10px;">${payCurrencyType}</span> ${couponUnitPrice?string("0")}
                                                                <#else>
                                                                    <span style="font-size:10px;">${payCurrencyType}</span> ${couponUnitPrice}
                                                                </#if>
                                                            </td>
                                                        </tr>
                                                    </#if>
                                                </#if>
                                                <#--外卡手续费-->
                                                <#if paymentInfo.payCCardFee gt 0>
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("serviceFee")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payCCardFee?string("0")}
                                                            <#else>
                                                                <span style="font-size:10px;">${payCurrencyType}</span> ${paymentInfo.payCCardFee}
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                                <#--多币种优惠券:减金额-->
                                                <#if paymentInfo.payCouponAmount gt 0>
                                                    <tr>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">${getResource("couponFee")}</td>
                                                        <td style="font-family:Arial,sans-serif;padding-bottom:8px;font-size:12px;">
                                                            <#if payCurrencyType == "JPY" || payCurrencyType == "KRW">
                                                                <span style="font-size:10px;">- ${payCurrencyType}</span> ${paymentInfo.payCouponAmount?string("0")}
                                                            <#else>
                                                                <span style="font-size:10px;">- ${payCurrencyType}</span> ${paymentInfo.payCouponAmount}
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="width:40.5%;font-family:Arial,sans-serif;font-weight:700;padding-bottom:15px;padding-left: 15px;padding-top: 15px;font-size:12px;">${getResource("flightInfo")}</td>
                                            <td style="font-family:Arial,sans-serif;padding-bottom:15px;padding-top: 15px;font-size:12px;">
                                            <#list orderDetail.flightInfoList as flightInfo>
                                                <#if orderDetail.flightWay == "RT">
                                                    <#if flightInfo_index == 0>
                                                        <p style="margin: 0;padding-bottom: 8px;color:#999999;">${getResource("departRoute")}</p>
                                                    <#else>
                                                    <br/>
                                                        <p style="margin: 0;padding-bottom: 8px;padding:0;color:#999999;">${getResource("returnRoute")}</p>
                                                    </#if>
                                                <#elseif orderDetail.flightWay == "OW">
                                                    <p style="margin: 0;padding-bottom: 8px;color:#999999;">${getResource("departRoute")}</p>
                                                <#else>
                                                    <#if flightInfo_index == 0>
                                                        <p style="margin: 0;padding-bottom: 8px;color:#999999;">${getResource("flightRoute")} ${flightInfo_index + 1}</p>
                                                    <#else>
                                                        <br/>
                                                        <p style="margin: 0;padding-bottom: 8px;padding:0;color:#999999;">${getResource("flightRoute")} ${flightInfo_index + 1}</p>
                                                    </#if>
                                                </#if>
                                                <p style="margin: 0;padding-bottom: 8px;">
                                                    <strong>${flightInfo.dCity.name} - ${flightInfo.aCity.name}</strong>
                                                </p>
                                                <p style="margin: 0;padding-bottom: 8px;">${getResource("depart")}: ${flightInfo.dDate?datetime}</p>
                                                <p style="margin: 0;">${getResource("arrival")}: ${flightInfo.aDate?datetime}</p>
                                            </#list>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" style="font-family:Arial,sans-serif;font-weight:700;padding-bottom:15px;padding-left: 15px;font-size:12px;">${getResource("passenger")}</td>
                                            <td style="font-family:Arial,sans-serif;padding-bottom:15px;font-size:12px;">
                                                <p style="margin: 0;padding-bottom: 8px;">
                                                <#list orderDetail.passengerList as passenger>
                                                    <strong>${passenger.name}</strong>
                                                    <#if passenger.passengerType?string == "ADT">
                                                        <span style="color: #999999;">(${getResource("adultTickets")})</span>
                                                    <#elseif passenger.passengerType?string == "CHD">
                                                        <span style="color: #999999;">(${getResource("childTickets")})</span>
                                                    <#elseif passenger.passengerType?string == "INF">
                                                        <span style="color: #999999;">(${getResource("infantTickets")})</span>
                                                    </#if>
                                                </#list>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-family:Arial,sans-serif;font-weight:700;padding-bottom:15px;padding-left: 15px;font-size:12px;">${getResource("orderid")}</td>
                                            <td style="font-family:Arial,sans-serif;padding-bottom:15px;font-size:12px;">
                                                <a href="${orderDetail.orderDetailUrl}" target="_blank" style="color: #2681FF;">${orderDetail.orderId?c}</a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        <#if orderDetail.relatedOrderInfoList?? && orderDetail.relatedOrderInfoList?size gt 0>
                            <tr>
                                <#assign relatedOrderCnt=orderDetail.relatedOrderInfoList?size>
                                <td style="font-family:Arial,sans-serif;padding-top: 15px;font-size:12px;">${getResource("relatedOrderDesc")}(
                                    <#list orderDetail.relatedOrderInfoList as relatedOrderInfo>
                                        <#if (relatedOrderInfo_index < relatedOrderCnt - 1)>
                                            <a href="${relatedOrderInfo.orderDetailUrl}" target="_blank" style="color: #2681FF;">${relatedOrderInfo.orderId?string["0"]}</a>,
                                        <#else>
                                            <a href="${relatedOrderInfo.orderDetailUrl}" target="_blank" style="color: #2681FF;">${relatedOrderInfo.orderId?string["0"]}</a>
                                        </#if>
                                    </#list>
                                    ).
                                </td>
                            </tr>
                        </#if>
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