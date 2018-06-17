<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"></meta>
    <title>${getResource("itinerarypdf")}</title>
    <style type="text/css">
        td[class="blue"]:hover{cursor:pointer;background-color:#34910db;}
        td[class="orange"]:hover{cursor:pointer;background-color:#ffc600;}

        * {
            font-family: ${fontFamily},Arial;
        }

    </style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<style type="text/css">

</style>
<center>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="padding:0 20px;font-family:'${fontFamily}',sans-serif;font-size:12px;color:#212121;text-align:left;">
        <tr>
            <td style="font-family:'${fontFamily}',sans-serif;">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family:'${fontFamily}',sans-serif;font-size:12px;color:#212121;padding-top:20px;">
                    <tr>
                        <td rowspan="3" valign="bottom" width="170">
                            <a href="http://english.ctrip.com" style="text-decoration:none;"><img src="${resourcePath.trademarkLogoPath!}" width="166" height="40" border="0" alt=""></img></a>
                        </td>
                        <td rowspan="3" valign="bottom">
                            <a href="http://english.ctrip.com" style="text-decoration:none;"><img src="${resourcePath.iataLogoPath!}" width="95" height="60" border="0" alt=""></img></a>
                        </td>
                        <td align="right" style="font-family:${fontFamily},sans-serif;line-height:1.5;color:#999999;">${getResource("bookingNo")} <a href="${orderDetail.orderDetailUrl}" target="_blank" style="color:#2681FF;text-decoration:underline;">${orderDetail.orderId?string["0"]}</a><br/>${getResource("bookingDate")}: ${orderDetail.orderDate?datetime}</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="20"></td>
        </tr>
        <tr><td style="font-size:20px;font-weight:bold;">
        ${getResource("itinerarypdf")}<br/>
            <span style="font-size:14px;">(${getResource("airlineBookingReference")}:${orderDetail.pnr})</span>
        </td></tr>
        <tr>
            <td height="15"></td>
        </tr>
        <tr>
            <td>
            ${getResource("itineraryadvise")}
            </td>
        </tr>
        <tr>
            <td height="15" colspan="2" style="border-bottom:1px solid #dddddd;"></td>
        </tr>
        <tr>
            <td height="15"></td>
        </tr>
        <tr>
            <td style="font-size: 16px;font-weight:700;">${getResource("rescheduledPassenger")}</td>
        </tr>
        <tr>
            <td height="10"></td>
        </tr>
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td width="25%" style="color: #999999;">${getResource("pName")}</td>
                        <td style="color: #999999;">${getResource("eTicketNo")}</td>
                    </tr>
                <#if (orderDetail.passengerList??&&orderDetail.passengerList?size > 0)>
                    <#list orderDetail.passengerList as passenger>
                        <tr>
                            <td height="10" colspan="2"></td>
                        </tr>
                        <tr>
                            <td>${passenger.name}</td>
                            <td>
                                <#if (passenger.eTickets??&&passenger.eTickets?size > 0)>
                                    ${passenger.eTickets?join(",")}
                                </#if>
                            </td>
                        </tr>
                    </#list>
                </#if>
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
            <td style="font-size: 16px;font-weight:700;">${getResource("flightDetails")}</td>
        </tr>
        <tr>
            <td height="10"></td>
        </tr>
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" style="border:1px solid #dddddd;">
                    <tr><td colspan="6" style="height:8px;background: #F2F8FF;"></td></tr>
                    <tr>
                        <th style="width:10px;background: #F2F8FF;"></th>
                        <th style="background: #F2F8FF;">${getResource("itinerarydatime")}</th>
                        <th style="background: #F2F8FF;width:45%">${getResource("itinerarydaport")}</th>
                        <th style="background: #F2F8FF;">${getResource("flightNo")}</th>
                        <th style="background: #F2F8FF;">${getResource("flightClass")}</th>
                        <th style="width:10px;background: #F2F8FF;"></th>
                    </tr>
                    <tr><td colspan="6" style="height:8px;background: #F2F8FF;"></td></tr>
                    <tr>
                        <td height="0" colspan="6" style="border-bottom:1px solid #dddddd;"></td>
                    </tr>
                <#list orderDetail.flightInfoList as flightInfo>
                    <#list flightInfo.columnList as col>
                        <tr>
                            <td height="10" colspan="6"></td>
                        </tr>
                        <tr>
                            <td style="width:10px;"></td>
                            <td>${col.dDate?datetime}</td>
                            <td style="line-height:1.2;">
                                ${col.dPort.name} ${col.dTerminal.shortName}
                            </td>
                            <td rowspan="3">${col.flightNo}<br/><span style="color:#999999;"><#if (col.carrierFlightNo??&&col.carrierFlightNo?length>0)> Code share ${col.carrierFlightNo}</#if></span></td>
                            <td rowspan="3">${col.className}</td>
                            <td style="width:10px;"></td>
                        </tr>
                        <tr>
                            <td height="10" colspan="6"></td>
                        </tr>
                        <tr>
                            <td style="width:10px;"></td>
                            <td>${col.aDate?datetime}</td>
                            <td>
                                ${col.aPort.name} ${col.aTerminal.shortName}
                            </td>
                        </tr>
                        <tr>
                            <td height="10" colspan="6"></td>
                        </tr>
                        <#if (col.baggageInfoFree?? && col.baggageInfoFree?length gte 1) || (col.passengerBaggagePaidModelList?? && col.passengerBaggagePaidModelList?size gte 1)>
                        <tr>
                            <td style="width:10px;"></td>
                            <td colspan="4">
                                ${getResource("baggageAllowance")}<br/>
                                <#if col.baggageInfoFree?? && col.baggageInfoFree?length gte 1>
                                    <p style="margin: 0;padding:0;height:5px;"></p>
                                    <p style="margin: 0;padding:0;"><strong style="color:#999999;">${getResource("itineraryfree")}</strong>${col.baggageInfoFree}</p>
                                </#if>
                                <#if col.passengerBaggagePaidModelList?? && col.passengerBaggagePaidModelList?size gte 1>
                                    <p style="margin: 0;padding:0;height:5px;"></p>
                                    <p style="margin: 0;padding:0;">
                                        <strong style="color:#999999;">${getResource("itinerarypaid")}</strong>
                                        <#list col.passengerBaggagePaidModelList as paidBaggageInfo>
                                        <#if paidBaggageInfo.baggagsStatus == 4>
                                            ${paidBaggageInfo.passengerName} :
                                            <#if paidBaggageInfo.pkgNumber?? && paidBaggageInfo.pkgNumber gt 0>
                                                ${getResource("paidBaggageDisplayPattern")?replace('${"$"}{piece}',paidBaggageInfo.pkgNumber)?replace('${"$"}{weight}',paidBaggageInfo.weightInfo.chargeWeight)}<br/>
                                            <#else>
                                                ${paidBaggageInfo.weightInfo.chargeWeight}kg<br/>
                                            </#if>
                                        </#if>
                                        </#list>
                                    </p>
                                </#if>
                            </td>
                            <td style="width:10px;"></td>
                        </tr>
                        <tr>
                            <td height="10" colspan="6" style="border-bottom:1px solid #dddddd;"></td>
                        </tr>
                        </#if>
                    </#list>
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
            <td style="font-size: 16px;font-weight:700;line-height: 1;">${getResource("itineraryimportant")}</td>
        </tr>
        <tr>
            <td height="10"></td>
        </tr>
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td style="vertical-align: top;padding-right: 5px;">•</td>
                        <td>${getResource("itinerarycontent1")}</td>
                    </tr>
                    <tr>
                        <td height="10" colspan="2"></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: top;padding-right: 5px;">•</td>
                        <td>${getResource("itinerarycontent2")}</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

</center>
</body>
</html>