<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" bodyContent="text/html; charset=UTF-8">
    <title>邮件测试页面</title>
    <script data-main="/test/js/home" src="/test/js/lib/require.js"></script>
</head>
<body>
<div>
    <div>
        <h3>测试邮件发送</h3>
    </div>
    <div>
        <p>订单号OrderId:<input type="text" placeholder="OrderId" id="orderId"/></p>
    </div>

    <div>
        <p>
            额外ID信息(多个用逗号分隔)
            <input type="text" placeholder="额外ID信息" id="additionIdList"/>
        </p>
    </div>

    <div>
        <p>用户Uid:<input type="text" placeholder="UID" id="uid"/></p>
    </div>

    <div>
        <p>
            邮件内容是否自定义
            <select id="isCustomContent">
                <option value="0" select="selected">false</option>
                <option value="1">true</option>
            </select>
        </p>
    </div>

    <div>
        <p>
            邮件模板类型TemplateType:
            <select id="templateTypeList">
                <#list emailTemplateList as tmpl>
                <option value="${tmpl.getValue()}">${tmpl}</option>
                </#list>
            </select>
        </p>
    </div>

    <div>
        <p>
            发送语言Language:
            <select id="languageTypeList">
                <#list emailSendLanList as lan>
                <option value="${lan.getValue()}">${lan}</option>
                </#list>
            </select>
        </p>
    </div>

    <div>
        <p>收件人Email(多个用逗号分隔):<input type="text" placeholder="RecipientEmail" id="recipientEmail"/></p>
    </div>

    <div>
        <p>抄送(多个用逗号分隔):<input type="text" placeholder="CCEmail" id="ccEmail"/></p>
    </div>

    <div>
        <p>密送(多个用逗号分隔):<input type="text" placeholder="BCCEmail" id="bccEmail"/></p>
    </div>

    <div>
        <p>
            邮件内容是否为HTML格式
            <select id="isContentHtml">
                <option value="1" select="selected">true</option>
                <option value="0">false</option>
            </select>
        </p>
    </div>

    <div>
        <p>
            自定义邮件Subject
            <input type="text" placeholder="Subject" id="subject"/>
        </p>
    </div>

    <div>
        <p>
            自定义邮件内容
            <input type="text" placeholder="如果为自定义内容邮件，请输入邮件内容" id="customContent"/>
        </p>
    </div>

    <div>
        <button id="sendEmailBtn">SendEmail</button>
    </div>

    <div>
        <p id="sendResultCode"></p>
    </div>
</div>

<div>
    <div>
        <h3>获取邮件发送状态</h3>
    </div>
    <div>
        <p>
            EmailId:<input type="text" id="emailId" placeholder="多个用逗号分隔"/>
        </p>
        <p>
            SendCode(17090002):<input type="text" id="sendCode" placeholder="邮件通道SendCode"/>(Java新邮件通道为17090002)
        </p>
    </div>
    <div>
        <p>
            <button id="btnGetEmailStatus">获取邮件</button>
        </p>
    </div>
    <div id="emailDetail">

    </div>
</div>
</body>
</html>