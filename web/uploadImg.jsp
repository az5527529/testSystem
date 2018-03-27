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
    <link rel="stylesheet" href="${ctx}/css/ignorePath/uploadImg.css">
</head>

<body>
    <div id="content" >
        <form id="userForm" action="${ctx}/userImg/uploadImg.action" method="post" enctype="multipart/form-data">
            <input type="hidden" id="openid" name="openid">
            <input type="hidden" id="imgUrl">
            <input type="hidden" id="userImgId" name="userImgId">
            <li style="height: auto;">
                <div class="imageDiv">
                    <img id="userImg" src="">
                </div>

            </li>
            <li style="height: auto;"><input type="file" id="uploadFile"  name="uploadFile" onchange="preview(this)">
            </li>
            <li class="bigFont"><label>姓名<span class="redStar">*</span>:</label><input id="userName" name="userName" type="text" placeholder="请输入姓名" required="required" /></li>
            <li class="bigFont"><label>电话<span class="redStar">*</span>:</label><input name="telephone" id="telephone" type="text" placeholder="请输入电话" required="required" /></li>
        </form>
        <ul id="uploadBtnUl">
            <li class="btnLi">
                <a href="javascript:void(0)" onclick="upload()" >提交</a>
            </li>
        </ul>

    </div>

</body>
<script src="${ctx}/js/ignorePath/uploadImg.js"></script>
<script type="text/javascript">

</script>
</html>
