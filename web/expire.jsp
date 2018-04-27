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
    <title>活动过期</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ignorePath/common/common.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ignorePath/expire.css">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style,content=black"/>
</head>
<body>
<div id="container" class="container">
    <div class="header">
        <img class="backgroundImg2" id="backgroundImg" src="${ctx}/userImg/getImgByUrl.action?imgUrl=${activity.backgroundUrl}">
    </div>
    <div class="content">
        <div class="tips">
            <p>活动未开始或已经结束</p>
            <p>开始时间:${activity.startTime}</p>
            <p>结束时间:${activity.endTime}</p>
        </div>
        <%--<div class="btn">
            <li><a class="greenBtn" onclick="javascript:history.back(-1);">返回</a>
            </li>
        </div>--%>
    </div>
</div>

</body>
</html>