<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${getResource("bookingConfirmationTitle")}</title>
    <style type="text/css">
        td[class="blue"]:hover {
            cursor: pointer;
            background-color: #3498db;
        }

        td[class="orange"]:hover {
            cursor: pointer;
            background-color: #ffc600;
        }

        .mail-header-container,
        .mail-footer-container {
            width: 600px;
        !important;
        }

        @media only screen and (max-width: 600px) {
            .mail-header-container,
            .mail-footer-container {
                width: 100% !important;
            }

            .mail-contact-us {
                width: 100% !important;
                display: block !important;
                padding-bottom: 10px;
            !important;
            }

            .mail-contact-us-padding {
                height: 10px !important;
            }
        }
    </style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <!--邮件头-->
        ${emailHeader}
            <table width="600" border="0" cellpadding="0" cellspacing="0"
                   style="font-family:Arial,sans-serif;font-size:12px;color:#212121;text-align:left;">
                <tr>
                    <td style="padding:5px 20px 0;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family:Arial,sans-serif;font-size:24px;padding-bottom:20px;padding-top:20px;text-decoration:none;">${getResource("bookingConfirmationTitle")}</td>
                            </tr>
                            <tr>
                                <td style="font-size:12px;padding-bottom:25px;font-family:Arial,sans-serif;">
                                    <strong>${getResource("dearCustomer")}</strong>,<br/><br/>
                                <#if orderDetail.trademark == "Trip">
                                    <#if orderDetail.xProduct?? && orderDetail.xProduct.xCheckInList?? && orderDetail.xProduct.xCheckInList?size gt 0>
                                        <p style="padding-top:5px;margin:0;line-height: 1.2;">
                                            ${getResource("checkInRemind")?replace('${"$"}{trademark}','Trip.com')?replace('${"$"}{checkinstarttime}',orderDetail.xProduct.xCheckInList[0].checkInStartTime?datetime)}
                                        </p><br/>
                                    <#else>
                                        <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("bookingConfirmationTitle1")?replace('${"$"}{trademark}','Trip.com')}</p><br/>
                                    </#if>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("bookingConfirmationTitle2")?replace('${"$"}{trademark}','Trip.com')}</p><br/>
                                    <p style="padding-top:10px;margin:0;line-height: 1.2;">${getResource("bookingConfirmationTitle3")}</p>
                                <#elseif orderDetail.trademark == "Ctrip">
                                    <#if orderDetail.xProduct?? && orderDetail.xProduct.xCheckInList?? && orderDetail.xProduct.xCheckInList?size gt 0>
                                        <p style="padding-top:5px;margin:0;line-height: 1.2;">
                                            ${getResource("checkInRemind")?replace('${"$"}{trademark}','Ctrip')?replace('${"$"}{checkinstarttime}',orderDetail.xProduct.xCheckInList[0].checkInStartTime?datetime)}
                                        </p><br/>
                                    <#else>
                                        <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("bookingConfirmationTitle1")?replace('${"$"}{trademark}','Ctrip')}</p><br/>
                                    </#if>
                                    <p style="padding-top:5px;margin:0;line-height: 1.2;">${getResource("bookingConfirmationTitle2")?replace('${"$"}{trademark}','Ctrip')}</p><br/>
                                    <p style="padding-top:10px;margin:0;line-height: 1.2;">${getResource("bookingConfirmationTitle3")}</p>
                                </#if>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;font-weight: 700;">
                                            <#if orderDetail.trademark == "Trip">
                                                ${getResource("bookingNo")?replace('${"$"}{trademark}','Trip.com')}
                                            <#elseif orderDetail.trademark == "Ctrip">
                                                ${getResource("bookingNo")?replace('${"$"}{trademark}','Ctrip')}
                                            </#if>
                                            </td>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;color:#2681FF;padding-bottom: 8px;">
                                                <a href="${orderDetail.orderDetailUrl}" target="_blank"
                                                   style="color:#2681FF;">${orderDetail.orderId?c}</a></td>
                                        </tr>
                                        <tr>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;font-weight: 700;">${getResource("bookingOn")}</td>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;">${orderDetail.orderDate?datetime}</td>
                                        </tr>
                                        <tr>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;font-weight: 700;">${getResource("airlineBookingReference")}</td>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;font-weight: 700;">${orderDetail.pnr}</td>
                                        </tr>
                                    <#if (orderDetail.relatedOrderInfoList??&&orderDetail.relatedOrderInfoList?size > 0)>
                                        <tr>
                                            <td colspan="2" style="height: 10px;"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;">
                                                ${getResource("relatedOrderDetails")}
                                            </td>
                                        </tr>
                                        <#list orderDetail.relatedOrderInfoList as relatedOrderInfo>
                                            <tr>
                                                <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;font-weight: 700;">
                                                    ${relatedOrderInfo.flightWayInfo}
                                                </td>
                                                <td style="font-family:Arial,sans-serif;font-size:12px;color:#2681FF;padding-bottom: 8px;">
                                                    <a href="${relatedOrderInfo.orderDetailUrl}" target="_blank" style="color:#2681FF;">${relatedOrderInfo.orderId?c}</a>
                                                </td>
                                            </tr>
                                        </#list>
                                    </#if>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="border-bottom:1px solid #dddddd;height: 20px;"></td>
                            </tr>
                        <#list orderDetail.flightInfoList as flightRouteInfo>
                            <tr>
                                <td valign="top" style="padding:25px 0 20px;font-size:14px;font-family: Arial,sans-serif;">
                                    <strong style="margin-right: 5px;">
                                        <#if orderDetail.flightWay == "OW">
                                            ${getResource("flightDetails")}
                                        <#elseif (orderDetail.flightWay == "RT")>
                                            <#if flightRouteInfo.segmentNo == 1>
                                                ${getResource("departure")}
                                            <#elseif flightRouteInfo.segmentNo == 2>
                                                ${getResource("return")}
                                            </#if>
                                        <#elseif orderDetail.flightWay == "MT">
                                            ${getResource("flightRoute")} ${flightRouteInfo.segmentNo}
                                        </#if>
                                    </strong> (${flightRouteInfo.transferAirportCodeList?join(" - ")})
                                </td>
                            </tr>
                            <#list flightRouteInfo.columnList as flightColumnInfo>
                                <#if flightRouteInfo.columnLayoverDetailList?? && flightRouteInfo.columnLayoverDetailList?size gte 1>
                                    <#list flightRouteInfo.columnLayoverDetailList as columnLayoverDetail>
                                        <#if columnLayoverDetail.curColumnSegmentNo == flightColumnInfo.segmentNo && columnLayoverDetail.curColumnSequenceNo == flightColumnInfo.sequenceNo>
                                            <tr>
                                                <td style="padding: 8px 0;">
                                                    <table border="0" cellpadding="0" cellspacing="0" width="100%"
                                                           style="font-family:Arial,sans-serif;font-size:12px;">
                                                        <tr>
                                                            <td valign="middle" style="width:30%;padding-top: 5px;">
                                                                <div style="width:100%;border-top: 1px dashed #dddddd;height: 1px;"></div>
                                                            </td>
                                                            <td style="text-align:center;font-weight: 700;font-size:12px;font-family: Arial,sans-serif;"><span style="vertical-align: -20%;">${getResource("layover")}: ${columnLayoverDetail.hourLayover}${getResource("hour")} ${columnLayoverDetail.minuteLayover}${getResource("minute")}</span></td>
                                                            <td valign="middle" style="width:30%;padding-top: 5px;">
                                                                <div style="width:100%;border-top: 1px dashed #dddddd;height: 1px;"></div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </#if>
                                    </#list>
                                </#if>
                                <tr>
                                    <td style="padding-bottom: 10px;font-size:12px;font-family: Arial,sans-serif;">
                                        <span style="margin-right: 15px;">${flightColumnInfo.dCity.name} - ${flightColumnInfo.aCity.name}</span>
                                        <span>${flightColumnInfo.airline.name} · ${flightColumnInfo.flightNo}
                                            <#if flightColumnInfo.carrierFlightNo?? && flightColumnInfo.carrierFlightNo?length gte 1>
                                                (${flightColumnInfo.carrierFlightNo})
                                            </#if>
                                            </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding: 20px;background: #F2F8FF;font-size:12px;font-family: Arial,sans-serif;">
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%"
                                               style="font-family:Arial,sans-serif;font-size:12px;">
                                            <tr>
                                                <td style="padding-bottom: 8px;font-size:12px;">${flightColumnInfo.dDate?datetime}</td>
                                                <td style="font-weight: 700; padding-bottom: 8px;font-size:12px;">${flightColumnInfo.dPort.code}</td>
                                                <td style="padding-bottom: 8px;font-size:12px;">${flightColumnInfo.dPort.name} ${flightColumnInfo.dTerminal.shortName}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding-bottom: 8px;font-size:12px;">${flightColumnInfo.aDate?datetime}</td>
                                                <td style="font-weight: 700;padding-bottom: 8px;font-size:12px;">${flightColumnInfo.aPort.code}</td>
                                                <td style="padding-bottom: 8px;font-size:12px;">${flightColumnInfo.aPort.name} ${flightColumnInfo.aTerminal.shortName}</td>
                                            </tr>
                                            <#if (flightColumnInfo.baggageInfoFree?? && flightColumnInfo.baggageInfoFree?length gte 1) || (flightColumnInfo.passengerBaggagePaidModelList?? && flightColumnInfo.passengerBaggagePaidModelList?size gte 1)>
                                                <tr>
                                                    <td colspan="3" style="padding-top:8px;font-size:12px;">
                                                        <strong>${getResource("baggageAllowance")}</strong>
                                                        <br/>
                                                        <#if flightColumnInfo.baggageInfoFree?? && flightColumnInfo.baggageInfoFree?length gte 1>
                                                            <p style="padding:5px 0 0;margin: 0;"><strong style="color:#999999;">${getResource("itineraryfree")}</strong>
                                                                ${flightColumnInfo.baggageInfoFree}
                                                            </p>
                                                        </#if>
                                                        <#if flightColumnInfo.passengerBaggagePaidModelList?? && flightColumnInfo.passengerBaggagePaidModelList?size gte 1>
                                                            <p style="padding:5px 0 0;margin: 0;"><strong style="color:#999999;">${getResource("itinerarypaid")}</strong>
                                                                <#list flightColumnInfo.passengerBaggagePaidModelList as paidBaggageInfo>
                                                                <#if paidBaggageInfo.baggageStatus == 4>
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
                                                </tr>
                                            </#if>
                                        </table>
                                    </td>
                                </tr>
                            </#list>
                        </#list>
                            <tr>
                                <td style="border-bottom:1px solid #dddddd;height: 30px;"></td>
                            </tr>
                            <tr>
                                <td style="font-size: 14px;font-weight: 700;padding-top: 20px;padding-bottom: 10px;font-family: Arial,sans-serif;">
                                ${getResource("passenger")}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;color: #888888;"> ${getResource("pName")}</td>
                                            <td style="font-family:Arial,sans-serif;font-size:12px;color: #888888;padding-bottom: 8px;">${getResource("eTicketNo")}</td>
                                        </tr>
                                    <#if (orderDetail.passengerList?? && orderDetail.passengerList?size>0)>
                                        <#list orderDetail.passengerList as  passenger>
                                            <tr>
                                                <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;">${passenger.name}</td>
                                                <td style="font-family:Arial,sans-serif;font-size:12px;padding-bottom: 8px;">
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
                                <td style="padding-top: 20px;font-size:12px;">${getResource("flightPolicyUrlDesc")?replace('${"$"}{flightPolicyUrl}',orderDetail.orderDetailUrl + '&policy=show')}</td>
                            </tr>
                            <tr>
                                <#if orderDetail.trademark == "Trip">
                                    <td style="padding-top: 20px;padding-bottom: 20px;font-size:12px;font-family: Arial,sans-serif;">${getResource("forMoreInformation")?replace('${"$"}{trademark}','Trip.com')}</td>
                                <#elseif orderDetail.trademark == "Ctrip">
                                <td style="padding-top: 20px;padding-bottom: 20px;font-size:12px;font-family: Arial,sans-serif;">${getResource("forMoreInformation")?replace('${"$"}{trademark}','Ctrip')}</td>
                                </#if>
                            </tr>
                            <tr>
                                <td style="padding: 15px;background: #F7F7F7;">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td colspan="2" style="font-size:14px;font-weight: 700;padding-bottom: 10px;font-family: Arial,sans-serif;">${getResource("importantInformation")}</td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;padding-right: 5px;">•</td>
                                            <td style="padding-bottom: 8px;font-size:12px;font-family: Arial,sans-serif;">${getResource("importantInformation1")}</td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;padding-right: 5px;">•</td>
                                            <td style="padding-bottom: 8px;font-size:12px;font-family: Arial,sans-serif;">${getResource("importantInformation2")}</td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;padding-right: 5px;">•</td>
                                            <td style="padding-bottom: 8px;font-size:12px;font-family: Arial,sans-serif;">${getResource("importantInformation3")}</td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;padding-right: 5px;">•</td>
                                            <td style="padding-bottom: 8px;font-size:12px;font-family: Arial,sans-serif;">${getResource("importantInformation4")}</td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;padding-right: 5px;">•</td>
                                            <td style="padding-bottom: 8px;font-size:12px;font-family: Arial,sans-serif;">${getResource("importantInformation5")}</td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align: top;padding-right: 5px;">•</td>
                                            <td style="padding-bottom: 8px;font-size:12px;font-family: Arial,sans-serif;">${getResource("importantInformation6")}</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
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