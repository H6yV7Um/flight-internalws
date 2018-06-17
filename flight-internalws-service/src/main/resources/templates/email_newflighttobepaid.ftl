<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${sharkMessageResource.newFlightTobePaid}</title>
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
            <table width="600" border="0" cellpadding="0" cellspacing="0" style="font-family:'Arial',sans-serif;font-size:14px;color:#4a4a4a;text-align:left;word-break:break-all">
                <tr>
                    <td width="600" style="padding:0 20px;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td style="font-size:14px;line-height:22px;padding:10px 0 0;">
                                ${sharkMessageResource.dearCustomer}
                                    <br/>
                                    <br/>
                                ${sharkMessageResource.newFlightTobePaidDetailPart_1}
                                <#list orderDetail.flightInfoList as flightInfo>
                                ${flightInfo.dCity.name!} ${sharkMessageResource.newFlightTobePaidDetailPart_2} ${flightInfo.aCity.name!},${sharkMessageResource.newFlightTobePaidDetailPart_5}
                                </#list>(${sharkMessageResource.orderId} ${orderDetail.orderId?string("0")},${sharkMessageResource.passenger}:
                                <#list orderDetail.passengerList as passenger>
                                ${passenger.name} 
                                </#list>)${sharkMessageResource.newFlightTobePaidDetailPart_3} <br/>
                                ${sharkMessageResource.newFlightTobePaidDetailPart_4}
                                </td>
                            </tr>
                            <tr>
                                <td style=" font-size:14px;line-height:24px;padding:30px 0;">
                                    <div style="text-align:center;">
                                        <table border="0" cellpadding="0" cellspacing="0" style="display:inline-block;font-family:'Arial',sans-serif;">
                                            <tr>
                                                <td align="center" bgcolor="#ffb000" style="width:160px;padding:10px;border-radius:5px;text-align:center;" class="orange"><a href="${payurl!}" style="font-size:16px;color:#fff;text-decoration:none;">Go to Pay</a></td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:600px;font-family:'Arial',sans-serif;font-size:14px;line-height:22px">
                                ${sharkMessageResource.newFlightTobePaidDescPart_1}<br />
                                </td>
                            </tr>
                            <tr>
                                <td style="width:600px;font-family:'Arial',sans-serif;font-size:14px;line-height:22px;word-break:break-all;">
                                    <a style="font-family:'Arial',sans-serif;color:#1171b7;text-decoration:underline;word-break:break-all;">${payurl!}</a><br />
                                    <br />
                                    <br />
                                </td>
                            </tr>
                            <tr>
                                <td style="width:600px;font-family:'Arial',sans-serif;font-size:14px;line-height:22px;word-break:break-all;">
                                ${sharkMessageResource.newFlightTobePaidDescPart_2}<br />
                                </td>
                            </tr>
                            <tr>
                                <td style="margin:0;padding:20px 0;">
                                    <p style="margin:0;text-align:center;">
                                    </p>
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
