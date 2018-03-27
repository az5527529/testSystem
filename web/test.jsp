<%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 2018/2/27
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<%
    String code = request.getParameter("code");
    if("null".equals(code) || code == null){
        code = "";
    }
%>
<script type="text/javascript">
    var code = "<%=code%>";
</script>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style,content=black"/>
    <link href="${ctx}/css/ignorePath/test.css" type="text/css" rel="stylesheet">
</head>

<body>
    <div id="content"  style="display: none;">
        <div id="question">

        </div>
        <div id="userDiv">
            <form id="userForm">
                <input type="hidden" id="openid">
                <input type="hidden" id="activityId">
                <ul>
                    <li><label>姓名<span class="redStar">*</span>:</label><input id="userName" name="userName" placeholder="请输入姓名" required="required"  /></li>
                    <li><label>电话<span class="redStar">*</span>:</label><input name="telephone" id="telephone" placeholder="请输入电话" required="required" /></li>

                </ul>

            </form>
            <ul>
                <li style="height: 2.5rem;line-height: 2.5rem">
                    <a href="javascript:void(0)" onclick="saveTest()" >提交</a>
                </li>
            </ul>

        </div>
    </div>

</body>
<script src="${ctx}/js/ignorePath/test.js"></script>

</html>
