<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign paymentInfo=orderDetail.paymentInfo>
<head>
    <style type="text/css">
        td[class="blue"]:hover{cursor:pointer;background-color:#3498db;}
        td[class="orange"]:hover{cursor:pointer;background-color:#ffc600;}

        * {
            font-family: ${fontFamily},Arial;
        }

    </style>
    <meta charset="utf-8"></meta>
    <title>${getResource("ereceipt")}</title>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<center>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="padding:0 20px;font-family:'${fontFamily}',sans-serif;font-size:12px;color:#212121;text-align:left;">
        <tr>
            <td style="font-family:'${fontFamily}',sans-serif;">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:'${fontFamily}',sans-serif;font-size:12px;color:#212121;padding-top:20px;">
                    <tr>
                        <td rowspan="3" valign="bottom" width="170">
                            <a href="${firstPageUrl}" style="text-decoration:none;"><img src="${resourcePath.trademarkLogoPath!}" width="166" height="40" border="0" alt=""></img></a>
                        </td>
                        <td rowspan="3" valign="bottom">
                            <a href="${firstPageUrl}" style="text-decoration:none;"><img src="${resourcePath.iataLogoPath!}" width="95" height="60" border="0" alt=""></img></a>
                        </td>
                        <td align="right" style="font-family:${fontFamily},sans-serif;line-height:1.5;color:#999999;">${getResource("bookingNo")} <a href="${orderDetail.orderDetailUrl}" target="_blank" style="color:#2681FF;text-decoration:underline;">${orderDetail.orderId?string["0"]}</a><br />${getResource("bookingDate")}: ${orderDetail.orderDate?datetime}</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr><td style="height:20px;"></td></tr>
        <#if orderDetail.pnr??>
            <tr>
                <td style="font-family:${fontFamily},sans-serif;font-size:20px;font-weight:bold;">
                ${getResource("ereceipt")} <br/>
                    <span style="font-size:14px;">(${getResource("airlineBookingReference")}:${orderDetail.pnr})</span>
                </td>
            </tr>
        </#if>
        <tr><td style="height:20px;"></td></tr>
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td width="69%">
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <#if customerName?? && customerName?length gt 0>
                                            <tr>
                                                <td width="50.8%" style="font-family:${fontFamily},sans-serif;font-size:12px;font-weight:700;">${getResource("customerName")}</td>
                                                <td valign="top" style="font-family:${fontFamily},sans-serif;line-height:1.2;">${customerName}</td>
                                            </tr>
                                            <tr>
                                                <td height="10" colspan="2"></td>
                                            </tr>
                                        </#if>
                                            <tr>
                                                <td style="font-family:${fontFamily},sans-serif;font-size:12px;font-weight:700;">${getResource("totalPrice")}</td>
                                                <td style="font-family:${fontFamily},sans-serif;"><span style="color:#666;font-size:12px;"><strong style="color:#2681FF;line-height:1;font-size:14px;">${paymentInfo.currencyType} ${paymentInfo.payTotalPrice}</strong></span>
                                                    <em style="color:#999999;font-style:normal;">(${getResource("includeTaxesAndFees")})</em></td>
                                            </tr>
                                            <tr>
                                                <td height="10" colspan="2"></td>
                                            </tr>
                                            <tr>
                                                <td style="font-family:${fontFamily},sans-serif;font-size:12px;font-weight:700;">${getResource("payPurpose")}</td>
                                                <td style="font-family:${fontFamily},sans-serif;line-height: 1.2;">${getResource("forAirTicket")}</td>
                                            </tr>
                                            <tr>
                                                <td height="10" colspan="2"></td>
                                            </tr>
                                            <tr>
                                                <td style="font-family:${fontFamily},sans-serif;font-size:12px;font-weight:700;">${getResource("paymentMethod")}</td>
                                                <td style="font-family:${fontFamily},sans-serif;line-height: 1.2;">${paymentInfo.payMethodDesc}</td>
                                            </tr>
                                            <tr>
                                                <td height="10" colspan="2"></td>
                                            </tr>
                                        <#if issuingAirlines?length gt 0>
                                            <tr>
                                                <td style="font-family:${fontFamily},sans-serif;font-size:12px;font-weight:700;">${getResource("carrierAirline")}</td>
                                                <td style="font-family:${fontFamily},sans-serif;line-height: 1.2;">${issuingAirlines}</td>
                                            </tr>
                                        </#if>
                                            <tr>
                                                <td height="10" colspan="2"></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="font-family:${fontFamily},sans-serif;font-size:12px;color:#999999;">${getResource("payDate")}: ${orderDetail.userPayDate?datetime}</td>
                                            </tr>
                                            <tr>
                                                <td height="10" colspan="2"></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="font-family:${fontFamily},sans-serif;color:#999999;font-size:12px;">${getResource("issuedData")}: ${orderDetail.orderFinishDate?datetime}</td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td align="left" valign="bottom" style="padding-left:20px">
                                        <img src="${resourcePath.stampLogoPath!}" alt="" width="160" height="160"></img>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="20" colspan="2" style="border-bottom:1px solid #dddddd;"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td height="20"></td>
                    </tr>
                    <tr>
                        <td style="font-size: 16px;font-weight:700;">${getResource("passenger")}</td>
                    </tr>
                    <tr>
                        <td height="10"></td>
                    </tr>
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <td width="35%" style="color: #999999;">${getResource("travelerName")}</td>
                                    <td style="color: #999999;">${getResource("eTicketNo")}</td>
                                </tr>
                            <#list orderDetail.passengerList as passengerInfo>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${passengerInfo.name}</td>
                                    <td>
                                        <#if (passengerInfo.eTickets??&&passengerInfo.eTickets?size > 0)>
                                              ${passengerInfo.eTickets?join(",")}
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td height="20" colspan="2" style="border-bottom:1px solid #dddddd;"></td>
                    </tr>
                    <tr>
                        <td height="20"></td>
                    </tr>
                    <tr>
                        <td style="font-size: 16px;font-weight:700;">${getResource("flight")}</td>
                    </tr>
                    <tr>
                        <td height="10"></td>
                    </tr>
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <td width="35%" style="color: #999999;">${getResource("date")}</td>
                                    <td width="35%" style="color: #999999;">${getResource("route")}</td>
                                    <td style="color: #999999;">${getResource("flightClass")}</td>
                                </tr>
                            <#list orderDetail.flightInfoList as flightRouteInfo>
                                <#if flightRouteInfo.columnList?? && flightRouteInfo.columnList?size gte 1>
                                    <#list flightRouteInfo.columnList as flightColumnInfo>
                                        <tr>
                                            <td height="10" colspan="3"></td>
                                        </tr>
                                        <tr>
                                            <td>${flightColumnInfo.dDate?date}</td>
                                            <td>${flightColumnInfo.dCity.code} - ${flightColumnInfo.aCity.code}</td>
                                            <td>${flightColumnInfo.className}</td>
                                        </tr>
                                    </#list>
                                </#if>
                            </#list>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td height="20" colspan="2" style="border-bottom:1px solid #dddddd;"></td>
                    </tr>
                    <tr>
                        <td height="20"></td>
                    </tr>
                    <tr>
                        <td style="font-size: 16px;font-weight:700;">${getResource("paymentSummary")}</td>
                    </tr>
                    <tr>
                        <td height="10"></td>
                    </tr>
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <td width="35%" style="color: #999999;">${getResource("name")}</td>
                                    <td style="color: #999999;">${getResource("price")}</td>
                                </tr>
                            <#if (paymentInfo.payFlightPrice > 0) >
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${getResource("flightFare")}</td>
                                    <td>${paymentInfo.currencyType} ${paymentInfo.payFlightPrice * orderDetail.passengerList?size}</td>
                                </tr>
                            </#if>
                            <#if (paymentInfo.payTax > 0)>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${getResource("taxFee")}</td>
                                    <td>${paymentInfo.currencyType} ${paymentInfo.payTax * orderDetail.passengerList?size}</td>
                                </tr>
                            </#if>
                            <#if (paymentInfo.payTotalAviationAccidentInsuranceFee > 0)>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${getResource("aviationAccidentInsurance")}</td>
                                    <td>${paymentInfo.currencyType} ${paymentInfo.payTotalAviationAccidentInsuranceFee}</td>
                                </tr>
                            </#if>
                            <#if (paymentInfo.loungeFee > 0)>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${getResource("lounge")}</td>
                                    <td>${paymentInfo.currencyType} ${paymentInfo.loungeFee}</td>
                                </tr>
                            </#if>
                            <#if (paymentInfo.payTotalTravelInsuranceFee > 0)>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${getResource("travelInsurance")}</td>
                                    <td>${paymentInfo.currencyType} ${paymentInfo.payTotalTravelInsuranceFee}</td>
                                </tr>
                            </#if>
                            <#if (paymentInfo.hotelCouponFee > 0)>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${getResource("hotelCouponFee")}</td>
                                    <td>${paymentInfo.currencyType} ${paymentInfo.hotelCouponFee}</td>
                                </tr>
                            </#if>
                            <#if (paymentInfo.payValueAddedBaggageFee > 0)>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td>${getResource("valueAddedBaggageFee")}</td>
                                    <td>${paymentInfo.currencyType} ${paymentInfo.payValueAddedBaggageFee}</td>
                                </tr>
                            </#if>
                                <tr>
                                    <td height="10" colspan="2"></td>
                                </tr>
                                <tr>
                                    <td style="font-weight: 700;">${getResource("totalAmount")}</td>
                                    <td style="font-weight: 700;color:#2681FF;">${paymentInfo.currencyType} ${paymentInfo.payTotalPrice}</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td height="10"></td>
                    </tr>
                    <tr>
                        <td style="font-size: 12px;color:#999;">${getResource("priceAccuracyReminder")}</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</center>
</body>
</html>